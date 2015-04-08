package model;

public class Stats {
	private int score;
	private int movesMade;
	private boolean won;
	
	public Stats(){
		score =	0;
		movesMade= 0;
		boolean won= false;
	}
	
	public void incrementScore(){
		setScore(getScore()+1);
	}
	
	public void decrementScore(){
		setScore(getScore()-1);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getMovesMade() {
		return movesMade;
	}

	public void setMovesMade(int movesMade) {
		this.movesMade = movesMade;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	
}
