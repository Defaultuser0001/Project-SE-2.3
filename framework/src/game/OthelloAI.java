package game;

import java.util.ArrayList;



public class OthelloAI {
	
	private static final int HUMAN        = 0; 
	private static final int COMPUTER     = 1; 
	public  static final int EMPTY        = 2;

	public  static final int HUMAN_WIN    = 0;
	public  static final int DRAW         = 1;
	public  static final int UNCLEAR      = 2;
	public  static final int COMPUTER_WIN = 3;
	
	private Othello othello;
	
	public OthelloAI(Othello othello){
		this.othello = othello;
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
		Best best = chooseMove(COMPUTER);
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

		if ((simpleEval = positionValue()) != UNCLEAR)
			return new Best(simpleEval);

		if (side == HUMAN)
			opp = COMPUTER;
		else
			opp = HUMAN;

		// TODO: implementeren m.b.v. recursie/backtracking
		
		
		for (Integer move : othello.getPossibleMoves()) {
			othello.playMove(move);
			reply = chooseMove(opp);
			if ((value < reply.val && side == COMPUTER)
					|| (value > reply.val && side == HUMAN)) {
				value = reply.val;
				bestMove = move;
			}
			othello.playMove(move);//empty
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
