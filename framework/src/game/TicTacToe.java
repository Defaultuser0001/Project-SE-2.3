package game;

import model.Player;

/**
 * Created by Gerard on 4/7/2015.
 */
public class TicTacToe extends AbstractGame{

	public TicTacToe(Player p1, Player p2) {
		super(p1, p2);
	}

	@Override
	boolean isAWin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean isADraw() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean movePossible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	void chooseMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	boolean moveValid() {
		// TODO Auto-generated method stub
		return false;
	}
}
