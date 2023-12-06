package tp1.logic;

import java.util.Random;

import tp1.logic.gameobjects.Laser;
import tp1.logic.gameobjects.UCMShip;
import tp1.logic.gameobjects.UCMWeapon;
import tp1.control.InitialConfiguration;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Shockwave;
import tp1.logic.gameobjects.SuperLaser;
import tp1.view.Messages;




public class Game implements GameStatus, GameModel, GameWorld{
	
	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;
	public static final int DESTROYER_ALIEN_DAMAGE = 1;
	public static final int DESTROYER_ALIEN_HEALTH = 1;
	public static final int REGULAR_ALIEN_HEALTH = 2;
	public static final int UFO_HEALTH = 1;
	public static final int DESTROYER_ALIEN_POINTS = 10;
	public static final int REGULAR_ALIEN_POINTS = 5;
	public static final int UFO_POINTS = 25;
	public static final int UCM_DAMAGE = 1;
	public static final int UCM_HEALTH = 3;
	public static final int SHOCKWAVE_DAMAGE = 1;
	public static final int EXPLOSIVE_ALIEN_HEALTH = 2; //ADD IN GAME
	public static final int EXPLOSIVE_ALIEN_POINTS = 12; //ADD IN GAME	
	public static final int EXPLOSION_DAMAGE = 1;

	
	
	private int cycle = 0;
	private Level level; 
	private long seed;
	private UCMShip player = new UCMShip(this);
	private Laser laser; 
	private SuperLaser superLaser;
	private AlienManager manager; 
	private Random rand;
	private int alienCycleCounter = 0;
	private boolean finished = false;
	private Shockwave shockwave = new Shockwave(this);
	boolean enableLaser = false;
	boolean enableSuperLaser = false;
	boolean reseted = false;
	private GameObjectContainer container;

	
	
	
	private int score = 0;

	public Game(Level level, long seed) {
		this.level = level;
		this.seed = seed;
		this.manager = new AlienManager (this, level);
		this.rand = new Random(this.seed);
		init(null);
	}
	
	/**
	 * Initializes the game.continer with the aliens and adds the UCMShip to it.
	 * @param config
	 */
	private void init(InitialConfiguration config) {
		container = manager.initialize(config);
		container.add(player);
	}

	//Specified in GameStatus
	public String stateToString() { 
		StringBuilder buffer = new StringBuilder();
		buffer
		.append("Life: ").append(player.getLife()).append(System.lineSeparator())
		.append("Points: ").append(score).append(System.lineSeparator())
		.append("Shockwave: ").append((shockwave.getShockwaveStatus() ? "ON" : "OFF")).append(System.lineSeparator());
		return buffer.toString();

	}

	//Specified in GameStatus
	public int getCycle() {
		return this.cycle;
	}

	//Specified in GameStatus
	public int getRemainingAliens() {
		return this.manager.getRemainingAliens();
	}
	
	//Specified in GameStatus
	public String positionToString(int col, int row) {
		String what;
		Position posAux = new Position(col, row);		
		what = container.getSymbolInPos(posAux);
		return what;
	}

	//Specified in GameStatus
	public boolean playerWin() {
		return this.manager.getRemainingAliens() == 0;
	}

	//Specified in GameStatus
	public boolean aliensWin()
	{
		return (!this.player.isAlive() || this.manager.inFinalRow());
		
	}

	//Specified in GameModel
	public boolean enableLaser() {
		boolean enabled = false;
		if ((this.laser == null || !this.laser.isAlive()) && (this.superLaser == null || !this.superLaser.isAlive())) {
			enableLaser = true;
			enabled = true;
		}
		return enabled;
	}
	
	//Specified in GameModel
	public boolean enableSuperLaser() {
		boolean enabled = false;
		if(this.score > 4)
			if (( this.superLaser == null || !this.superLaser.isAlive()) && (this.laser == null || !this.laser.isAlive()) ) 
			{
				score -= 5;
				enableSuperLaser = true;
				enabled = true;
			}
			
		return enabled;
	}
	
	/**
	 * Increases the game cycle
	 */
	public void incrCycle() { 
		++this.cycle;
		++this.alienCycleCounter;
	}
	
	//Specified in GameWorld
	public Position getPlayerPos() {
		return this.player.getPos();
	}
	
	/**
	 * Method partially in charge of managing the aliens by sending signals to the AlienManager
	 */
	private void manageAliens() {
		if (getAliensMove()) alienCycleCounter = 0;
		if (manager.onBorder() && !manager.alreadyDescended()) { //to check if in border and did not descend yet
			manager.setDescend(true); //tells manager to descend
			alienCycleCounter = level.getSpeed(); //for the next cycle to reset the counter that indicates when the aliens move
		}
		else if (this.manager.alreadyDescended()) //if it already descended then we don't want it to descend again
			this.manager.setDescend(false);
		if (!this.manager.onBorder()) //when we get off the border we set already descended to false for the next time it is on the border it knows it has to descend
			this.manager.setAlreadyDescended(false);
		
	}
	
	 /**
	  * Method in charge of processing the collisions of a UCMWeapon with other objects in the board. First it checks if the weapon crossed with 
	  * another enemy object, then if it is in the same position. If any of these is true, then the weapon should die and the method should end. 
	  * @param weapon
	  */
	private void processHIT(UCMWeapon weapon) 
	{
		int i = 0;
		if (weapon != null && weapon.isAlive()) {
			while (i < container.getSize() && weapon.isAlive()) {
				weapon.performAttack(container.getObject(i), true);
				++i;
			}
			i = 0;
			while (i < container.getSize() && weapon.isAlive()) {
				weapon.performAttack(container.getObject(i), false);
				++i;
			}
		}
	}
	
