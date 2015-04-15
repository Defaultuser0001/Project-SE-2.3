package model;

import java.util.ArrayList;


public abstract class BoardModel {

	protected static final int EMPTY = 0;
	protected static final int PLAYER1 = 1;
	protected static final int PLAYER2 = 2;

	public static final int WIN = 1;
	public static final int LOSS = -1;
	public static final int DRAW = 0;
	public static final int UNCLEAR = 2;
	
	protected int[] board;
	public int side;
	private boolean myTurn = false;
	
	protected Player player1;
	protected Player player2;
	
	public BoardModel(int width, int height){
		board = new int[width*height];
		
		for (int i = 0; i < width; i++) {
			board[i] = EMPTY;
		}
	}
	
	public abstract boolean playMove(int move);
	
	protected abstract boolean isValidMove(int move);
	
	/**
	 * returns wether it is a Win, Loss, or a draw from the perspective of this client
	 * @return -1 == loss, 0 == draw, 1 == win
	 */
	public abstract int isAWin();

	public int getActivePlayer() {
		return side;
	}
	
	protected void flipSide() {
		if (side == PLAYER1) side = PLAYER2;
		else side = PLAYER1;
	}

	public void setActivePlayer(boolean b) {
		this.myTurn = b;
		
	}

	public boolean isMyTurn() {
		return myTurn;
	}

	public abstract ArrayList<Integer> possibleMoves(int side);
}

