package game;




import java.awt.event.ActionEvent;

import controller.BoardController;

/**
 * Created by Gerard on 4/13/2015.
 */
public class OthelloController extends BoardController {

    public OthelloController() {
        super(8, 8, "Othello");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	OthelloBoard board = (OthelloBoard) e.getSource();
		
		int move = Integer.parseInt(e.getActionCommand());
		board.makeMove(board.PLAYER1, move);
		System.out.println(e.getActionCommand());
    }
}
