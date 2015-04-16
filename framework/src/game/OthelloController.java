package game;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import model.Player;
import tools.ServerConnection;
import controller.BoardController;
import exceptions.ServerErrorException;

/**
 * Created by Gerard on 4/13/2015.
 */
public class OthelloController extends BoardController {
	
	private Player player;

	public OthelloController(ServerConnection connection, Player player) {
		super(8, 8, "Othello", connection);
		this.player = player;
		model = new Othello(player);
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
		int player = model.getActivePlayer();
		int move = Integer.parseInt(e.getActionCommand());
		if(model.isMyTurn()){
			if (model.playMove(move)) {
				board1.updateBoard(model.getBoard());
				try {
					connection.sendCommand("MOVE " + move);
					board1.resetBoardBg();
				} catch (ServerErrorException e1) {
					e1.printStackTrace();
				}
			} else
				JOptionPane.showMessageDialog(board, "Illegal move");
		} else JOptionPane.showMessageDialog(board, "Not your turn");
	}
	

	@Override
	public void playMove(int move) {
		model.playMove(move);
		board.updateBoard(model.getBoard());
		timeView.reset();
		OthelloBoard board1 = (OthelloBoard) board;
		board1.resetBoardBg();
		
			ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
			for (Entry<Integer, ArrayList<Integer>> entry : model.possibleMoves(
					model.getActivePlayer()).entrySet()) {
				System.out.println(entry.getKey());
				if (entry.getKey() != null) {
					possibleMoves.add(entry.getKey());
				}
			}

			board1.hilightMoves(possibleMoves);
		
	}

}
