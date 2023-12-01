package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public abstract class EnemyShip extends Ship{
	protected int points;

	public EnemyShip(Game game, Position pos, int life) {
		super(game, pos, life);
		// TODO Auto-generated constructor stub
	}
	
	public  void automaticMove() { //performs movement if ship is alive, dont know if it is common for weapons too
		if (isAlive())
			performMovement();
	}
	

}
