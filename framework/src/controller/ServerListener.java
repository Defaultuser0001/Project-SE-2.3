package controller;

import exceptions.ServerErrorException;
import game.TicTacToeController;
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
    private BoardController activeGame;
    private GameView activeGameView;
	private ChooseGameController cgcontroller;
	


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
                    player = new Player(view.getLogin().getUsername());
                    view.setVisible(false);
                    view.dispose();
                    ChooseGameView gameView = new ChooseGameView(player, connection, this);
                } else if (connection.getLastCommand().equals("login") && lastLine.equals("ERR Duplicate name exists")) {
                    throw new ServerErrorException("Username already in use!");
                } else if (connection.getLastCommand().equals("login") && lastLine.equals("ERR Already logged in")){
                    System.err.println("Congrats, you managed to break the login system");
                /*
                 * Listen if a move has been made.
                 */
                } else if(lastLine.contains("SVR GAME MATCH")){
                	if(parseString(lastLine).get(1).equals("Tic-tac-toe")){
                		cgcontroller.createGame("ttt");
                		cgcontroller.getView().disableQueueText();
                		if(parseString(lastLine).get(0).equals(player.getName())){
                			activeGame.getModel().setActivePlayer(true);
                			activeGameView.setSide(true);
                		} else {
                			activeGame.getModel().setActivePlayer(false);
                			activeGameView.setSide(false);
                		}
                	} else {
                		cgcontroller.createGame(parseString(lastLine).get(1));
                		cgcontroller.getView().disableQueueText();
                		if(parseString(lastLine).get(0).equals(player.getName())){
                			activeGameView.setSide(true);
                		} else {
                			activeGameView.setSide(false);
                		}
                	}
                
                } else if (lastLine.contains("SVR GAME MOVE")){
                	activeGame.getModel().setActivePlayer(false);
                	activeGameView.updateLabel();
                	if (!parseString(lastLine).get(0).equals(player.getName())) {
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
                	//Generate a new PlayerList as soon as the button is clicked and a command was fired.
                	PlayerListView plist = new PlayerListView(parseString(lastLine), connection);
                /*
                 * Receive a challenge
                 */
                } else if(lastLine.contains("SVR GAME CHALLENGE {")){
                	String challenger = parseString(lastLine).get(0);
                	int id = Integer.parseInt(parseString(lastLine).get(1));
                	String gametype = parseString(lastLine).get(2);      	
                	int reply = JOptionPane.showConfirmDialog(null, challenger + " has challenged you to play " + gametype, "Challenge received!", JOptionPane.YES_NO_OPTION);
                	if(reply == JOptionPane.YES_OPTION){
                		connection.sendCommand("challenge accept " + id);
                	}
                } else if(lastLine.contains("LOSS")){
                	JOptionPane.showMessageDialog(null, "You lose!", "Lost!", JOptionPane.CLOSED_OPTION);
                	activeGameView.dispose();
                	cgcontroller.getView().setEnabled(true);
                } else if(lastLine.contains("WIN")){
                	JOptionPane.showMessageDialog(null, "You win!", "Won!", JOptionPane.CLOSED_OPTION);
                	activeGameView.dispose();
                	cgcontroller.getView().setEnabled(true);
                } else if(lastLine.contains("DRAW")){
                	JOptionPane.showMessageDialog(null, "It's a draw!", "Draw!", JOptionPane.CLOSED_OPTION);
                	activeGameView.dispose();
                	cgcontroller.getView().setEnabled(true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServerErrorException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Parse a string to gather information from the output from the server. Dump the information in a LinkedList
     * @param str
     * @return
     */
    private LinkedList<String> parseString(String str){
    	LinkedList<String> list = new LinkedList<String>();
    	String[] toParse = str.split(",");
    	for (int i = 0; i < toParse.length; i++) {
    		toParse[i] = toParse[i].substring(toParse[i].indexOf("\"")+1,toParse[i].lastIndexOf("\""));
    		list.add(toParse[i]);
		}
    	return list;
    }
    

	public void setActiveGame(BoardController game, GameView gameView) {
		activeGame = game;
		activeGameView = gameView;
	}
	
	public void setActiveChooseGameController(ChooseGameController cgcontroller){
		this.cgcontroller = cgcontroller;
	}

}
