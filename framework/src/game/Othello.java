package game;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isValidMove(int move) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int isAWin() {
		// TODO Auto-generated method stub
		/*
		 * vgm is -1 een pas sturen, kijk of er nog zetten mogelijk zijn, kijk of de lijst met mogelijke zetten leeg is, 
		 * kijk of we meer stenen hebben en of het veld al vol is
		 */
		return 0;
	}
	
	//TODO
	//een methode om een lijst te genereren die alle mogelijke zetten bevat | linked list van integers

	
}
