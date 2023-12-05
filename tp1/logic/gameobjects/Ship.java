package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Ship extends GameObject{
	protected int armor = 0;

	public Ship(GameWorld game, Position pos, int life) {
		super(game, pos, life);
		// TODO Auto-generated constructor stub
	}
	
	public int getArmor() {
		return armor;
	}
	

}
