package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class GameObject implements GameItem {

	protected Position pos;
	protected int life;
	protected Game game;
	protected Move dir;
	
	public GameObject(Game game, Position pos, int life) {	
		this.pos = pos;
		this.game = game;
		this.life = life;
	}
	
	

	protected int getLife() {
		return this.life;
	}

	//TODO fill with your code

	
	protected abstract String getSymbol();
	protected abstract int getDamage();
	protected abstract int getArmor();
	
	protected abstract void performMovement();
	
			
	//public abstract void onDelete();
	public abstract void automaticMove();
	protected abstract boolean onBorder();
	public abstract void computerAction();
	
	//TODO fill with your code
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		hit(weapon);
		return true;
	}

	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		hit (weapon);
		return true;
		
	}
	
	protected void hit(Weapon weapon)
	{
		life = life - weapon.getDamage();
	}
	
	protected void die()
	{
		this.life = 0;
	}
	
	@Override
	public boolean isAlive() {
		return this.life > 0;
	}
	
	public boolean isOnPosition(Position pos) {
		return this.pos.isEqual(pos);
	}
	
	public Position getPos()
	{
		return pos;
	}

}
