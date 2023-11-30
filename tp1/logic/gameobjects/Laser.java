package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;


//Changes 1.1: getter functions for position and appearance, and addition of position and appearance in attributes

/**
 * 
 * Class that represents the laser fired by {@link UCMShip}
 *
 */
public class Laser {
	
	//TODO fill your code
	private Move dir;
	private String appearance = Messages.LASER_SYMBOL;
	private Position pos = new Position(-1, -1);
	private boolean active = false;
	private int damage = Game.UCM_DAMAGE; //ask	
	
	public Laser() {
		this.dir = Move.UP;
	}

	/**
	 *  Method called when the laser disappears from the board
	 */
//	public void onDelete() {
//		game.enableLaser();
//	}

	/**
	 *  Implements the automatic movement of the laser	
	 */
	public void automaticMove () {
		performMovement(dir);
		if(isOut())
			die();
	}

	
	
	public int getDamage()
	{
		return this.damage;
	}
	
	
	
	
	// PERFORM ATTACK METHODS
	
	
	
	
	private void die() { //ASK -------------------------------- PUBLIC OR PRIVATE

		this.active = false;
	}

	private boolean isOut() { //
		return this.pos.getRow() < 0;
	}

	private void performMovement(Move dir) {
		dir.updatePosition(this.pos);
	}

	/**
	 * Method that implements the attack by the laser to a regular alien.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the regular alien possibly under attack
	 * @return <code>true</code> if the alien has been attacked by the laser.
	 */
	public boolean performAttack(RegularAlien other) { //ASK -----------------------------------------------------------------
		boolean attacked = false;
		if (other.inPosition(this.pos)) {
			attacked = weaponAttack(other);
			this.die();
		}
		//TODO fill your code
		return attacked;
	}
	/**
	 * Method that implements the attack by the laser to a destroyer alien.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the destroyer alien possibly under attack
	 * @return <code>true</code> if the alien has been attacked by the laser.
	 */

	
	
	//TODO fill your code


	//ACTUAL ATTACK METHODS
	

	/**
	 * 
	 * @param other regular alien under attack by the laser
	 * @return always returns <code>true</code>
	 */
	private boolean weaponAttack(RegularAlien other) { //ASK RETURN TRUE -----------------------------------------------------------------------------------------------
		return other.receiveAttack(this);	
	}
	
	
	public Position getPos() { //changes 1.1
		return this.pos;
	}
	
	public String getAppearance() { //changes 1.1
		return this.appearance;
	}
	
	public void activate() {
		this.active = true;
	}
	
	public boolean is_active() {
		return this.active;
	}

	//TODO fill your code


	// RECEIVE ATTACK METHODS
	
	/**
	 * Method to implement the effect of bomb attack on a laser
	 * @param weapon the received bomb
	 * @return always returns <code>true</code>
	 */
	
	public boolean receiveAttack(Bomb weapon) {
		receiveDamage();
		return true;
	}
	
	public void receiveDamage() {
		die();
	}

	public boolean performAttack(DestroyerAlien other) {
		boolean attacked = false;
		if (other.inPosition(this.getPos())) {
			attacked = weaponAttack(other);
			this.die();
		}
		return attacked;
	}
	
	private boolean weaponAttack(DestroyerAlien other) { 
		return other.receiveAttack(this);	
	}
	
	public boolean performAttack(Ufo other) { 
		boolean attacked = false;
		if (other.inPosition(this.getPos())) {
			attacked = weaponAttack(other);
			this.die();
		}
		//TODO fill your code
		return attacked;
	}
	
	private boolean weaponAttack(Ufo other) { 
		return other.receiveAttack(this);	
	}

}
