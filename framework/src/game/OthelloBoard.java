package game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.GameBoard;



public class OthelloBoard extends GameBoard{

	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	public OthelloBoard(ActionListener listener) {
		super(listener);
		setBoardTiles();
		setLayout(new GridLayout(8,8));
	}

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

	public void makeMove(int player, int move) {
		if (player == PLAYER1){
			tiles.get(move).setWhite();
		} else {
			tiles.get(move).setBlack();
		}
	}

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
