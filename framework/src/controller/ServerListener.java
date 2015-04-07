package controller;

import exceptions.UsernameAlreadyExistsException;
import tools.ServerConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerListener implements Runnable{

	private ServerConnection connection;
    private BufferedReader in;

    public ServerListener(ServerConnection connection){
        this.connection = connection;
        try {
            System.out.println("Creating new buffered reader");
            in = new BufferedReader(new InputStreamReader(connection.getSocket().getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String reader;
        System.out.println("Created thread");
        try {
            while((reader = in.readLine()) != null || true){
                System.out.println(reader);
                if(reader == "ERR Duplicate name exists"){
                    throw new UsernameAlreadyExistsException("This username is already in use!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UsernameAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
