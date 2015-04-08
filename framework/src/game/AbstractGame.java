package game;

import model.Player;
import model.Stats;

/**
 * Created by Gerard on 4/7/2015.
 */
public abstract class AbstractGame {

	public AbstractBoard board;
	public Player p1;
	public Player p2;
    
    abstract void addPlayer();    
    
    abstract boolean isAWin();
    
    abstract boolean isADraw();
    
    abstract boolean movePossible();
    
    abstract void chooseMove();
    
    abstract boolean moveValid();
    

}
