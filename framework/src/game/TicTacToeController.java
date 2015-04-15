package game;



import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

import tools.ServerConnection;

import controller.BoardController;
import exceptions.ServerErrorException;

public class TicTacToeController extends BoardController{

	/**
	 * Constructor
	 * 
	 * Creates new instances of both the model and view required for a game of tic-tac-toe
	 */
	public TicTacToeController(ServerConnection connection) {
		super(3, 3, "TicTacToe", connection);
		model = new TicTacToe();
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
		if(model.isMyTurn()){
			if(model.playMove(move)) {
				board.makeMove(player, move);
				timeView.reset();
				try {
					connection.sendCommand("MOVE " + move);
					
				} catch (ServerErrorException e1) {
					e1.printStackTrace();
				}
			}
			else JOptionPane.showMessageDialog(board, "Illegal move");
		} else JOptionPane.showMessageDialog(board, "Not your turn");
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
