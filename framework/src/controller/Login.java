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

public class Login extends JPanel {

    private ServerConnection connection;
    private LoginView view;
    private String username;
    private Lock lock = new ReentrantLock();

    public Login(final LoginView view) throws IOException {
        this.view = view;
        JButton button = new JButton("Log in");
        button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				connection = new ServerConnection(view.getIP(), view.getPort(), view.getCurrentName());
            	Thread thread = new Thread(new ServerListener(connection, view));
                thread.start();
                username = view.getCurrentName();
                try {
					connection.sendCommand("login " + username);
				} catch (ServerErrorException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
        	
        });
        this.add(button);
    }

    public String getUsername(){
        return this.username;
    }

}
