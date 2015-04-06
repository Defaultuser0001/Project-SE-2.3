package tools;


import model.Stats;

import java.io.IOException;
import java.net.Socket;

public class ServerConnection {

	private Stats stats;

    public ServerConnection() throws IOException{
        //Create a new socket connection
        Socket socket = new Socket("localhost", 7789);
    }
	
}
