package controller;

import exceptions.ServerErrorException;
import game.TicTacToeController;
import model.Human;
import model.Player;
import tools.ServerConnection;
import view.ChooseGameView;
import view.GameView;
import view.LoginView;
import view.PlayerListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import javax.swing.JOptionPane;



public class ServerListener implements Runnable{

	private ServerConnection connection;
    private BufferedReader in;
    private LoginView view;
    private Player player;
    private ChooseGameView gameView;
    private BoardController activeGame;
    private GameView activeGameView;
    private static final int CHARACTERREMOVAL = 15;

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
                /*
                 * Log in
                 */
                if (connection.getLastCommand().equals("login") && lastLine.equals("OK")) {
                    player = new Human(view.getLogin().getUsername());
                    view.setVisible(false);
                    view.dispose();
                    gameView = new ChooseGameView((Human)player, connection, this);
                } else if (connection.getLastCommand().equals("login") && lastLine.equals("ERR Duplicate name exists")) {
                    throw new ServerErrorException("Username already in use!");
                } else if (connection.getLastCommand().equals("login") && lastLine.equals("ERR Already logged in")){
                    System.err.println("Congrats, you managed to break the login system");
                /*
                 * Listen if a move has been made.
                 */
                } else if (lastLine.contains("SVR GAME MOVE")){
                	activeGame.getModel().setActivePlayer(false);
                	activeGameView.updateLabel();
                	if (!lastLine.contains(player.getName())) {
                		activeGame.playMove(Integer.parseInt(lastLine.split("MOVE: ")[1].replaceAll("[^0-9]", "")));
                	}
                /*
                 * Listen whose turn it is.
                 */
                } else if (lastLine.contains("SVR GAME YOURTURN")){
                	activeGame.getModel().setActivePlayer(true);
                	activeGameView.updateLabel();
                /*
                 * Grab the playerlist
                 */
                } else if(lastLine.contains("SVR PLAYERLIST")){
                	//Grab all the users from the line we receive from the server. Then remove the first fifteen characters in order to get to the playernames.
                	String[] users = lastLine.substring(CHARACTERREMOVAL).split("\\s+");
                	LinkedList<String> list = new LinkedList<String>();
                	for (int i = 0; i < users.length; i++) {
                		//Removing all the interpunction
						users[i] = users[i].replace(",", "").replace("[", "").replace("]", "").replace("\"", "").replace(player.getName(), "");
						list.add(users[i]);
					}
                	//Generate a new PlayerList as soon as the button is clicked and a command was fired.
                	PlayerListView plist = new PlayerListView(list, connection);
                /*
                 * Receive a challenge
                 */
                } else if(lastLine.contains("SVR GAME CHALLENGE {")){
                	String[] str = lastLine.split("\\s+");
                	for (int i = 0; i < str.length; i++) {
                		str[i] = str[i].replace(",", "").replace("[", "").replace("]", "").replace("\"", "").replace("}", "");
					}
                	String challenger = str[4];
                	int id = Integer.parseInt(str[6]);
                	String gametype = str[8];
                	int reply = JOptionPane.showConfirmDialog(null, challenger + " has challenged you to play " + gametype, "Challenge received!", JOptionPane.YES_NO_OPTION);
                	if(reply == JOptionPane.YES_OPTION){
                		connection.sendCommand("challenge accept " + id);
                	}
                	
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
