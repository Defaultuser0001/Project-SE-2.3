package model;

/**
 * Created by Gerard on 4/6/2015.
 */
public abstract class Player {
	public Stats stats;
	private String name;

	public Player(String name){
		this.name = name;
    }

	public String getName(){
		return name;
	}

}
