package game;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
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
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		for (Entry<Integer, ArrayList<Integer>> entry : model.possibleMoves(
				model.getActivePlayer()).entrySet()) {
			System.out.println(entry.getKey());
			if (entry.getKey() != null) {
				possibleMoves.add(entry.getKey());
			}
		}
		OthelloBoard board1 = (OthelloBoard) board;
		board1.hilightMoves(possibleMoves);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OthelloBoard board1 = (OthelloBoard) board;
		Othello model1 = (Othello) model;
		board1.resetBoardBg();
		int player = model.getActivePlayer();
		int move = Integer.parseInt(e.getActionCommand());
		if (model.playMove(move)) {
			board1.updateBoard(model.getBoard());
			try {
				connection.sendCommand("MOVE " + move);
				
			} catch (ServerErrorException e1) {
				e1.printStackTrace();
			}
		} else
			JOptionPane.showMessageDialog(board, "Illegal move");
		for (Integer i : model1.getTilesToFlip(model1.getPossibleMoves().get(0))) {
			System.out.println(i);
		}
		board1.hilightMoves(model1.getPossibleMoves());
	}

}
