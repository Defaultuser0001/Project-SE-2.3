package model;

public class Human extends Player{
	
	public Human(String name){
		super(name);
		System.out.println("[Human]: " + name + " has logged in.");
	}


}
