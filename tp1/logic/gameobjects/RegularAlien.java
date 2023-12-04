package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
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

	public RegularAlien (Game game, Position pos, AlienManager alienManager)
	{	
		super(game, pos, Game.REGULAR_ALIEN_HEALTH, Game.REGULAR_ALIEN_POINTS, alienManager);
	}
	
	/**
	 *  Implements the automatic movement of the regular alien	
	 */
//	public void automaticMove() {
//		if(this.Alive())
//			this.performMovement();
//	}

//	private void descent() {
//		Move dir2 = Move.DOWN;
//		dir2.updatePosition(this.pos);
//		if (!this.alienManager.alreadyDescended())
//			this.alienManager.setAlreadyDescended(true);
//		descended = true;
//		
//	}


//	private boolean isInBorder() {// ASK-------------------------------------------------------------------- FUNCION EN COMUN PARA TODAS (PUBLIC OR PRIVATE)
//		//TODO fill your code
//		return (this.pos.getCol() == 0 || this.pos.getCol() == Game.DIM_X - 1);
//	}

//	public boolean receiveAttack(Laser laser) {	//ASK ----------------------------------------------------------
//		return this.inPosition(laser.getPos()); 
//	}
	
//	public boolean Alive()
//	{
//		boolean alive = true;
//		if (health <= 0)
//			alive = false;
//		return alive;
//	}
	
//	public int getHealth()
//	{
//		return this.health;
//	}
	
//	public void remove() //ASK ---------------------------------------------------
//	{
//		this.pos.setCol(-1);
//		this.pos.setRow(-1); //CHANGE
//	}
//	
//	public boolean inPosition(Position pos1)
//	{
//		return this.pos.isEqual(pos1);
//	}
	
//	public Position getPos()
//	{
//		return this.pos;
//	}
	
//	public void hit(int damage)
//	{
//		this.health = this.health - damage;
//		
//	}
	

	
//	public void performMovement()
//	{	
//		if (this.alienManager.readyToDescend()) {
//			this.descent();
//		}
//		else {
//			if (this.descended) {
//				if (this.dir == Move.LEFT)
//					this.dir = Move.RIGHT;
//				else
//					this.dir = Move.LEFT;
//				if (this.alienManager.onBorder())
//					this.alienManager.notOnBorder();
//				this.descended = false;
//			}
//			this.dir.updatePosition(this.pos);
//		}
//		if (this.onBorder() && !this.descended) {
//			this.alienManager.shipOnBorder();
//		}
//		if (this.finalRow() && !this.alienManager.inFinalRow())
//			this.alienManager.sendInFinalRow();
//	}
	
	
	public String getSymbol()
	{
		return Messages.REGULAR_ALIEN_SYMBOL + "[" + (this.life < 10 ? "0": "") + Integer.toString(this.life) + "]";
	}
	

	
	//IGNORE METHODS

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