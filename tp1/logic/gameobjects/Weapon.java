package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Weapon extends GameObject{
	protected boolean active = false;
	protected int damage;

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
	
	protected boolean onBorder()
	{
		return pos.getRow() > Game.DIM_Y - 1 || pos.getRow() < 0;
	}
	
	protected int getDamage()
	{
		return this.damage;
	}

	public void automaticMove () {
		if (this.active) {
			performMovement(dir);
			if(onBorder())
				die();
		}
	}
	
	protected void performMovement(Move dir) {
		dir.updatePosition(this.pos);
//		this.inPlayerPos = this.game.inPlayerPos(this.pos); //unique for bomb, but we can implement it in computer actions
	}
	

	protected abstract boolean weaponAttack(GameItem other);
	

	
	
	
	
	
	
	

}
