package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import model.BoardModel;
import model.Player;

/**
 * Created by Gerard on 4/7/2015.
 */
public class Othello extends BoardModel{
	
	private ArrayList<Integer> possibleMoves;
	private Player player;
	
	public Othello(Player player) {
		super(8, 8);
		this.player = player;
		board[27] = PLAYER2;
		board[28] = PLAYER1;
		board[35] = PLAYER1;
		board[36] = PLAYER2;
		side = PLAYER1;
		
	}

	@Override
	public boolean playMove(int move) {
			if (isValidMove(move)){
				flipTiles(getTilesToFlip(move));
				board[move] = side;
				flipSide();
				
				return true;
			} else {
				return false;
			}
	}
	
	public void flipTiles(ArrayList<Integer> tilesToFlip){
		for (Integer integer : tilesToFlip) {
			if(board[integer] == getOpponent(getActivePlayer())){
				board[integer] = getActivePlayer();
			}
		}
	}

	@Override
	protected boolean isValidMove(int move) {
		return getPossibleMoves().contains(move);
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
		if (boardIsFull()){
		if (hasWon(client)) return WIN;
		if (hasWon(opponent)) return LOSS;
		return DRAW;
		}
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
	
	public int getPossessedTiles(int player){
		int tiles = 0;
		for(Integer i : board){
			if(board[i] == player){
				tiles++;
			}
		}
		return tiles;
	}
	
	public boolean hasWon(ArrayList<Integer> player){
		
		return false;	
	}
	
	private HashMap<Integer, ArrayList<Integer>> findPossibleMoves(int possition, int stappen , ArrayList<Integer> possiblemoves, int player, int first){
		int posSide = player;
		ArrayList<Integer> moves = possiblemoves;
		HashMap<Integer , ArrayList<Integer>> flippableMoves = new HashMap<Integer, ArrayList<Integer>>();
		
		if(first == 1) {
			return findPossibleMoves(possition + stappen, stappen, moves, posSide, 2);
		}
		
		if (possition >= 0 && possition < 64){
		if (first == 2 && board[possition] == EMPTY) {
			return null;
		} 
		if (board[possition] == EMPTY && moves.size() > 0) {
			// stop conditie: 
			// hij voegt de flippable moves toe met een positie er bij
			flippableMoves.put(possition, moves);
			return flippableMoves;
		}
		if(((possition / 8) >= 0 && (possition / 8) <= 7 && (possition % 8) <= 7 && (possition % 8) >= 0 && stappen % 8 == 0) 
				|| ((possition / 8) > 0 && (possition / 8) < 7 && (possition % 8) < 7 && (possition % 8) > 0)
				|| ((possition / 8 == 7 || possition / 8 == 0) && (possition % 8) < 7 && (possition % 8) > 0 && (stappen == 1 || stappen == -1))){
			//als de possitie gelijk is aan die is meegegeven (hij is dus veranderd als dit niet de eerste recursie is)
			if(board[possition] == getOpponent(posSide)){
				//als de move nog niet in de moves array voeg m toe.
				if(!moves.contains(possition)){
					moves.add(possition);
				}
			} else return null;
			// daarna check volgende positie.
			first++;
			return findPossibleMoves(possition + stappen, stappen, moves, posSide, first);
		}
		}
		/*
		//Wanneer er al een steen is gecontrolleer en de steen is niet de zelfde als de initieele steen doe dit:
		if (first == false && board[possition] == getOpponent(posSide) ) {
			//flippableMoves.put(possition, moves);
			return null;
		//Wanneer de possitie leeg is en er al iets in de moves array zit doe dit:
		} else if (board[possition] == EMPTY && moves.size() > 0) {
			// stop conditie: 
			// hij voegt de flippable moves toe met een positie er bij
			flippableMoves.put(possition, moves);
			return flippableMoves;
		// Wanneer het de eerste steen is die recursief gecontroleerd wordt flip de side om
		} else if (first) {
			posSide = getOpponent(posSide);
		}
		// Als de possitie binnen het veld ligt 
		if((possition / 8) > 0 && (possition / 8) < 7 && (possition % 8) < 7 && (possition % 8) > 0){
			//als de possitie gelijk is aan die is meegegeven (hij is dus veranderd als dit niet de eerste recursie is)
			if(board[possition] == posSide && board[possition] != EMPTY){
				//als de move nog niet in de moves array voeg m toe.
				if(!moves.contains(possition)){
					moves.add(possition);
				}
			}
			// daarna check volgende positie.
			return findPossibleMoves(possition + stappen, stappen, moves, posSide, false);
		}*/
		return null;
	}
	
	public int getOpponent(int side){
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
				temp = findPossibleMoves(count, -9, new ArrayList<Integer>(), side, 1);
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
				
				temp = findPossibleMoves(count, -8, new ArrayList<Integer>(), side, 1);
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
				
				temp = findPossibleMoves(count, -7, new ArrayList<Integer>(), side, 1);
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
				
				temp = findPossibleMoves(count, -1, new ArrayList<Integer>(), side, 1);
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
				
				temp = findPossibleMoves(count, 1, new ArrayList<Integer>(), side, 1);
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
				
				temp = findPossibleMoves(count, 7, new ArrayList<Integer>(), side, 1);
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
				
				temp = findPossibleMoves(count, 8, new ArrayList<Integer>(), side, 1);
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
				temp = findPossibleMoves(count, 9, new ArrayList<Integer>(), side, 1);
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
	public ArrayList<Integer> getTilesToFlip(int move){
		ArrayList<Integer> tilesToFlip = new ArrayList<Integer>();
		HashMap<Integer , ArrayList<Integer>> possiblemoves = possibleMoves(getActivePlayer());
		if(possiblemoves.get(move) != null)
			tilesToFlip = possiblemoves.get(move);
		
		return tilesToFlip;
	}
	
	public ArrayList<Integer> getPossibleMoves(){
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		for (Entry<Integer, ArrayList<Integer>> entry : possibleMoves(getActivePlayer()).entrySet()) {
			possibleMoves.add(entry.getKey());
		}
		return possibleMoves;
	}
	
	public void playEmpty(int i) {
		board[i] = EMPTY;
		flipSide();
	}
	
	@Override
	public void setActivePlayer(boolean b) {
		this.myTurn = b;
		if(player.isAI() && isMyTurn()){
			OthelloAI ai = new OthelloAI(this);
			int move = ai.chooseMove();
			System.out.println("Sent move" + move);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			playMove(move);
			processAction(move);
			
		}
	}
	@Override
	protected void processAction(int move) {
		for(ActionListener listener : listeners){
			listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Integer.toString(move)));
		}
	}
}