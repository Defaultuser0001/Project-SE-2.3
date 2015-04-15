package game;

import java.util.ArrayList;

import model.BoardModel;
import model.Player;

/**
 * Created by Gerard on 4/7/2015.
 */
public class Othello extends BoardModel{

	public Othello() {
		super(8, 8);
		board[27] = 1;
		board[26] = 2;
		board[25] = 2;
		board[24] = 0;
		
		// TODO Auto-generated constructor stub
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
		return true;
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
	
	
/*	findLeft(int 8, player, int opponentpossitions,  boolean first){
		player = player;
			if(position == empty && opponent positions > 0){
			add possible move;
			return true;
		}
		Else if( first ){
			Player = Getopponent(player);	
		}
		Else if(er zijn plaatsen links ){
			If(position = player){
			Opponentpossitions++;
		}
			findLeft( “ een links van 8”, player, false, false)
		}
		Return false;
		}
*/
	private int findPossibleMovesUpLeft(int possition, int opponentPossitions, int player, boolean first){
		int posSide = player;
		if (first == false && board[possition] == getOpponent(posSide) ) {
			return -9;
		} else if (board[possition] == EMPTY && opponentPossitions > 0) {
			return possition;
		} else {
			posSide = getOpponent(posSide);
		}
		if((possition % 8) > 0 && (possition / 8) >0){
			if(board[possition] == posSide){
				opponentPossitions++;
			}
			findPossibleMovesLeft(possition -9 , opponentPossitions, posSide, false);
		}
		return -9;
	}
	
	private int findPossibleUp(int possition, int opponentPossitions, int player, boolean first){
		int posSide = player;
		if (first == false && board[possition] == getOpponent(posSide) ) {
			return -8;
		} else if (board[possition] == EMPTY && opponentPossitions > 0) {
			return possition;
		} else {
			posSide = getOpponent(posSide);
		}
		if((possition / 8) > 0){
			if(board[possition] == posSide){
				opponentPossitions++;
			}
			findPossibleMovesLeft(possition -8 , opponentPossitions, posSide, false);
		}
		return -8;
	}
	
	private int findPossibleUpRight(int possition, int opponentPossitions, int player, boolean first){
		int posSide = player;
		if (first == false && board[possition] == getOpponent(posSide) ) {
			return -7;
		} else if (board[possition] == EMPTY && opponentPossitions > 0) {
			return possition;
		} else {
			posSide = getOpponent(posSide);
		}
		if((possition / 8) > 0 && (possition % 8) < 7){
			if(board[possition] == posSide){
				opponentPossitions++;
			}
			findPossibleMovesLeft(possition -7 , opponentPossitions, posSide, false);
		}
		return -7;
	}
	
	private int findPossibleMovesLeft(int possition, int opponentPossitions, int player, boolean first){
		int posSide = player;
		if (first == false && board[possition] == getOpponent(posSide) ) {
			return -1;
		} else if (board[possition] == EMPTY && opponentPossitions > 0) {
			return possition;
		} else {
			posSide = getOpponent(posSide);
		}
		if((possition % 8) > 0){
			if(board[possition] == posSide){
				opponentPossitions++;
			}
			findPossibleMovesLeft(possition -1 , opponentPossitions, posSide, false);
		}
		return -1;
	}
	

	private int findPossibleMovesRight(int possition, int opponentPossitions, int player, boolean first){
		int posSide = player;
		if (first == false && board[possition] == getOpponent(posSide) ) {
			return +1;
		} else if (board[possition] == EMPTY && opponentPossitions > 0) {
			return possition;
		} else {
			posSide = getOpponent(posSide);
		}
		if((possition % 8) < 7){
			if(board[possition] == posSide){
				opponentPossitions++;
			}
			findPossibleMovesLeft(possition +1 , opponentPossitions, posSide, false);
		}
		return +1;
	}
	
	private int findPossibleMovesDownLeft(int possition, int opponentPossitions, int player, boolean first){
		int posSide = player;
		if (first == false && board[possition] == getOpponent(posSide) ) {
			return +7;
		} else if (board[possition] == EMPTY && opponentPossitions > 0) {
			return possition;
		} else {
			posSide = getOpponent(posSide);
		}
		if((possition % 8) > 0 && (possition / 8) < 7){
			if(board[possition] == posSide){
				opponentPossitions++;
			}
			findPossibleMovesLeft(possition +7 , opponentPossitions, posSide, false);
		}
		return +7;
	}
	
	private int findPossibleMovesDown(int possition, int opponentPossitions, int player, boolean first){
		int posSide = player;
		if (first == false && board[possition] == getOpponent(posSide) ) {
			return +1;
		} else if (board[possition] == EMPTY && opponentPossitions > 0) {
			return possition;
		} else {
			posSide = getOpponent(posSide);
		}
		if((possition / 8) < 7){
			if(board[possition] == posSide){
				opponentPossitions++;
			}
			findPossibleMovesLeft(possition +1 , opponentPossitions, posSide, false);
		}
		return +1;
	}
	
	private int findPossibleMovesDownRight(int possition, int opponentPossitions, int player, boolean first){
		int posSide = player;
		if (first == false && board[possition] == getOpponent(posSide) ) {
			return +1;
		} else if (board[possition] == EMPTY && opponentPossitions > 0) {
			return possition;
		} else {
			posSide = getOpponent(posSide);
		}
		if((possition / 8) < 7 && (possition % 8) < 7){
			if(board[possition] == posSide){
				opponentPossitions++;
			}
			findPossibleMovesLeft(possition +1 , opponentPossitions, posSide, false);
		}
		return +1;
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
		for(int i : board){
			if(i == side){
				int j = findPossibleMovesLeft(27,0,1,true);
				if(j >= 0){
					moves.add(j);
				}
			}
		}
		return moves;
	}
	/*
	// modulo = horizontal
	// divide = vertical
	public ArrayList<Integer> checkHorizontal(int pos){
		ArrayList<Integer> moves = new ArrayList<Integer>();
		int i = pos / 8;
		int emptyPositions = 0;
		int p1Positions = 0;
		int p2Positions = 0;
		for(int j = (i * 8); j < i*8+7; j++){
			switch(board[j]){
			case 0:
				emptyPositions++;
			break;
			case 1:
				p1Positions++;
			break;
			case 2:
				p2Positions++;
			break;	
			}		
		
			moves.add(j);
		}
		if(emptyPositions == 0)
			return null;
		else if(p1Positions == 7 || p2Positions == 7){
			return null;
		}
		if()
			
		
		return moves;
	}	*/
	
	public ArrayList<Integer> checkVertical(int pos){
		ArrayList<Integer> moves = new ArrayList<Integer>();
		int i = pos % 8;
		for(int j = i; j < i +7*8; j+=8){
			if(board[j] == EMPTY){
				
				moves.add(j);
			}
		}
		
		return moves;
	}
	//een methode om een lijst te genereren die alle mogelijke zettenvat | linked list van integers
	
}
