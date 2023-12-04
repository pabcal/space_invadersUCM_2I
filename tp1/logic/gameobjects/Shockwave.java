package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Shockwave extends UCMWeapon{
	private boolean shockwave = false;
	Ufo ufo;

	public Shockwave(Game game) {
		super(game,  new Position(0, 0));
		damage = Game.SHOCKWAVE_DAMAGE;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getSymbol() {
		return Messages.SHOCKWAVE_SYMBOL;
	}

	
	
	public boolean getShockwaveStatus()
	{
		return this.shockwave;
	}
	public void setShockwave(boolean value)
	{
		this.shockwave = value;
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