	//Specified in GameModel
	public boolean movePlayer(Move dir) {
		boolean did = false;
		if (dir != null && dir != Move.UP && dir != Move.DOWN)
			did = player.performMovement(dir);
		return did;
	}
	
	//Specified in GameModel
	public void listCommand() 
	{
		System.out.printf(Messages.UCM_DESCRIPTION, Messages.UCMSHIP_DESCRIPTION, Game.UCM_DAMAGE, Game.UCM_HEALTH);
		System.out.println("");
		System.out.printf(Messages.ALIEN_DESCRIPTION, Messages.REGULAR_ALIEN_DESCRIPTION, Game.REGULAR_ALIEN_POINTS, 0, Game.REGULAR_ALIEN_HEALTH);
		System.out.println("");
		System.out.printf(Messages.ALIEN_DESCRIPTION, Messages.DESTROYER_ALIEN_DESCRIPTION, Game.DESTROYER_ALIEN_POINTS,Game.DESTROYER_ALIEN_DAMAGE, Game.DESTROYER_ALIEN_HEALTH);
		System.out.println("");
		System.out.printf(Messages.ALIEN_DESCRIPTION, Messages.UFO_DESCRIPTION,Game.UFO_POINTS, 0, Game.UFO_HEALTH);
		System.out.println("");
		System.out.printf(Messages.ALIEN_DESCRIPTION, Messages.EXPLOSIVE_ALIEN_DESCRIPTION,Game.EXPLOSIVE_ALIEN_POINTS, Game.EXPLOSION_DAMAGE, Game.EXPLOSIVE_ALIEN_HEALTH);
		System.out.println("");
	}

	//Specified in GameWorld
	public Level getLevel() { 
		return this.level;
	}

	//Specified in GameModel
	public void reset(InitialConfiguration config)
	{
		this.score = 0;
		this.cycle = 0;
		this.laser = null;
		this.manager = null;
		this.manager = new AlienManager(this, this.level);
		container = null;
		this.player = new UCMShip(this);
		init(config);
		this.rand = new Random(this.seed);
		this.alienCycleCounter = 1;
		reseted = true;
	}
	
	/**
	 Update method is in charge of updating all objects' state after executing a command and before printing the board again.
	 */
	public void update() { 
		if (!reseted) { //If command was not reset
			if (laser != null && !laser.isAlive()) // checks is laser is not null and is dead, and proceeds to put it to null
				laser = null;
			if (superLaser != null && !superLaser.isAlive()) // checks is superLaser is not null and is dead, and proceeds to put it to null
				superLaser = null;
			
			container.automaticMoves(); //automatic moves of all objects
			manageAliens(); 
			container.computerActions(); //computer actions of all objects
	
			if (laser == null && enableLaser) { //If shoot was called then enableLaser will be true
				laser = new Laser(this);
				container.add(laser); //adds laser to game
			}
			
			else if (superLaser == null && enableSuperLaser) //If Super Laser was called then enableSuperLaser will be true
			{
				superLaser = new SuperLaser(this);
				container.add(superLaser); //adds Super Laser to game
			}
			
			//Here it checks if laser or Super Laser are not null, and if one of them is not it will call processHIT() with that UCMWeapon
			UCMWeapon weapon;
			if (laser != null)
				weapon = laser;
			else if (superLaser != null)
				weapon = superLaser;
			else
				weapon = null;

			enableLaser = enableSuperLaser = false;
			processHIT(weapon); //In charge of checking collision of the UCMWeapons with the enemy objects of the game.
			playerProcessHit(); //In charge of checking collisions between bombs and player.
			incrCycle();
		}
		else
			reseted = false; //put reseted to false for next cycle (board is just printed)
	}
	
	
	//Specified in GameWorld
	public void enableShockwave()
	{
		shockwave.setShockwave(true);
	}
	
	
	//Specified in GameModel
	public boolean shootShockwave() { 
		boolean completed = false;
		int size = container.getSize();
		if (shockwave.getShockwaveStatus())
		{
			for (int i = size - 1; i >= 0; --i) {
				GameObject obj = container.getObject(i);
				obj.receiveAttack(shockwave);
			}
			completed = true;
			shockwave.setShockwave(false);
		}
		return completed;
	}
	
	//Specified in GameWorld
	public void markPoints(int points) { 
		this.score += points;
	}
	
	
	/**
	 Method concerning the collision and damage effects between the bombs and the player.
	*/
	private void playerProcessHit() { 
		
		for (int i = 0; i < container.getSize(); ++i) {
			GameObject obj = container.getObject(i);
			obj.performAttack(player, false);
		}
	}
	
	//Specified in GameWorld
	public double ndd() { //produces and returns the next double of the Random object in Game - world
		double nextDouble = this.rand.nextDouble();
		return nextDouble;
	}
	
	
	//Specified in GameWorld
	public boolean getAliensMove() { //world
		return (this.alienCycleCounter == (this.level.getSpeed() + 1));
	}

	
	//Specified in GameModel
	public boolean isFinished()
	{
		if (!finished && (this.playerWin() || this.aliensWin()))
			finished = true;
			
		return finished;
	}

	//Specified in GameModel
	public void exit() { //COMENTAR - model
		finished = true;
	}

	//Specified in GameStatus
	@Override
	public String infoToString() { // status
		// TODO Auto-generated method stub
		return null;
	}
	
	//Specified in GameWorld
	public GameObject objectsExploded (Position pos)
	{
		return container.getInPos(pos);
	}
	
	//Specified in GameWorld
	public void addObject(GameObject obj) { //world
		container.add(obj);
	}
	
	//Specified in GameWorld
	public void deleteObject(GameObject obj) { //world
		container.remove(obj);
	}
	
	
	
	
	
}

