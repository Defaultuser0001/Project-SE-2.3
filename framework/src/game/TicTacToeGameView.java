package game;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.GameView;

public class TicTacToeGameView extends GameView{

	public TicTacToeGameView(JFrame parent, JPanel board, TicTacToeController tttController) {
		super(parent, board, tttController);
		setSize(300, 300);
	}

}
