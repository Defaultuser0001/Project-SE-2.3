package model;

import java.util.ArrayList;


public class AI {

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
	
	
}
