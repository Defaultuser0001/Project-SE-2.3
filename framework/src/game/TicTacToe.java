package game;

import exceptions.ServerErrorException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.BoardModel;
import model.Player;

/**
 * Created by Gerard on 4/7/2015.
 */
public class TicTacToe extends BoardModel {

	private Player player;
	
	public TicTacToe(Player player) {
		super(3, 3);
		side = PLAYER1;
		this.player = player;
	}
	@Override
	public boolean playMove(int move) {
		if (getActivePlayer() == side) {
			if (isValidMove(move)) {
				board[move] = side;
				flipSide();
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	protected boolean isValidMove(int move) {
		return board[move] == EMPTY;
	}

	@Override
	public int isAWin() {
		ArrayList<Integer> client = new ArrayList<Integer>();
		ArrayList<Integer> opponent = new ArrayList<Integer>();
		final int YOU = getActivePlayer();
		final int OPP = getOpponent(YOU);
		for (int i = 0; i < 9; i++) {
			if(board[i]==YOU)
				client.add(i);
			else if(board[i]==OPP)
				opponent.add(i);
		}

		if (hasWon(client))
			return WIN;
		if (hasWon(opponent))
			return LOSS;
		if (boardIsFull())
			return DRAW;
		return UNCLEAR;
	}

	public int getOpponent(int side){
		if(side == PLAYER1) {
			return PLAYER2;
		} else {
			return PLAYER1;
		}
	}
	/**
	 * Checks wether the board is full
	 * 
	 * @return wether board is full
	 */
	private boolean boardIsFull() {
		for (int i = 0; i < 9; i++) {
			if (board[i] == EMPTY) {
				return false;
			}
		}
		return true;
	}

	private boolean hasWon(ArrayList<Integer> player) {
		int a = 0;
		int b = 1;
		int c = 2;

		if (player.size() > 2) {

			while (a != player.size() - 2) {
				if (player.get(a) / 3 == player.get(b) / 3
						&& player.get(a) / 3 == player.get(c) / 3)
					return true;

				if (player.get(a) % 3 == player.get(b) % 3
						&& player.get(a) % 3 == player.get(c) % 3)
					return true;

				if ((player.get(a) == 0 && player.get(b) == 4 && player.get(c) == 8)
						|| (player.get(a) == 2 && player.get(b) == 4 && player
								.get(c) == 6))
					return true;

				if (c != player.size() - 1)
					c++;
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
	public HashMap<Integer, ArrayList<Integer>> possibleMoves(int side) {
		return null;
	}
	
	public void playEmpty(int i) {
		board[i] = EMPTY;
		flipSide();
	}
	
	@Override
	public void setActivePlayer(boolean b) {
		this.myTurn = b;
		if(player.isAI() && isMyTurn()){
			TicTacToeAI ai = new TicTacToeAI(this);
			int move = ai.chooseMove();
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
