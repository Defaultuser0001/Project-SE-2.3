package model;

public class Stats {
	private int score;
	private int movesMade;
	private boolean isAWin;
	
	public Stats(){
		score =	0;
		movesMade= 0;
		isAWin = false;
	}
	
	public void resetStats(){
		score = 0;
		movesMade = 0;
		isAWin = false;
	}
	
	public boolean isAWin() {
		return isAWin;
	}

	public void setAWin(boolean isAWin) {
		this.isAWin = isAWin;
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
	
	
	
}
