package tp1.logic.gameobjects;

import tp1.logic.Position;

public interface GameItem {
	
	
	
	/**
	 Method in charge of performinG attack to another game object. If attack was successfully performed it returns true.
	 Otherwise it returns false.
	 */
	public boolean performAttack(GameItem other, boolean cross);
	
	
	/**
	 Method in charge of receiving attack from a UCMWeapon. Only returns true if damage was done to the
	 object by the weapon. 
	 */
	public boolean receiveAttack(UCMWeapon weapon);
	
	/**
	 Method in charge of receiving attack from an EnemyWeapon. Only returns true if damage was done to the
	 object by the weapon. 
	 */
	public boolean receiveAttack(EnemyWeapon weapon);
	
	/**
	 Method in charge of receiving attack from the Explosion. Always return true; 
	 */
	public void receiveAttack(Explosion weapon);

	
	/**
	 Returns true if object is alive. False otherwise.
	 */
	public boolean isAlive();
	
	/**
	 Returns true if the position of the object is equal to the position passed as an argument. 
	 False otherwise.
	 */
	public boolean isOnPosition(Position pos);
	
	/**
	 Returns true if the object crossed the object passed as an argument given their positions and previous positions. 
	 False otherwise. 
	 */
	public boolean theyCrossed(GameObject obj);

}
