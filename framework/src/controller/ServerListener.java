package controller;

import exceptions.ServerErrorException;
import model.Human;
import model.Player;
import tools.ServerConnection;
import view.ChooseGameView;
import view.LoginView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class ServerListener implements Runnable{

	private ServerConnection connection;
    private BufferedReader in;
    private LoginView view;
    private Player player;
    private ChooseGameView gameView;

    public enum Commands {
        LOGIN,
        LOGOUT,
        SUBSCRIBE,
        MOVE
    }


    /**
     * The ServerListener works on its own and will constantly idle on the background and check for new output from the server.
     * As soon as the server sends in a new line(s) of information, the listener will parse these, and check whether we have to do something
     * with the output.
     * @param connection
     */
    public ServerListener(ServerConnection connection, LoginView view){
        this.view = view;
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
                //processCommand(connection.getLastCommand(), lastLine);
                if (connection.getLastCommand().equals("login") && lastLine.equals("OK")) {

                    player = new Human(view.getLogin().getUsername());
                    view.setVisible(false);
                    view.dispose();
                    gameView = new ChooseGameView((Human)player, connection);
                } else if (connection.getLastCommand().equals("login") && lastLine.equals("ERR Duplicate name exists")) {
                    throw new ServerErrorException("Username already in use!");
                } else if (connection.getLastCommand().equals("login") && lastLine.equals("ERR Already logged in")){
                    System.err.println("Congrats, you managed to break the login system");
                } else if (connection.getLastCommand().equals("move") && lastLine.equals("OK")){
                    // move has been succesfully done
                } else if (lastLine.contains("SVR GAME MOVE")){
                	// someone made a move (either you or your opponent)
                } else if (lastLine.contains("SVR GAME YOURTURN")){
                	// it's the players turn to move, allow him to do so and set the timer in motion
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServerErrorException e) {
            e.printStackTrace();
        }
    }

    /*private void processCommand(String lastCommand, String lastLine) throws InterruptedException {

        String str = lastCommand;
        Commands command = Commands.valueOf(str.toUpperCase());

        switch(command){
            case LOGIN:
                System.out.println("LOGIN");
                break;
            case MOVE:
                System.out.println("MOVE");
            break;
            default:
                System.out.println("waiting..");
                Thread.sleep(1000);
                break;
        }
    }*/
}
