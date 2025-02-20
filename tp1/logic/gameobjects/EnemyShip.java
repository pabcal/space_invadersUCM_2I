package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyShip extends Ship{
	protected int points;

	public EnemyShip(GameWorld game, Position pos, int life, int points) {
		super(game, pos, life);
		this.points = points;
		this.dir = Move.LEFT;
	}
	
	
	/*
	 Performs movement if ship is alive.
	 */
	public  void automaticMove() { 
		if (isAlive()) 
			performMovement();
		
	}
	
	
	/*
	 Getter method for attribute points (int).
	 */
	public int getPoints() {
		return points;
	}
	
	
	/*
	 Receives attack from a UCMWeapon. Always return true.
	 */
	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		hit (weapon);
		return true;
		
	}
	
	
	/*
	 Receives attack from EnemyWeapon. Does not affect life of object. Always return false.
	 */
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		// TODO Auto-generated method stub
		return false;
	}

}
