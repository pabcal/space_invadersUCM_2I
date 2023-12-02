package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Shockwave extends UCMWeapon{
	private boolean shockwave = false;
	Ufo ufo;

	public Shockwave(Game game) {
		super(game,  new Position());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getSymbol() {
		return Messages.SHOCKWAVE_SYMBOL;
	}

	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean getShockwaveStatus()
	{
		return this.shockwave;
	}
	public void setShockwave(boolean value)
	{
		this.shockwave = value;
	}
	public void UfoHasBeenHit()
	{
		if(!ufo.isAlive())
			shockwave = true;
	}
	
	//I can make this a boolean function that returns shockwave as well as changes it
	//and then call it inside the if of shootShockwave
	
	

}
