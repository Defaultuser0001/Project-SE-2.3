package game;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.GameBoard;


public class OthelloBoard extends GameBoard{

	
	public OthelloBoard(ActionListener listener) {
		super(listener);
		setBoardTiles();
		setLayout(new GridLayout(8,8));
	}

	@Override
	protected void setBoardTiles() {
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				Tile tile = new Tile(i,j);
				if ((i == 3 && j == 3) || (i == 4 && j == 4)) {
					tile.setWhite();
				}if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
					tile.setBlack();
				}
				add(tile);
			}
		}
	}

	
	private class Tile extends JButton{

		private ImageIcon white = new ImageIcon("./res/Othello/Othello_white.png");
		private ImageIcon black = new ImageIcon("./res/Othello/Othello_black.png");
		
		private int x;
		private int y;
		
		public Tile(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			setBackground(new Color(0,155,0));
		}
		
		public void setWhite() {
			setIcon(white);
		}

		public void setBlack() {
			setIcon(black);
		}
		
	}
	
}
