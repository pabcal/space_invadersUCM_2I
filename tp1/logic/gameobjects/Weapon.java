package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Weapon extends GameObject{
	static final int LIFE_OF_WEAPONS = 1;
	protected boolean active = false;
	protected int damage;
	protected int armor;

	public Weapon(Game game, Position pos, int life) {
		super(game, pos, life);
	}
	
	//TODO fill with your code
	
	public boolean performAttack(GameItem other) {
		boolean attacked = false;
		if (other.isOnPosition(this.pos)) {
			attacked = weaponAttack(other);
			this.die();
		}
		return attacked;
	}
	public boolean is_active()
	{
		return this.active;
	}
	@Override
	protected boolean onBorder() //tells you if its outside
	{
		return pos.getRow() > Game.DIM_Y - 1 || pos.getRow() < 0;
	}
	@Override
	protected int getDamage()
	{
		return this.damage;
	}
	@Override
	public void automaticMove () {
		if (this.active) {
			performMovement();
			if(onBorder())
				die();
		}
	}
	
	@Override
	protected void performMovement() {
		dir.updatePosition(this.pos);
	}
	@Override
	protected int getArmor()
	{
		return this.armor;
	}

	protected abstract boolean weaponAttack(GameItem other);
	

	
	
	
	
	
	
	

}
