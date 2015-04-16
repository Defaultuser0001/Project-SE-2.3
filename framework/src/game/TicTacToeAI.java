package game;

import java.util.ArrayList;



public class TicTacToeAI {
	
	public  static final int EMPTY       = 0;
	private static final int PLAYER1     = 1; 
	private static final int PLAYER2     = 2; 
	

	public  static final int HUMAN_WIN    = 0;
	public  static final int DRAW         = 1;
	public  static final int UNCLEAR      = 2;
	public  static final int COMPUTER_WIN = 3;
	
	private TicTacToe ttt;
	
	public TicTacToeAI(TicTacToe ttt){
		this.ttt = ttt;
	}

	private int[] positionStrategy = {
	99,  -8,  8,  6,  6,  8, -8,  99,
	-8, -24, -4, -3, -3, -4, -24, -8,
	 8,  -4,  7,  4,  4,  7,  -4,  8,
	 6,  -3,  4,  0,  0,  4,  -3,  6,
	 6,  -3,  4,  0,  0,  4,  -3,  6,
	 8,  -4,  7,  4,  4,  7,  -4,  8,
	-8, -24, -4, -3, -3, -4, -24, -8,
	99,  -8,  8,  6,  6,  8,  -8, 99 };
	
	public int chooseMove() {
		Best best = chooseMove(ttt.getActivePlayer());
		return best.pos;
		// return 0;
	}

	// Find optimal move
	private Best chooseMove(int side) {
		int opp; // The other side
		Best reply; // Opponent's best reply
		int simpleEval; // Result of an immediate evaluation
		int bestMove;
		int value;

		if ((simpleEval = ttt.isAWin()) != UNCLEAR)
			return new Best(simpleEval);


		// TODO: implementeren m.b.v. recursie/backtracking
		
		
		int i = 0;
		while (i < 8 && ttt.getBoard()[i] != EMPTY){
			i++;
		}
		
		ttt.playMove(i);
		reply = chooseMove(ttt.getOpponent(ttt.getActivePlayer()));
		value = reply.val;
		bestMove = i;
		ttt.playEmpty(i);//empty;
		i++;
		
		
		while( i < 9) {
			if(ttt.getBoard()[i] == EMPTY){
				ttt.playMove(i);
				reply = chooseMove(ttt.getOpponent(ttt.getActivePlayer()));
				if ((value < reply.val && side == ttt.getOpponent(ttt.getActivePlayer()))
						|| (value > reply.val && side == ttt.getActivePlayer())) {
					value = reply.val;
					bestMove = i;
				}
				ttt.playEmpty(i);//empty
			}
			i++;
		}
		

		return new Best(value, bestMove);

	}

	class Best {
		int pos;
		int val;

		public Best(int v) {
			this(v, 0);
		}

		public Best(int v, int p) {
			val = v;
			pos = p;
		}
	}
}
