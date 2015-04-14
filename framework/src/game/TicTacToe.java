package game;

import model.BoardModel;
import model.Player;

/**
 * Created by Gerard on 4/7/2015.
 */
public class TicTacToe extends BoardModel{


	public TicTacToe(int starting_player) {
		super(starting_player, 3, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean playMove(int move) {
		if (isValidMove(move)){
			board[move] = side;
			flipSide();
			return true;
		} else return false;
	}

	@Override
	protected boolean isValidMove(int move) {
		return board[move] == EMPTY;
	}

	@Override
	public int isAWin(int side) {
		// TODO Auto-generated method stub
		return 0;
	}
}
