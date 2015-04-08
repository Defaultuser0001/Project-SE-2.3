package controller;

import exceptions.UsernameAlreadyExistsException;
import tools.ServerConnection;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServerListener implements Runnable{

	private ServerConnection connection;
    private BufferedReader in;

    public ServerListener(ServerConnection connection){
        this.connection = connection;
        try {
            in = new BufferedReader(new InputStreamReader(connection.getSocket().getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String reader, lastLine;
        try {
            while((reader = in.readLine()) != null) {
                System.out.println(reader);
                lastLine = reader;
                if (connection.getLastCommand().equals("login") && lastLine.equals("OK")) {
                    connection.setLoginSuccess(true);
                } else if (connection.getLastCommand().equals("login") && lastLine.equals("ERR Duplicate name exists")) {
                    connection.setLoginSuccess(false);
                } else if (connection.getLastCommand().equals("login") && lastLine.equals("ERR Already logged in")){
                    System.err.println("Congrats, you managed to break the login system");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UsernameAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
