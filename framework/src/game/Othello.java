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
	
	public boolean hasWon(ArrayList<Integer> player){
		
		return false;	
	}
	public ArrayList<Integer> possibleMoves(){
		ArrayList<Integer> player = new ArrayList<Integer>();
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for(int i : board){
			if(i == side){
				player.add(i);
			}
		}
		checkHorizontal(27);
		return moves;
	}
	// modulo = horizontal
	// divide = vertical
	public ArrayList<Integer> checkHorizontal(int pos){
		ArrayList<Integer> moves = new ArrayList<Integer>();
		int i = pos / 8;
		int emptyPositions = 0;
		int p1Possitions = 0;
		int p2Possitions = 0;
		for(int j = (i * 8); j < i*8+7; j++){
			if(board[j] == EMPTY)
				emptyPositions++;
			moves.add(j);
		}
		if(emptyPositions == 0)
			return null;
		else
			for(int move: moves){
				//TODO Remove moves that are not possible moves.
				
			}
		
		return moves;
	}	
	
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
