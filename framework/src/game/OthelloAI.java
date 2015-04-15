package game;

import java.util.ArrayList;



public class OthelloAI {

	private Rules rules;
	
	private static final int HUMAN        = 0; 
	private static final int COMPUTER     = 1; 
	public  static final int EMPTY        = 2;

	public  static final int HUMAN_WIN    = 0;
	public  static final int DRAW         = 1;
	public  static final int UNCLEAR      = 2;
	public  static final int COMPUTER_WIN = 3;

	private int[] positionStrategy = {
	99,  -8,  8,  6,  6,  8, -8,  99,
	-8, -24, -4, -3, -3, -4, -24, -8,
	 8,  -4,  7,  4,  4,  7,  -4,  8,
	 6,  -3,  4,  0,  0,  4,  -3,  6,
	 6,  -3,  4,  0,  0,  4,  -3,  6,
	 8,  -4,  7,  4,  4,  7,  -4,  8,
	-8, -24, -4, -3, -3, -4, -24, -8,
	99,  -8,  8,  6,  6,  8,  -8, 99 };
	
	public int chooseMove()
	{
	    Best best=chooseMove(COMPUTER);
	    return best.row*3+best.column;
	    //return 0;
    }
	
	// Find optimal move
		private Best chooseMove( int side )
		{
			int opp;              // The other side
			Best reply;           // Opponent's best reply
			int simpleEval;       // Result of an immediate evaluation
			int bestRow = 0;
			int bestColumn = 0;
			int value;

			if( ( simpleEval = positionValue( ) ) != UNCLEAR )
				return new Best( simpleEval );

			if (side == HUMAN) opp = COMPUTER;
			else opp = HUMAN;
			

			// TODO: implementeren m.b.v. recursie/backtracking
			
			
			int i = 0;
			while (!squareIsEmpty(i/3, i%3)){
				i++;
			}
			
			place(i/3, i%3, side);
			reply = chooseMove(opp);
			value = reply.val;
			bestRow = i/3;
			bestColumn = i%3;
			place(i/3, i%3, EMPTY);
			i++;
			
			while (i < 9){
				if (squareIsEmpty(i/3, i%3)){
					place(i/3, i%3, side);
					reply = chooseMove(opp);
					if((value < reply.val && side == COMPUTER) || (value > reply.val && side == HUMAN)){
						value = reply.val;
						bestRow = i/3;
						bestColumn = i%3;
					}
					place(i/3, i%3, EMPTY);
				}
				i++;
			}
			
			return new Best(value, bestRow, bestColumn);
	
			class Best
		    {
		       int row;
		       int column;
		       int val;

		       public Best( int v )
		         { this( v, 0, 0 ); }
		      
		       public Best( int v, int r, int c )
		        { val = v; row = r; column = c; }
		    } 
	
}}
