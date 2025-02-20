package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class GameObject implements GameItem {

	protected Position pos;
	protected Position prevPos;
	protected int life;
	protected GameWorld game;
	protected Move dir;
	boolean delete = false;
	
	public GameObject(GameWorld game, Position pos, int life) {	
		this.pos = pos;
		this.game = game;
		this.life = life;
		this.prevPos = pos;
	}
	
	
	/*
	 * getter method for object's life
	 */
	public int getLife() {
		return this.life;
	}

	/*
	 * Getter method for object's symbol
	 */
	public abstract String getSymbol();
	/*
	 * Getter method for object's Damage
	 */
	protected abstract int getDamage();
	/*
	 * Getter method for object's armor
	 */
	protected abstract int getArmor();
	
	protected abstract void performMovement();
	
	public abstract void automaticMove();
	protected abstract boolean onBorder();
	public abstract void computerAction();
	/*
	 * method called when object dies 
	 */
	public abstract void onDelete();
	

	public void receiveAttack(Explosion weapon)
	{
		hit(weapon);
	
	}
	
	/*
	 * if the life of the object being attacked, is the same as the damage of the weapon 
	 * attacking it, the object dies
	 * otherwise, it looses as much life as the damage of the weapon attacking it
	 */
	public void hit(Weapon weapon)
	{
		int damage = weapon.getDamage();
		if (life <= damage)
			die();
		else 
			life -= damage;
	}
	
	/*
	 * Sets life to 0 and calls onDelete()
	 */
	
	protected void die()
	{
		onDelete();
		life = 0;
	}

	@Override
	public boolean isAlive() {
		return life > 0;
	}
	
	public boolean isOnPosition(Position pos) {
		return this.pos.isEqual(pos);
	}
	/*
	 * Getter method for object's position
	 */
	public Position getPos()
	{
		return pos;
	}
	/*
	 * Getter method for object's previous pos
	 */
	public Position getPrevPos() {
		return prevPos;
	}
	/*
	 * Checks if 2 object's paths have crossed 
	 */
	public boolean theyCrossed(GameObject obj) {
		
		return (prevPos.isEqual(obj.getPos()) && pos.isEqual(obj.getPrevPos()));
	}

}
