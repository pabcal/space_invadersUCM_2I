package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class AlienShip extends EnemyShip {
	protected AlienManager alienManager;
	protected boolean descended = false;
	protected String symbol;

	public AlienShip(GameWorld game, Position pos, int life, int points, AlienManager alienManager) {
		super(game, pos, life, points);
		this.alienManager = alienManager;
		
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 Performs descent of alien when called. Sets attribute descended to true. 
	 */
	public  void descent() { 
		Move dir2 = Move.DOWN;
		dir2.updatePosition(this.pos);
		if (!alienManager.alreadyDescended())
			alienManager.setAlreadyDescended(true);
		descended = true;
	}
	
	/**
	 Performs movement for all alien ships. Communicates with alien manager to know when to descend and change direction of movement. 
	 Also sends signal to alien manager if the object is in the final row.
	 */
	@Override
	public  void performMovement() {
		if (game.getAliensMove()) { //
			prevPos = new Position(pos.getCol(), pos.getRow());
			if (alienManager.readyToDescend()) { //if alien manager tells it to descend it calls descend()
				descent();
			}
			else {
				if (descended) {
					if (dir == Move.LEFT) //changes direction
						dir = Move.RIGHT;
					else
						dir = Move.LEFT;
					if (alienManager.onBorder()) //calls alien manager to tell it it is no longer in border
						alienManager.notOnBorder();
					descended = false;
				}
				dir.updatePosition(pos); //updates position
			}
			if (onBorder() && !descended) { //if it is on border and havent't descended yet, then it tells alien manager it is on border
				alienManager.shipOnBorder();
			}
			if (finalRow() && !alienManager.inFinalRow()) //if in final row, it sends a signal to alien manager
				alienManager.sendInFinalRow();
		}
	}
	
	/**
	 Returns true if alien is in border (only side borders). False otherwise.
	 */
	@Override 
	public boolean onBorder() {
		return (pos.getCol() == 0 || pos.getCol() == Game.DIM_X - 1);
	}
	
	/**
	 Returns true if alien is in final row. False otherwise.
	 */
	public boolean finalRow ()
	{
		return pos.getRow() == Game.DIM_Y - 1;
	}
	
	/*
	 
	 */
//	public boolean descended() {
//		return this.alienManager.alreadyDescended();
//	}
	
	/**
	 Calls a method to tell alien manager that alien died. 
	 */
	public void callDead() {
		alienManager.alienDied();
	}
	
	
	/**
	 Specified in GameItem
	 */
	@Override
	public String getSymbol()
	{
		return symbol + "[" + (life < 10 ? "0": "") + Integer.toString(life) + "]";
	}
	
	/**
	 Returns the symbol attribute. Used for the Alien Factory.
	 */
	public String getSymbolFact() {
		return symbol;
	}
	
	
	/**
	 Returns a copy of an instance of the corresponding AlienShip child class (DestroyerAlien, RegularAlien, ExplodingShip)
	 */
	protected abstract AlienShip copy(GameWorld game, Position pos, AlienManager am);

}