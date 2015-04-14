package controller;

import exceptions.ServerErrorException;
import view.LoginView;
import tools.ServerConnection;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Login extends JPanel implements ActionListener {

    private ServerConnection connection;
    private LoginView view;
    private String username;
    private Lock lock = new ReentrantLock();

    public Login(LoginView view) throws IOException {
        this.view = view;
        JButton button = new JButton("log_in");
        this.add(button);
        button.addActionListener(this);
    }

    public String getUsername(){
        return this.username;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("log_in")) {
            try {
            	connection = new ServerConnection(view.getIP(), view.getPort());
            	Thread thread = new Thread(new ServerListener(connection, view));
                thread.start();
                
                this.username = view.getCurrentName();
                this.connection.sendCommand("login " + this.username);
            } catch (ServerErrorException e1) {
                e1.printStackTrace();
            }
        }
    }
}

