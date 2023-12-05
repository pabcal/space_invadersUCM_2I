package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon{

	public EnemyWeapon(GameWorld game, Position pos) {
		super(game, pos);
		// TODO Auto-generated constructor stub
	}

	protected boolean weaponAttack(GameItem item)
	{
		return item.receiveAttack(this);
	}
	
	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		hit (weapon);
		return true;
		
	}
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
