package controller;

import exceptions.UsernameAlreadyExistsException;
import view.ChooseGameView;
import view.LoginView;
import tools.ServerConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Login extends JPanel implements ActionListener{

    private ServerConnection connection;
    private LoginView view;
    private String username;
    private Lock lock = new ReentrantLock();

    public Login(String username, ServerConnection connection, LoginView view) throws IOException {
        this.username = username;
        this.connection = connection;
        this.view = view;
        this.setLayout(new GridLayout(1,1));
        //create login button
        JButton button = new JButton("log_in");
        this.add(button);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("log_in")) {
            try {
                connection.sendCommand("login " + this.username);
                if (connection.isLoginSuccess()) {
                    System.out.println("Login success");
                    view.dispose();
                    new ChooseGameView();
                }
            } catch (UsernameAlreadyExistsException e1) {
                e1.printStackTrace();
            }
        }
    }
}

