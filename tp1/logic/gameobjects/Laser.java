package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class that represents the laser fired by {@link UCMShip}
 *
 */
public class Laser extends UCMWeapon{
	
	public Laser(GameWorld game) {
		super(game, new Position());
		damage = Game.UCM_DAMAGE;
		Position auxPos = game.getPlayerPos();
		pos = new Position(auxPos.getCol(), auxPos.getRow() - 1);
		this.dir = Move.UP;		
	}
	
	public String getSymbol() {
		return Messages.LASER_SYMBOL;
	}

	@Override
	public void computerAction() {
		
	}

}
