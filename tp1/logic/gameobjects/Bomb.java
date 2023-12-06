package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class Bomb extends EnemyWeapon {
	
	public Bomb( GameWorld game, Position pos) {
		super(game, pos);
		damage = Game.DESTROYER_ALIEN_DAMAGE;
		dir = Move.DOWN;
	}
	
	public String getSymbol() {
		return Messages.BOMB_SYMBOL;
	}

	
	@Override
	public void computerAction() {
		
	}

}
