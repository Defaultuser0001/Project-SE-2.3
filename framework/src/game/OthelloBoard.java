package game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.BoardController;

import view.GameBoard;



public class OthelloBoard extends GameBoard{

	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	/**
	 * Creates a new instance of the OthelloBoard
	 * @param listener An ActionListener that handles actions fired by this class
	 */
	public OthelloBoard(BoardController listener) {
		super(listener);
		setBoardTiles();
		setLayout(new GridLayout(8,8));
	}

	/**
	 * Creates 64 tiles with the starting position of an othello game
	 * 
	 *  0  1  2  3  4  5  6  7
	 *  8  9 10 11 12 13 14 15
	 * 16 17 18 19 20 21 22 23
	 * 24 25 26 27 28 29 30 31
	 * 32 33 34 35 36 37 38 39
	 * 40 41 42 43 44 45 46 47
	 * 48 49 50 51 52 53 54 55
	 * 56 57 58 59 60 61 62 63
	 * 
	 */
	@Override
	protected void setBoardTiles() {
		for (int i = 0; i < 64; i++){
			Tile tile = new Tile(i);
			if (i == 27|| i == 36) {
				tile.setWhite();
			}if (i == 28 || i == 35) {
				tile.setBlack();
			}
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
		if (player == WHITE){
			tiles.get(move).setWhite();
		} else {
			tiles.get(move).setBlack();
		}
	}

	/**
	 * private inner class
	 * 
	 * Takes care of visualization of a tile of an othello game
	 */
	private class Tile extends JButton{

		private ImageIcon white = new ImageIcon("./res/Othello/Othello_white.png");
		private ImageIcon black = new ImageIcon("./res/Othello/Othello_black.png");
		
		private int move;
		
		public Tile(int move) {
			super();
			this.move = move;
			setBackground(new Color(0,155,0));
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
