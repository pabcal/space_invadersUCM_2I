package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public abstract class Weapon extends GameObject{

	public Weapon(Game game, Position pos, int life) {
		super(game, pos, life);
	}
	
	//TODO fill with your code
	
	
	

	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		
		return false;
	}

	
	
	
	public boolean performAttack(GameItem other) {
		boolean attacked = false;
		if (other.isOnPosition(this.pos)) {
			attacked = weaponAttack(other);
			this.die();
		}
		return attacked;
	}

	protected abstract boolean weaponAttack(GameItem other);
	

	
	
	
	
	
	
	

}
