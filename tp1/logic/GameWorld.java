package tp1.logic;

import tp1.logic.gameobjects.GameObject;

public interface GameWorld {
	/*
	 * getter method for the position of the player
	 */
	public Position getPlayerPos();
	/*
	 * getter method for the level chosen
	 */
	public Level getLevel();
	/*
	 * Sets the shockwave boolean to true 
	 */
	public void enableShockwave();
	/*
	 * Adds the points obtained from killing an alien to the total number of points
	 */
	public void markPoints(int points);
	/*
	 * Controls the alien speed
	 */
	public boolean getAliensMove();
	/*
	 * Adds object to the game object container 
	 */
	public void addObject(GameObject obj);
	/*
	 * deletes object to the game object container 
	 */
	public void deleteObject(GameObject obj);
	/*
	 * generates a random double 
	 */
	public double ndd();
	/*
	 * returns true if the player is not alive or the aliens have reached the final row 
	 */
	public boolean aliensWin();
	/*
	 * Returns the object in a specific location of the container. Used only for explosion.
	 */
	public GameObject objectsExploded(Position pos);
}
