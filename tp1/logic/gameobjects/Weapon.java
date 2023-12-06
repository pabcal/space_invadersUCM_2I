package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class Weapon extends GameObject{
	static final int LIFE_OF_WEAPONS = 1;
	protected int damage;
	protected int armor;

	public Weapon(GameWorld game, Position pos) {
		super(game, pos, LIFE_OF_WEAPONS);
	}
	/*
	 * If the boolean cross is true, the function calls theyCrossed, which returns a bool.
	 * If the boolean cross is false, the function checks if the weapon is in the same 
	 * position as the object receiving the attack, returning a bool.
	 * If the specific function called returns true, it will do a weaponAttack on the object.
	 * Since all weapons (so far) have a health of 1, if the weapon attacks, it will die on impact
	 */
	@Override
	public boolean performAttack(GameItem other, boolean cross) {
		boolean attacked = false;
		boolean crossed = (cross ? other.theyCrossed(this) : other.isOnPosition(this.pos));
		if (crossed) {
			attacked = weaponAttack(other);
			if (attacked)
				die();
		}
		return attacked;
	}
	/*
	 * Checks if the weapon is inside the border 
	 */
	@Override
	protected boolean onBorder()
	{
		return pos.getRow() > Game.DIM_Y - 1 || pos.getRow() < 0;
	}
	/*
	 * Getter method for weapon damage 
	 */
	@Override
	public int getDamage()
	{
		return this.damage;
	}
	/*
	 * If the weapon is alive, it calls perform movement, if it is on the border, it kills it
	 */
	@Override
	public void automaticMove () {
		if (this.isAlive()) {
			performMovement();
			if(onBorder())
				die();
		}
	}
	/*
	 * Updates the weapons position, saves the previous position to later use to check 
	 * if the weapon and object have crossed 
	 */
	@Override
	protected void performMovement() {
		prevPos = new Position(pos.getCol(), pos.getRow());
		dir.updatePosition(this.pos);
	}
	/*
	 * Getter method for armor
	 */
	@Override
	protected int getArmor()
	{
		return this.armor;
	}
	
	protected abstract boolean weaponAttack(GameItem other);
	
	/*
	 * deletes the weapon from the container once it dies 
	 */
	@Override
	public void onDelete() {
		game.deleteObject(this);
	}
	
	
	
	
	
	

}
