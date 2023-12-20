package tp1.logic;

import tp1.control.InitialConfiguration;
/*
* The game methods which are called in commands 
*/
public interface GameModel {
	
	
	/*
	 * Exits the game setting the game boolean finished to true 
	 */
	public void exit();
	/*
	 * if the bool variable finished is false, and either of the players have won, the game finishes
	 */
	public boolean isFinished();
	/*
	 * prints the name and description of each object (the list)
	 */
	public void listCommand();
	/*
	 * Inputting a parameter of type move, performs that movement in that direction if it is valid
	 */
	public boolean movePlayer(Move dir) throws OffWorldException, NotAllowedMoveException;
	/*
	 * resets the game based on the configuration chosen 
	 */
	public void reset(InitialConfiguration config);
	/*
	 * If the shockwave is active, shoots the shockwave dealing damage to everyone in the container.
	 * Once shot, sets the shoclwave to false
	 */
	public boolean shootShockwave() throws NoShockWaveException;
	/*
	 * Activates the laser if there is no laser already active (including the superlaser)
	 */
	public boolean enableLaser() throws LaserInFlightException;
	/*
	 * Activates the superlaser if there is no laser already active (including the regular laser)
	 */
	public boolean enableSuperLaser() throws LaserInFlightException, NotEnoughPointsException;
}
