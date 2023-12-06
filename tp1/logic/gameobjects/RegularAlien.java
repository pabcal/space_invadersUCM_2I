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

	//TODO fill your code
//	private int cyclesToMove;
//	private Move dir = Move.LEFT;
//	private int health;
//	private int points = Game.REGULAR_ALIEN_POINTS;
//	
//	private String appearance = Messages.REGULAR_ALIEN_SYMBOL;
//	private Position pos;
//	private boolean descended = false;
	
//	private AlienManager alienManager;

	//TODO fill your code

	public RegularAlien (GameWorld game, Position pos, AlienManager alienManager)
	{	
		super(game, pos, Game.REGULAR_ALIEN_HEALTH, Game.REGULAR_ALIEN_POINTS, alienManager);
		symbol = Messages.REGULAR_ALIEN_SYMBOL;
	}
	
	public RegularAlien() {
		super(null, null, Game.REGULAR_ALIEN_HEALTH, Game.REGULAR_ALIEN_POINTS, null);
		symbol = Messages.REGULAR_ALIEN_SYMBOL;
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