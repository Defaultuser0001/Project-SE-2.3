package game;



import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import model.Player;
import tools.ServerConnection;
import controller.BoardController;
import exceptions.ServerErrorException;

public class TicTacToeController extends BoardController{

	private Player player;

	/**
	 * Constructor
	 * 
	 * Creates new instances of both the model and view required for a game of tic-tac-toe
	 */
	public TicTacToeController(ServerConnection connection, Player player) {
		super(3, 3, "TicTacToe", connection);
		this.player = player;
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
	
	@Override
	public void playMove(int move) {
		model.playMove(move);
		board.updateBoard(model.getBoard());
		timeView.reset();
		if(player.isAI()){
			TicTacToeAI ai = new TicTacToeAI((TicTacToe) model);
			move = ai.chooseMove();
			model.playMove(move);
			board.updateBoard(model.getBoard());
			timeView.reset();
			try {
				connection.sendCommand("MOVE " + move);
			} catch (ServerErrorException e1) {
				e1.printStackTrace();
			}
		}
	}

}
