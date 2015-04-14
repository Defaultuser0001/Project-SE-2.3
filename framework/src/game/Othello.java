package game;

import model.BoardModel;
import model.Player;

/**
 * Created by Gerard on 4/7/2015.
 */
public class Othello extends BoardModel{

	public Othello() {
		super(8, 8);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean playMove(int move) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isValidMove(int move) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int isAWin() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
