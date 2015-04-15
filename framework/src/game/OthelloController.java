package game;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Map.Entry;

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
		OthelloBoard board1 = (OthelloBoard) board;
		board1.resetBoardBg();
		int player = model.getActivePlayer();
		int move = Integer.parseInt(e.getActionCommand());
		if (model.isMyTurn()) {
			if (model.playMove(move)) {
				board.makeMove(model.getActivePlayer(), move);
				try {
					connection.sendCommand("MOVE " + move);
				} catch (ServerErrorException e1) {
					e1.printStackTrace();
				}
			} else
				JOptionPane.showMessageDialog(board, "Illegal move");
		} else
			JOptionPane.showMessageDialog(board, "Not your turn");
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		for (Entry<Integer, ArrayList<Integer>> entry : model.possibleMoves(
				model.getActivePlayer()).entrySet()) {
			possibleMoves.add(entry.getKey());
		}
		board1.hilightMoves(possibleMoves);

	}
}
