package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class representing a regular alien
 *
 */
public class RegularAlien extends AlienShip{

	public RegularAlien (GameWorld game, Position pos, AlienManager alienManager)
	{	
		super(game, pos, Game.REGULAR_ALIEN_HEALTH, Game.REGULAR_ALIEN_POINTS, alienManager);
		this.symbol = Messages.REGULAR_ALIEN_SYMBOL;
	}
	
	public RegularAlien() {
		super(null, null, Game.REGULAR_ALIEN_HEALTH, Game.REGULAR_ALIEN_POINTS, null);
		this.symbol = Messages.REGULAR_ALIEN_SYMBOL;
	}
	
	
	/**
	 onDelete() method for RegularAlien. Further specified in GameObject.
	 */
	@Override
	public void onDelete() {
		game.deleteObject(this);
		game.markPoints(getPoints());
		alienManager.alienDied();
	}
	
	
	/**
	  copy() method for RegularAlien. Further specified in AlienShip.
	 */
	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
		return new RegularAlien(game, pos, am);
	}

	

	
	////////////////////////////////////////////////////////////////////////////////////
	//EMPTY METHODS

	@Override
	public boolean performAttack(GameItem other, boolean cross) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected int getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

	
	

	
	
}