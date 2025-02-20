package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public class Explosion extends Weapon{

	public Explosion(GameWorld game, Position pos) {
		super(game, pos);
		this.damage = Game.EXPLOSION_DAMAGE;
		// TODO Auto-generated constructor stub
	}

	public void performExplosion()
	{
		GameItem other = null;
		for(int i = -1; i < 2; i++)
			for(int j = -1; j < 2; j++)
			{
				Position adjeacentAndDiagonalPos = new Position(pos.getCol() + i, pos.getRow() + j); 
				other = game.objectsExploded(adjeacentAndDiagonalPos);
				if (other != null && other.isAlive())
				{
					other.receiveAttack(this);
				}
			}
	}
	
	
	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean weaponAttack(GameItem other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

}
