package game;

import java.util.ArrayList;
import java.util.HashMap;

import model.BoardModel;
import model.Player;

/**
 * Created by Gerard on 4/7/2015.
 */
public class Othello extends BoardModel{
	
	public Othello() {
		super(8, 8);
		board[27] = PLAYER1;
		board[28] = PLAYER2;
		board[35] = PLAYER2;
		board[36] = PLAYER1;
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
		return possibleMoves(getActivePlayer()).contains(move);
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
		//if (boardIsFull()) return DRAW;
		return UNCLEAR;
		/*
		 * vgm is -1 een pas sturen, kijk of er nog zetten mogelijk zijn, kijk of de lijst met mogelijke zetten leeg is, 
		 * kijk of we meer stenen hebben en of het veld al vol is
		 */
		
		//return 0;
	}
	
	/**
	 * Checks wether the board is full
	 * @return wether board is full
	 */
	private boolean boardIsFull() {
		for(int i = 0; i<63; i++){
			if(board[i] == EMPTY){
				return false;
			}
		}return true;
	}
	
	/**
	 * Checks wether the player has any tiles
	 * @return of een player nog een tile heeft en dus uberhaupt een zet kan doen
	 */
	private boolean playerHasTiles(int PLAYER) {
		for(int i : board){
			if(i == PLAYER){
				return true;
			}
		}return false;
	}
	
	public boolean hasWon(ArrayList<Integer> player){
		
		return false;	
	}
	
	private HashMap<Integer, ArrayList<Integer>> findPossibleMoves(int possition, int stappen , ArrayList<Integer> possiblemoves, int player, boolean first){
		int posSide = player;
		ArrayList<Integer> moves = possiblemoves;
		HashMap<Integer , ArrayList<Integer>> flippableMoves = new HashMap<Integer, ArrayList<Integer>>();
		if (first == false && board[possition] == getOpponent(posSide) ) {
			return null;
		} else if (board[possition] == EMPTY && !moves.isEmpty()) {
			flippableMoves.put(possition, moves);
			return flippableMoves;
		} else if (first) {
			posSide = getOpponent(posSide);
		}
		if((possition / 8) > 0 && (possition / 8) < 7 && (possition % 8) < 7 && (possition % 8) > 0){
			if(board[possition] == posSide){
				moves.add(possition);
			}
			return findPossibleMoves(possition, stappen, moves, posSide, false);
		}
		return null;
	}
	
	
	
	private int getOpponent(int side){
		if(side == PLAYER1) {
			return PLAYER2;
		} else {
			return PLAYER1;
		}
	}
	
	public ArrayList<Integer> possibleMoves(int side){
		ArrayList<Integer> moves = new ArrayList<Integer>(); 
		int count = 0;
		for(int i : board){
			if(i == side){
/*				moves = findPossibleMove(findPossibleMovesUpLeft(count, 0, side, true), moves);
				moves = findPossibleMove(findPossibleMovesUp(count, 0, side, true), moves);
				moves = findPossibleMove(findPossibleMovesUpRight(count, 0, side, true), moves);
				moves = findPossibleMove(findPossibleMovesLeft(count, 0, side, true), moves);
				moves = findPossibleMove(findPossibleMovesRight(count, 0, side, true), moves);
				moves = findPossibleMove(findPossibleMovesDownLeft(count, 0, side, true), moves);
				moves = findPossibleMove(findPossibleMovesDown(count, 0, side, true), moves);
				moves = findPossibleMove(findPossibleMovesDownRight(count, 0, side, true), moves);		*/
			}
			count++;
		}
		return moves;
	}
}
