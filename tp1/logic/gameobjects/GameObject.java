package tp1.logic.gameobjects;

import tp1.logic.Game;
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
	
	

	public int getLife() {
		return this.life;
	}

	//TODO fill with your code

	
	public abstract String getSymbol();
	protected abstract int getDamage();
	protected abstract int getArmor();
	
	protected abstract void performMovement();
	
			
	//public abstract void onDelete();
	public abstract void automaticMove();
	protected abstract boolean onBorder();
	public abstract void computerAction();
	public abstract void onDelete();
	
	//TODO fill with your code
	
	
	public void receiveAttack(Explosion weapon)
	{
		hit(weapon);
	
	}
	
	
	public void hit(Weapon weapon)
	{
		int damage = weapon.getDamage();
		if (life <= damage)
			die();
		else 
			life -= damage;
	}
	
	protected void die()
	{
		onDelete();
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
	
	public Position getPrevPos() {
		return prevPos;
	}
	
	public boolean theyCrossed(GameObject obj) {
		
		return (prevPos.isEqual(obj.getPos()) && pos.isEqual(obj.getPrevPos()));
	}

}
