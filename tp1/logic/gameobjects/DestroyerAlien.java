package tp1.logic.gameobjects;


import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Level;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class DestroyerAlien extends AlienShip {
//	private Move dir = Move.LEFT;
//	private int health;
//	private int points = Game.DESTROYER_ALIEN_POINTS;
//	private Game game;
	private Bomb bomb;
	
//	private String appearance = Messages.DESTROYER_ALIEN_SYMBOL;
//	private Position pos;
//	private boolean descended = false;
//	
//	private AlienManager alienManager;

	//TODO fill your code

	public DestroyerAlien (Game game, Position pos, AlienManager alienManager)
	{
		super(game, pos, Game.DESTROYER_ALIEN_HEALTH, Game.DESTROYER_ALIEN_POINTS, alienManager);
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

//
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
//		if (this.health <= 0)
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
//	
//	public Position getPos()
//	{
//		return this.pos;
//	}
	
//	public void hit(int damage)
//	{
//		this.health = this.health - damage;
//		
//	}
	
//	public boolean finalRow ()
//	{
//		return this.pos.getRow() == Game.DIM_Y - 1;
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
//		if (this.isInBorder() && !this.descended) {
//			this.alienManager.shipOnBorder();
//		}
//		if (this.finalRow() && !this.alienManager.inFinalRow())
//			this.alienManager.sendInFinalRow();
//	}
	
	
	public String getSymbol()
	{
		return Messages.DESTROYER_ALIEN_SYMBOL + "[" + (life < 10 ? "0": "") + Integer.toString(life) + "]";
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
	
	
//	public int getPoints() {
//		return this.points;
//	}
	
	public void bombMove() {
		if (this.bomb != null) {
			this.bomb.automaticMove();
		}
	}
	
	
	public Bomb getBomb() {
		return this.bomb;
	}
	
	public void deleteBomb() {
		this.bomb = null;
	}
	
	private void createBomb() {
		Position copy = new Position(this.pos.getCol(), this.pos.getRow() + 1);
		Bomb bomb = new Bomb(game, copy);
		this.bomb = bomb;
	}
	
	public boolean validBomb() {
		return this.bomb != null && this.bomb.isAlive();
	}

	
	
	//IGNORE
	
	@Override
	public boolean performAttack(GameItem other) {
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

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}
}

