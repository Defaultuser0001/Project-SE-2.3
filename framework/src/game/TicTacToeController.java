package game;


import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import controller.BoardController;

public class TicTacToeController extends BoardController{

	public TicTacToeController() {
		super(3, 3, "TicTacToe");
		model = new TicTacToe(1);
		board = new TicTacToeBoard(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int player = model.getActivePlayer();
		int move = Integer.parseInt(e.getActionCommand());
		if(model.playMove(move))
			board.makeMove(player, move);
		else JOptionPane.showMessageDialog(board, "Illegal move");
	}

}
