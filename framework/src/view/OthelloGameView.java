package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class OthelloGameView extends GameView{

	public OthelloGameView(JFrame parent, JPanel board) {
		super(parent, board);
		setSize(500, 500);
	}

}
