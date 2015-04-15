package game;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tools.ServerConnection;
import view.GameView;

public class OthelloGameView extends GameView{

	public OthelloGameView(JFrame parent, JPanel board, OthelloController controller, ServerConnection connection) {
		super(parent, board, controller, connection);
		setSize(500, 500);
	}

}