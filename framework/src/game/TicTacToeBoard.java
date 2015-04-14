package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.BoardController;

import view.GameBoard;



public class TicTacToeBoard extends GameBoard{

	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	/**
	 * Creates a new instance of the TicTacToeBoard
	 * @param listener An ActionListener that handles actions fired by this class
	 */
	public TicTacToeBoard(BoardController listener) {
		super(listener);
		setBoardTiles();
		setLayout(new GridLayout(3,3));
	}

	/**
	 * Creates 9 tiles with the starting position of a tic tac toe game
	 * 
	 *  0  1  2 
	 *  3  4  5
	 *  6  7  8
	 * 
	 */
	@Override
	protected void setBoardTiles() {
		for (int i = 0; i < 9; i++){
			Tile tile = new Tile(i);
			tile.addActionListener(this);
			tiles.add(tile);
			add(tile);
			
		}
	}

	/**
	 * Fires an action to be caught by this class's ActionListener
	 * Passes along a move number
	 */
	@Override
	protected void processAction(int move) {
		for(ActionListener listener : listeners){
			listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Integer.toString(move)));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Tile tile = (Tile) e.getSource();
		
		processAction(tile.getMove());
	}
	
	/**
	 * Adds the played move to the board
	 * 
	 * @param player the player (black or white) that made the move
	 * @param move the index of the move to be played
	 */
	public void makeMove(int player, int move) {
		if (player == PLAYER1){
			tiles.get(move).setWhite();
		} else {
			tiles.get(move).setBlack();
		}
	}

	/**
	 * private inner class
	 * 
	 * Takes care of visualization of a tile of a tic tac toe game
	 */
	private class Tile extends JButton{

		private ImageIcon white = new ImageIcon("./res/TicTacToe/TicTacToe_circle.png");
		private ImageIcon black = new ImageIcon("./res/TicTacToe/TicTacToe_cross.png");
		
		private int move;
		
		public Tile(int move) {
			super();
			this.move = move;
			setBackground(new Color(150, 150, 150));
		}
		
		public void setWhite() {
			setIcon(white);
		}

		public void setBlack() {
			setIcon(black);
		}
		
		public int getMove(){
			return move;
		}
		
	}

}
