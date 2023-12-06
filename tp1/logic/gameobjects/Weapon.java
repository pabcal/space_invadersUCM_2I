package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class Weapon extends GameObject{
	static final int LIFE_OF_WEAPONS = 1;
//	protected boolean active = false;
	protected int damage;
	protected int armor;

	public Weapon(GameWorld game, Position pos) {
		super(game, pos, LIFE_OF_WEAPONS);
	}
	
	//TODO fill with your code
	
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
//	public boolean is_active()
//	{
//		return this.active;
//	}
	@Override
	protected boolean onBorder() //tells you if its outside
	{
		return pos.getRow() > Game.DIM_Y - 1 || pos.getRow() < 0;
	}
	@Override
	public int getDamage()
	{
		return this.damage;
	}
	@Override
	public void automaticMove () {
		if (this.isAlive()) {
			performMovement();
			if(onBorder())
				die();
		}
	}
	
	@Override
	protected void performMovement() {
		prevPos = new Position(pos.getCol(), pos.getRow());
		dir.updatePosition(this.pos);
	}
	@Override
	protected int getArmor()
	{
		return this.armor;
	}

	protected abstract boolean weaponAttack(GameItem other);
	
	@Override
	public void onDelete() {
		game.deleteObject(this);
	}
	
	
	
	
	
	

}
