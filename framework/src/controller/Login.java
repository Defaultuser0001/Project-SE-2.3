package controller;

import view.LoginView;
import tools.ServerConnection;

import java.io.IOException;

public class Login {

    private ServerConnection connection;
    //private LoginView loginview;

    public Login(String username) throws IOException {
        //Create a new server conneciton
        this.connection = new ServerConnection();
        Thread thread = new Thread(new ServerListener(this.connection));
        thread.start();
        connection.sendCommand("login " + username);
    }

}

