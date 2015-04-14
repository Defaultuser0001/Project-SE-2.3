package game;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.GameView;

public class TicTacToeGameView extends GameView{

	public TicTacToeGameView(JFrame parent, JPanel board) {
		super(parent, board);
		setSize(300, 300);
	}

}
