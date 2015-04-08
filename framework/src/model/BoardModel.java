package model;


public abstract class BoardModel {

	private static final int EMPTY = 0;
	
	private int[][] board;
	private int side;
	
	public BoardModel(int side, int width, int height){
		board = new int[width][height];
		this.side = side;
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++){
				board[i][j] = EMPTY;
			}
		}
	}
	
	public abstract boolean playMove(int x, int y);
	
	protected abstract boolean isValidMove(int x, int y);
}
