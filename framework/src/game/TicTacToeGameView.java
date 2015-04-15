package game;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tools.ServerConnection;
import view.GameView;

public class TicTacToeGameView extends GameView{

	public TicTacToeGameView(JFrame parent, JPanel board, TicTacToeController tttController, ServerConnection connection) {
		super(parent, board, tttController, connection);
		setSize(300, 300);
	}

}
