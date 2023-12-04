package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Weapon extends GameObject{
	static final int LIFE_OF_WEAPONS = 1;
//	protected boolean active = false;
	protected int damage;
	protected int armor;

	public Weapon(Game game, Position pos) {
		super(game, pos, LIFE_OF_WEAPONS);
	}
	
	//TODO fill with your code
	
	public boolean performAttack(GameItem other) {
		boolean attacked = false;
		if (other.isOnPosition(this.pos) || other.theyCrossed(this)) {
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
		prevPos = pos;
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
