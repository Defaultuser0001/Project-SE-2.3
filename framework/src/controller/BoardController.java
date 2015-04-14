package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import model.BoardModel;

import tools.ServerConnection;
import view.GameBoard;

/**
 * Abstract class that forms the blueprints for a controller that receives input from 
 * the GameBoard class. 
 * Is responsible for updating the BoardModel
 */
public abstract class BoardController implements ActionListener{

	protected GameBoard board;
	protected BoardModel model;
	protected String gameMode;
	protected ServerConnection connection;
	
	public BoardController(int x, int y, String gameMode, ServerConnection connection){
		this.gameMode = gameMode;
		this.connection = connection;
	}

	/**
	 * event is of type Gameboard and the command 
	 */
	@Override
	public abstract void actionPerformed(ActionEvent e);
	
	public JPanel getBoard() {
		return board;
	}
	
	public void playMove(int move) {
		board.makeMove(model.getActivePlayer(), move);
		model.playMove(move);
	}
}
