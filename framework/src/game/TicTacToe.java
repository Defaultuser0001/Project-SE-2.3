package game;

import java.util.ArrayList;

import model.BoardModel;

/**
 * Created by Gerard on 4/7/2015.
 */
public class TicTacToe extends BoardModel{

	public TicTacToe() {
		super(3, 3);
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
	public int isAWin() {
		ArrayList<Integer> client = new ArrayList<Integer>();
		ArrayList<Integer> opponent = new ArrayList<Integer>();
		
		for (int i = 0; i < 9; i++){
			switch (board[i]){
			case PLAYER1:
				client.add(i);
				break;
			case PLAYER2:
				opponent.add(i);
				break;
			}
		}
		
		if (hasWon(client)) return WIN;
		if (hasWon(opponent)) return LOSS;
		if (boardIsFull()) return DRAW;
		return UNCLEAR;
	}
	
	/**
	 * Checks wether the board is full
	 * @return wether board is full
	 */
	private boolean boardIsFull() {
		for(int i = 0; i<9; i++){
			if(board[i] == EMPTY){
				return false;
			}
		}return true;
	}
	
	private boolean hasWon(ArrayList<Integer> player) {
		int a = 0;
		int b = 1;
		int c = 2;
		
		if (player.size() > 2) {
			
			while (a != player.size() - 2) {
				if (	player.get(a)/3 == player.get(b)/3 && 
						player.get(a)/3 == player.get(c)/3) return true;
				
				if (	player.get(a)%3 == player.get(b)%3 && 
						player.get(a)%3 == player.get(c)%3) return true;
				
				if (	(player.get(a) == 0 && 
						player.get(b) == 4 &&
						player.get(c) == 8 ) || 
						(player.get(a) == 2 &&
						player.get(b) == 4 &&
						player.get(c) == 6)) return true;
				
				if (c != player.size() - 1) c++;
				else if (b != player.size() - 2) {
					b++;
					c = b + 1;
				} else {
					a++;
					b = a + 1;
					c = b + 1;
				}
			}
		}
		return false;
	}

	@Override
	public ArrayList<Integer> possibleMoves() {
		return null;
	}
	
	
}
