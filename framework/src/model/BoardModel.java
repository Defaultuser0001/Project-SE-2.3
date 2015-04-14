package model;


public abstract class BoardModel {

	protected static final int EMPTY = 0;
	protected static final int PLAYER1 = 1;
	protected static final int PLAYER2 = 2;
	
	protected int[] board;
	protected int side;
	
	public BoardModel(int side, int width, int height){
		board = new int[width*height];
		this.side = side;
		
		for (int i = 0; i < width; i++) {
			board[i] = EMPTY;
		}
	}
	
	public abstract boolean playMove(int move);
	
	protected abstract boolean isValidMove(int move);
	
	/**
	 * returns wether it is a Win, Loss, or a draw
	 * @return -1 == loss, 0 == draw, 1 == win
	 */
	public abstract int isAWin(int side);

	public int getActivePlayer() {
		return side;
	}
	
	protected void flipSide() {
		if (side == PLAYER1) side = PLAYER2;
		else side = PLAYER1;
	}
}
