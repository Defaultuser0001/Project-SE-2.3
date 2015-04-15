package tools;


import exceptions.ServerErrorException;
import model.Player;
import model.Stats;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection{

    private PrintWriter out;
    private Socket socket;
    private String lastCommand = "";
    private boolean loginSuccess = false;
    private String playerName;

    public ServerConnection(String ip, int port, String playerName) {
        try {
        	this.playerName = playerName;
            socket = new Socket(ip, port);
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(String msg) throws ServerErrorException {
        try{
            out.write(msg+"\n");
            out.flush();
            String arr[] = msg.split(" ", 2);
            lastCommand = arr[0];
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public String getPlayerName(){
    	return playerName;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getLastCommand() {
        return lastCommand;
    }

    public boolean isLoginSuccess(){
        return loginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) throws ServerErrorException {
        this.loginSuccess = loginSuccess;
        if(this.loginSuccess == false){
            throw new ServerErrorException("Username already exists!");
        }
    }

}
