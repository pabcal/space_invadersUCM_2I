package tp1.logic.gameobjects;


import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Level;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class DestroyerAlien {
	private Move dir = Move.LEFT;
	private int health;
	private int points = Game.DESTROYER_ALIEN_POINTS;
	private Game game;
	private EnemyWeapon bomb;
	
	private String appearance = Messages.DESTROYER_ALIEN_SYMBOL;
	private Position pos;
	private boolean descended = false;
	
	private AlienManager alienManager;

	//TODO fill your code

	public DestroyerAlien (int health, int col, int row, AlienManager alienManager, Game game)
	{
		this.health = health;
		pos = new Position(col, row);
		this.alienManager = alienManager;
		this.game = game;
	}
	
	/**
	 *  Implements the automatic movement of the regular alien	
	 */
	public void automaticMove() {
		if(this.Alive())
			this.performMovement();
	}

	private void descent() {
		Move dir2 = Move.DOWN;
		dir2.updatePosition(this.pos);
		if (!this.alienManager.alreadyDescended())
			this.alienManager.setAlreadyDescended(true);
		descended = true;
		
	}


	private boolean isInBorder() {// ASK-------------------------------------------------------------------- FUNCION EN COMUN PARA TODAS (PUBLIC OR PRIVATE)
		//TODO fill your code
		return (this.pos.getCol() == 0 || this.pos.getCol() == Game.DIM_X - 1);
	}

	public boolean receiveAttack(UCMWeapon laser) {	//ASK ----------------------------------------------------------
		return this.inPosition(laser.getPos()); 
	}
	
	public boolean Alive()
	{
		boolean alive = true;
		if (this.health <= 0)
			alive = false;
		return alive;
	}
	
	public int getHealth()
	{
		return this.health;
	}
	
//	public void remove() //ASK ---------------------------------------------------
//	{
//		this.pos.setCol(-1);
//		this.pos.setRow(-1); //CHANGE
//	}
//	
	public boolean inPosition(Position pos1)
	{
		return this.pos.isEqual(pos1);
	}
	
	public Position getPos()
	{
		return this.pos;
	}
	
	public void hit(int damage)
	{
		this.health = this.health - damage;
		
	}
	
	public boolean finalRow ()
	{
		return this.pos.getRow() == Game.DIM_Y - 1;
	}
	
	public void performMovement()
	{	
		if (this.alienManager.readyToDescend()) {
			this.descent();
		}
		else {
			if (this.descended) {
				if (this.dir == Move.LEFT)
					this.dir = Move.RIGHT;
				else
					this.dir = Move.LEFT;
				if (this.alienManager.onBorder())
					this.alienManager.notOnBorder();
				this.descended = false;
			}
			this.dir.updatePosition(this.pos);
		}
		if (this.isInBorder() && !this.descended) {
			this.alienManager.shipOnBorder();
		}
		if (this.finalRow() && !this.alienManager.inFinalRow())
			this.alienManager.sendInFinalRow();
	}
	
	
	public String getAppearance()
	{
		return appearance + "[" + (this.health < 10 ? "0": "") + Integer.toString(this.health) + "]";
	}
	
	public void shoot() {
		Level level = game.getLevel();
		
		if ((bomb == null || bomb.getInPlayerPos()) && shootProbability()) { //If bomb is in the player's position, due to the order of our functions in Game.update(), the bomb won't be deactivated yet 
			if (bomb != null && bomb.getInPlayerPos())
				this.deleteBomb();
			this.createBomb();
		}
	}
	
	private boolean shootProbability() {
		Level level = game.getLevel();
		return this.game.ndd() < level.getShootFrequency();
	}
	
	public void callDead() {
		this.alienManager.alienDied();
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public void bombMove() {
		if (this.bomb != null) {
			this.bomb.automaticMove();
		}
	}
	
	
	public EnemyWeapon getBomb() {
		return this.bomb;
	}
	
	public void deleteBomb() {
		this.bomb = null;
	}
	
	private void createBomb() {
		Position copy = new Position(this.pos.getCol(), this.pos.getRow() + 1);
		EnemyWeapon bomb = new EnemyWeapon(copy, game);
		this.bomb = bomb;
	}
	
	public boolean validBomb() {
		return this.bomb != null && this.bomb.is_active();
	}
}

