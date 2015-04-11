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
    
    public AbstractGame(Player p1, Player p2){
    	this.p1 = p1;
    	this.p2 = p2;
    }    
    
    abstract boolean isAWin();
    
    abstract boolean isADraw();
    
    abstract boolean movePossible();
    
    abstract void chooseMove();
    
    abstract boolean moveValid();
    

}
