package tp1.logic;

public interface GameStatus {
	/*
	 * returns the symbol of the object in the position 
	 */
	public String positionToString(int x, int y);
	
	public String infoToString();
	/*
	 * Prints the players life, how many points the player has, and if he has the shockwave enabled
	 */
	public String stateToString();
	/*
	 * returns true if the amount of aliens remaining is 0
	 */
	public boolean playerWin();
	/*
	 * returns true if the player is not alive or the aliens have reached the final row 
	 */
	public boolean aliensWin();
	/* 
	 * getter method for the current cycle 
	 */
	public int getCycle();
	/* 
	 * getter method for the aliens that are remaining 
	 */
	public int getRemainingAliens();

}