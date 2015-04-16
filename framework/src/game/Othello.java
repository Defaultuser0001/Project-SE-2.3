package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
		flipSide();
		flipSide();
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
		for (Entry<Integer, ArrayList<Integer>> entry : possibleMoves(getActivePlayer()).entrySet())
		{
		  if(entry.getValue() != null && entry.getKey() == move){
			  return true;
		  }
		}
		return false;
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
		} else if (board[possition] == EMPTY && moves.size() > 0) {
			flippableMoves.put(possition, moves);
			return flippableMoves;
		} else if (first) {
			posSide = getOpponent(posSide);
		}
		if((possition / 8) > 0 && (possition / 8) < 7 && (possition % 8) < 7 && (possition % 8) > 0){
			if(board[possition] == posSide){
				if(!moves.contains(possition)){
					moves.add(possition);
				}
			}
			return findPossibleMoves(possition + stappen, stappen, moves, posSide, false);
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
	
	public HashMap<Integer, ArrayList<Integer>> possibleMoves(int side){
		HashMap<Integer , ArrayList<Integer>> possiblemoves = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer , ArrayList<Integer>> temp = new HashMap<Integer, ArrayList<Integer>>();
		int count = 0;
		for(int i : board){
			if(i == side){
				temp = findPossibleMoves(count, -9, new ArrayList<Integer>(), side, true);
				if(temp != null){
					for(Entry<Integer, ArrayList<Integer>> entry : temp.entrySet()){
						if(possiblemoves.containsKey(entry.getKey())){
							ArrayList<Integer> test = possiblemoves.get(entry.getKey());
							test.addAll(entry.getValue());
							possiblemoves.put(entry.getKey(), test);
						}else{
							possiblemoves.put(entry.getKey(),entry.getValue());
						}
					}
				}
				
				temp = findPossibleMoves(count, -8, new ArrayList<Integer>(), side, true);
				if(temp != null){
					for(Entry<Integer, ArrayList<Integer>> entry : temp.entrySet()){
						if(possiblemoves.containsKey(entry.getKey())){
							ArrayList<Integer> test = possiblemoves.get(entry.getKey());
							test.addAll(entry.getValue());
							possiblemoves.put(entry.getKey(), test);
						}else{
							possiblemoves.put(entry.getKey(),entry.getValue());
						}
					}
				}
				
				temp = findPossibleMoves(count, -7, new ArrayList<Integer>(), side, true);
				if(temp != null){
					for(Entry<Integer, ArrayList<Integer>> entry : temp.entrySet()){
						if(possiblemoves.containsKey(entry.getKey())){
							ArrayList<Integer> test = possiblemoves.get(entry.getKey());
							test.addAll(entry.getValue());
							possiblemoves.put(entry.getKey(), test);
						}else{
							possiblemoves.put(entry.getKey(),entry.getValue());
						}
					}
				}
				
				temp = findPossibleMoves(count, -1, new ArrayList<Integer>(), side, true);
				if(temp != null){
					for(Entry<Integer, ArrayList<Integer>> entry : temp.entrySet()){
						if(possiblemoves.containsKey(entry.getKey())){
							ArrayList<Integer> test = possiblemoves.get(entry.getKey());
							test.addAll(entry.getValue());
							possiblemoves.put(entry.getKey(), test);
						}else{
							possiblemoves.put(entry.getKey(),entry.getValue());
						}
					}
				}
				
				temp = findPossibleMoves(count, 1, new ArrayList<Integer>(), side, true);
				if(temp != null){
					for(Entry<Integer, ArrayList<Integer>> entry : temp.entrySet()){
						if(possiblemoves.containsKey(entry.getKey())){
							ArrayList<Integer> test = possiblemoves.get(entry.getKey());
							test.addAll(entry.getValue());
							possiblemoves.put(entry.getKey(), test);
						}else{
							possiblemoves.put(entry.getKey(),entry.getValue());
						}
					}
				}
				
				temp = findPossibleMoves(count, 7, new ArrayList<Integer>(), side, true);
				if(temp != null){
					for(Entry<Integer, ArrayList<Integer>> entry : temp.entrySet()){
						if(possiblemoves.containsKey(entry.getKey())){
							ArrayList<Integer> test = possiblemoves.get(entry.getKey());
							test.addAll(entry.getValue());
							possiblemoves.put(entry.getKey(), test);
						}else{
							possiblemoves.put(entry.getKey(),entry.getValue());
						}
					}
				}
				
				temp = findPossibleMoves(count, 8, new ArrayList<Integer>(), side, true);
				if(temp != null){
					for(Entry<Integer, ArrayList<Integer>> entry : temp.entrySet()){
						if(possiblemoves.containsKey(entry.getKey())){
							ArrayList<Integer> test = possiblemoves.get(entry.getKey());
							test.addAll(entry.getValue());
							possiblemoves.put(entry.getKey(), test);
						}else{
							possiblemoves.put(entry.getKey(),entry.getValue());
						}
					}
				}
				temp = findPossibleMoves(count, 9, new ArrayList<Integer>(), side, true);
				if(temp != null){
					for(Entry<Integer, ArrayList<Integer>> entry : temp.entrySet()){
						if(possiblemoves.containsKey(entry.getKey())){
							ArrayList<Integer> test = possiblemoves.get(entry.getKey());
							test.addAll(entry.getValue());
							possiblemoves.put(entry.getKey(), test);
						}else{
							possiblemoves.put(entry.getKey(),entry.getValue());
						}
					}
				}
				
				
			}
			count++;
		}
		return possiblemoves;
	}
}