package model;

/**
 * Created by Gerard on 4/6/2015.
 */
public class Player {
	public Stats stats;
	private String name;
	private boolean isAI = false;

	public Player(String name){
		this.name = name;
    }

	public String getName(){
		return name;
	}
	
	public void setAI(boolean bool){
		this.isAI = bool;
	}
	
	public boolean isAI(){
		return isAI;
	}

}
