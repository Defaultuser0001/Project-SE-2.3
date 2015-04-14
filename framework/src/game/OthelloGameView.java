package game;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.GameView;

public class OthelloGameView extends GameView{

	public OthelloGameView(JFrame parent, JPanel board, OthelloController controller) {
		super(parent, board, controller);
		setSize(500, 500);
	}

}
