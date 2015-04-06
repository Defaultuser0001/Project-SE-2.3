package tools;


import model.Stats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection {

	private Stats stats;
    private PrintWriter out;
    private Socket socket;

    public ServerConnection() {
        try {
            socket = new Socket("localhost", 7789);
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(String msg){
        try{
            out.write(msg+"\n");
            out.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

	
}
