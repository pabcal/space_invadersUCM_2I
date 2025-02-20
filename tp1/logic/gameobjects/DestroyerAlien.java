package tp1.logic.gameobjects;


import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Level;
import tp1.logic.Position;
import tp1.view.Messages;

public class DestroyerAlien extends AlienShip {
	private Bomb bomb;
	
	public DestroyerAlien (GameWorld game, Position pos, AlienManager alienManager)
	{
		super(game, pos, Game.DESTROYER_ALIEN_HEALTH, Game.DESTROYER_ALIEN_POINTS, alienManager);
		this.symbol = Messages.DESTROYER_ALIEN_SYMBOL;
	}
	
	public DestroyerAlien() {
		super(null, null, Game.DESTROYER_ALIEN_HEALTH, Game.DESTROYER_ALIEN_POINTS, null);
		this.symbol = Messages.DESTROYER_ALIEN_SYMBOL;
	}
	
	
	/**
	 onDelete() for DestroyerAlien. Further specified in GameObject.
	 */
	@Override
	public void onDelete() {
		game.deleteObject(this);
		game.markPoints(getPoints());
		alienManager.alienDied();
	}	
	
	/**
	 Method in charge of shooting a bomb. 
	 */
	public void shoot() {
		
		if (bomb != null && !bomb.isAlive())
			deleteBomb();
		
		if (bomb == null && shootProbability()) { //If bomb is in the player's position, due to the order of our functions in Game.update(), the bomb won't be deactivated yet 
			createBomb();
		}
	}
	
	/**
	 Method to calculate the shoot probability.
	 @return True if game.ndd() is lower than the shoot frequency of the level. False otherwise
	 */
	private boolean shootProbability() {
		Level level = game.getLevel();
		return game.ndd() < level.getShootFrequency();
	}
	

	/**
	 Method in charge of putting the bomb (Bomb) attribute to null
	 */
	public void deleteBomb() {
		bomb = null;
	}
	
	/**
	 Method in charge of creating a bomb and adding it to the game.container
	 */
	private void createBomb() {
		Position copy = new Position(this.pos.getCol(), this.pos.getRow() + 1);
		Bomb bomb = new Bomb(game, copy);
		game.addObject(bomb);
		this.bomb = bomb;
	}
	
	/**
	 Method that checks if bomb is valid
	 @return True if bomb is not null and is alive.
	 */
//	public boolean validBomb() {
//		return this.bomb != null && this.bomb.isAlive();
//	}

	/**
	  copy() method for DestroyerAlien. Further specified in AlienShip.
	  @return DestroyerAlien
	 */
	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
		return new DestroyerAlien(game, pos, am);
	}
	
	/**
	 * Calls shoot()
	 */
	@Override
	public void computerAction() {
		shoot();
		
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



	
	
	
}

