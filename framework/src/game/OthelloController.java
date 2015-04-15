package game;




import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import tools.ServerConnection;
import controller.BoardController;
import exceptions.ServerErrorException;

/**
 * Created by Gerard on 4/13/2015.
 */
public class OthelloController extends BoardController {

    public OthelloController(ServerConnection connection) {
        super(8, 8, "Othello", connection);
		model = new Othello();
		board = new OthelloBoard(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	int player = model.getActivePlayer();
		int move = Integer.parseInt(e.getActionCommand());
		if(model.playMove(move)){
			board.makeMove(board.PLAYER1, move);
			try{
				connection.sendCommand("MOVE " + move);
			} catch (ServerErrorException e1){
				e1.printStackTrace();
			}
		}
		else JOptionPane.showMessageDialog(board, "Illegal move");
		
		
		for(Integer i : model.possibleMoves(model.side)){
			System.out.println(i.toString());
		}
    }
}
