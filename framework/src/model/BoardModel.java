package model;

public class BoardModel {

	private static final int PLAYER1 = 1;
	private static final int PLAYER2 = 2;
	private static final int EMPTY = 0;
	
	
	private int[][] board;
	
	public BoardModel(int width, int height){
		board = new int[width][height];
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++){
				board[i][j] = EMPTY;
			}
		}
	}
	
	public void playMove(int side, int x, int y){
		if (side == PLAYER1 || side == PLAYER2) {
			board[x][y] = side;
		}
	}
}
