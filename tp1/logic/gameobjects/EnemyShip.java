package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyShip extends Ship{
	protected int points;

	public EnemyShip(Game game, Position pos, int life, int points) {
		super(game, pos, life);
		this.points = points;
		this.dir = Move.LEFT;
		// TODO Auto-generated constructor stub
	}
	
	public  void automaticMove() { //performs movement if ship is alive, dont know if it is common for weapons too
		if (isAlive())
			performMovement();
	}
	
	public int getPoints() {
		return this.points;
	}

}
