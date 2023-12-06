package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExplodingShip extends AlienShip{
	
	public static final int EXPLOSIVE_ALIEN_HEALTH = 2; //ADD IN GAME
	public static final int EXPLOSIVE_ALIEN_POINTS = 12; //ADD IN GAME	
	
	public ExplodingShip (GameWorld game, Position pos, AlienManager alienManager)
	{	
		super(game, pos, EXPLOSIVE_ALIEN_HEALTH, EXPLOSIVE_ALIEN_POINTS, alienManager);
		symbol = Messages.EXPLOSIVE_ALIEN_SYMBOL;
	}
	
	public ExplodingShip() {
		super(null, null, EXPLOSIVE_ALIEN_HEALTH, EXPLOSIVE_ALIEN_POINTS, null);
		symbol = Messages.EXPLOSIVE_ALIEN_SYMBOL;
	}

	
	public void explode() { 
		Explosion explosion = new Explosion(game, pos) ;
		explosion.performExplosion();
	}

	@Override
	public void onDelete() {
		game.deleteObject(this);
		game.markPoints(getPoints());
		alienManager.alienDied();
		explode();
	}
	
	
	@Override
	public boolean performAttack(GameItem other, boolean cross) {
		return false;
	}
	
	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
		return new ExplodingShip(game, pos, am);
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
