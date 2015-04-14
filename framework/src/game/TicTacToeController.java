package game;


import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

import controller.BoardController;

public class TicTacToeController extends BoardController{

	/**
	 * Constructor
	 * 
	 * Creates new instances of both the model and view required for a game of tic-tac-toe
	 */
	public TicTacToeController() {
		super(3, 3, "TicTacToe");
		model = new TicTacToe(1);
		board = new TicTacToeBoard(this);
	}

	/**
	 * When a tile is clicked in the view this method is called.
	 * This method sends the move to the model for checking and if legal processing it;
	 * if illegal a pop-up will appear notifying the player and the move won't be processed
	 * 
	 * TODO: server connection
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int player = model.getActivePlayer();
		int move = Integer.parseInt(e.getActionCommand());
		if(model.playMove(move))
			board.makeMove(player, move);
		else JOptionPane.showMessageDialog(board, "Illegal move");
		
		/*
		 * Following code within comments is for testing the win/loss/draw check
		 * 
		switch (model.isAWin()) {
		case BoardModel.WIN: JOptionPane.showMessageDialog(board, "You won!");
			break;
		case BoardModel.LOSS: JOptionPane.showMessageDialog(board, "You lost!");
			break;
		case BoardModel.DRAW: JOptionPane.showMessageDialog(board, "It's a draw!");
			break;
		}
		*/
	}

}
