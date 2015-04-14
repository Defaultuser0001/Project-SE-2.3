package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TicTacToeGameView extends GameView{

	public TicTacToeGameView(JFrame parent, JPanel board) {
		super(parent, board);
		setSize(300, 300);
	}

}
