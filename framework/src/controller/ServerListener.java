package controller;

import exceptions.ServerErrorException;
import game.TicTacToeController;
import model.Human;
import model.Player;
import tools.ServerConnection;
import view.ChooseGameView;
import view.GameView;
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
    private BoardController activeGame;
    private GameView activeGameView;

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
                if (connection.getLastCommand().equals("login") && lastLine.equals("OK")) {

                    player = new Human(view.getLogin().getUsername());
                    view.setVisible(false);
                    view.dispose();
                    gameView = new ChooseGameView((Human)player, connection, this);
                } else if (connection.getLastCommand().equals("login") && lastLine.equals("ERR Duplicate name exists")) {
                    throw new ServerErrorException("Username already in use!");
                } else if (connection.getLastCommand().equals("login") && lastLine.equals("ERR Already logged in")){
                    System.err.println("Congrats, you managed to break the login system");
                } else if (connection.getLastCommand().equals("move") && lastLine.equals("OK")){
                    // move has been succesfully done
                } else if (lastLine.contains("SVR GAME MOVE")){
                	activeGame.getModel().setActivePlayer(false);
                	activeGameView.updateLabel();
                	if (!lastLine.contains(player.getName())) {
                		activeGame.playMove(Integer.parseInt(lastLine.split("MOVE: ")[1].replaceAll("[^0-9]", "")));
                	}
                } else if (lastLine.contains("SVR GAME YOURTURN")){
                	activeGame.getModel().setActivePlayer(true);
                	activeGameView.updateLabel();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServerErrorException e) {
            e.printStackTrace();
        }
    }

	public void setActiveGame(BoardController game, GameView gameView) {
		activeGame = game;
		activeGameView = gameView;
	}

}
