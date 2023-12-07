package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Shockwave extends UCMWeapon{
	private boolean shockwave = false;
	Ufo ufo;

	public Shockwave(GameWorld game) {
		super(game,  new Position(0, 0));
		this.damage = Game.SHOCKWAVE_DAMAGE;
	}

	@Override
	public String getSymbol() {
		return Messages.SHOCKWAVE_SYMBOL;
	}

	/*
	 * Getter method for boolean shockwave 
	 */
	public boolean getShockwaveStatus()
	{
		return this.shockwave;
	}
	
	/*
	 * Getter method for boolean shockwave
	 */
	public void setShockwave(boolean value)
	{
		shockwave = value;
	}
	
	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}
}
