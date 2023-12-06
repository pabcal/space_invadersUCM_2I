package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class UCMWeapon extends Weapon {

	public UCMWeapon(GameWorld game, Position pos) {
		super(game, pos);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Deals damage to the object it is attacking
	 */
	protected boolean weaponAttack(GameItem item)
	{
		return item.receiveAttack(this);
	}
	/*
	 * receives damage from another weapon
	 */
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		hit(weapon);
		return true;
	}
	
	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		return false;
	}
	
	
}
