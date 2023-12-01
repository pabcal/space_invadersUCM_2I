package tp1.logic;

import java.util.Random;

import tp1.logic.gameobjects.Laser;
import tp1.logic.gameobjects.UCMShip;
import tp1.logic.gameobjects.Ufo;
import tp1.logic.gameobjects.Bomb;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.lists.DestroyerAlienList;
import tp1.logic.lists.RegularAlienList;
import tp1.view.Messages;

//changes 1.1: UCMLaser integration to board. Changes in UCMLaser class and in Game class, referred in code with comments.
//In class Game UCMLaser object was added and method positionToString was modified so that the laser could be displayed in board 



// TODOimplementarlo
public class Game implements GameStatus{
	
	//constant declarations (the same in all difficulties)
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

	
	
	private int cycle = 0;
	private Level level; 
	private long seed;
	private UCMShip player = new UCMShip();
	private Laser laser = new Laser(this); //changes 1.1
	private AlienManager manager; 
	private RegularAlienList rAlienList;
	private DestroyerAlienList dAlienList;
	private Random rand;
	private Ufo ufo;
	private boolean shockwave = false;
	private int alienCycleCounter = 0;
	private boolean finished = false;

	private int score = 0;
	//TODO fill your code

	public Game(Level level, long seed) {
		this.level = level;
		this.seed = seed;
		this.manager = new AlienManager (this, level);
		this.rAlienList  = this.manager.initializeRegularAliens();
		this.dAlienList = this.manager.initializeDestroyerAliens();
		this.rand = new Random(this.seed);
		this.ufo = new Ufo(this);
	}

	public String stateToString() {
		StringBuilder buffer = new StringBuilder();
		buffer
		.append("Life: ").append(this.player.getHealth()).append(System.lineSeparator())
		.append("Points: ").append(this.score).append(System.lineSeparator())
		.append("Shockwave: ").append((this.shockwave ? "ON" : "OFF")).append(System.lineSeparator());
		return buffer.toString();

	}

	public int getCycle() {
		return this.cycle;
	}

	public int getRemainingAliens() { //used in game printer
		return this.manager.getRemainingAliens();
	}

	public String positionToString(int col, int row) {
		String what;
		Position posAux = new Position(col, row);
		RegularAlien auxRAlien = this.rAlienList.anObjectInPos(posAux);
		DestroyerAlien auxDAlien = this.dAlienList.anObjectInPos(posAux);
		Bomb auxBomb = this.dAlienList.searchBombInPos(posAux);
		
		if(auxRAlien != null)
			what = auxRAlien.getAppearance();
		else if (auxDAlien != null)
			what = auxDAlien.getSymbol();
		else if (posAux.isEqual(this.player.getPos()))
			what = player.getSymbol();
		else if (posAux.isEqual(this.laser.getPos()) && this.laser.is_active()) 
			what = this.laser.getSymbol();
		else if (auxBomb != null)
			what = auxBomb.getSymbol();
		else if (posAux.isEqual(this.ufo.getPosition()) && this.ufo.Alive()) 
			what = this.ufo.getAppearance();
		else
			what = "";
		return what;
	}

	public boolean playerWin() {
		return this.manager.getRemainingAliens() == 0;
	}

	public boolean aliensWin() 
	{
		if (this.rAlienList.anObjectInPos(getPlayerPos()) != null ||
				this.dAlienList.anObjectInPos(getPlayerPos()) != null ||
				this.ufo.inPosition(getPlayerPos()))
		{
			this.player.setHealthToZero();
		}
		return (this.player.getHealth() == 0 || this.manager.inFinalRow() ||
				this.rAlienList.anObjectInPos(this.player.getPos()) != null);
		
	}

	
	public boolean enableLaser() { //enables UCMShip's laser
		boolean enabled = false;
		Position laserPos = this.laser.getPos();
		Position playerPos = this.player.getPos();
		if (!this.laser.is_active()) {
			laserPos.setCol(playerPos.getCol());		
			laserPos.setRow(playerPos.getRow());
			this.laser.activate();
			enabled = true;
		}
		
		return enabled;
	}

	public Random getRandom() { //ASK -----------------------------------------------------------
		
		return this.rand;
	}
	
	
	public void incrCycle() {
		++this.cycle;
		++this.alienCycleCounter;
	}
	
	public Position getPlayerPos() {
		return this.player.getPos();
	}
	
	private void laser_move() {
		if (this.laser.is_active())
			this.laser.automaticMove();
	}
	
	private void moveAliens() {
		this.rAlienList.automaticMoves();
		this.dAlienList.automaticMoves();
		if (this.manager.onBorder() && !this.manager.alreadyDescended()) { //to check if in border and did not descend yet
			this.manager.setDescend(true); //tells manager to descend
			this.alienCycleCounter = this.level.getSpeed(); //for the next cycle to reset the counter that indicates when the aliens move
		}
		else if (this.manager.alreadyDescended()) //if it already descended then we don't want it to descend again
			this.manager.setDescend(false);
		if (!this.manager.onBorder()) //when we get off the border we set already descended to false for the next time it is on the border it knows it has to descend
			this.manager.setAlreadyDescended(false);
		
	}
	
	 /* 
	  Game.processHIT() is an important method in our implementation of the game. It is centered in the laser and the different objects with which it can collide in the game.
	  It is called 2 times in the Game.update() method, one after the laser moves and other when the aliens/bombs move. In some cases we want to check aliens/bombs did not cross each other 
	  vertically, which is the reason it is called two times. However, we just want the processHIT to check if they crossed each other vertically, which is why when aliens move horizontally 
	  the function must not check aliens collision with the laser the first time after the laser moves, only the second time when both aliens and laser moved. This is the reason why there is a boolean parameter
	  aliensCheck that indicates the function to check laser collision with aliens or not. Laser collision with bombs is checked always as both objects move always vertically in opposite directions. 
	  
	  
	 */
	private void processHIT(boolean aliensCheck) 
	{
		RegularAlien alien = null;
		DestroyerAlien dAlien = null;
		Bomb bomb = null;
		int i = 0;
		if (laser.is_active()) {
			while (i < this.dAlienList.getNum() && laser.is_active()) {
				bomb = this.dAlienList.getBombFrom(i);
				if (bomb != null && bomb.is_active())
					bomb.performAttack(laser);
				++i;
			}
			i = 0;
			while (aliensCheck && i < this.rAlienList.getNum() && laser.is_active()) {
				alien = this.rAlienList.getAlien(i);
				if (this.laser.performAttack(alien))
					alien.hit(this.laser.getDamage());
				++i;
			}
			i = 0;
			while (aliensCheck && i < this.dAlienList.getNum() && laser.is_active()) {
				dAlien = this.dAlienList.getAlien(i);
				if (this.laser.performAttack(dAlien)) 
					dAlien.hit(this.laser.getDamage());
				++i;
			}
			if (laser.is_active() && this.laser.performAttack(this.ufo))
				this.ufo.hit(laser.getDamage());
			this.removeDead();
		}
		
		
	}
	
	
	public boolean movePlayer(Move dir) {
		return this.player.performMovement(dir);
	}
	
	
	private void removeDead() { //removes dead aliens
		this.rAlienList.removeDead();
		this.dAlienList.removeDead();
	}
	
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
	}
	
	public long getSeed() {
		return this.seed;
	}
	
	public Level getLevel() {
		return this.level;
	}

	public void reset()
	{
		this.score = 0;
		this.cycle = 0;
		this.laser = null;
		this.laser = new Laser(this);
		this.manager = null;
		this.manager = new AlienManager(this, this.level);
		this.rAlienList = null;
		this.rAlienList = this.manager.initializeRegularAliens();
		this.dAlienList = null;
		this.dAlienList = this.manager.initializeDestroyerAliens();
		this.player = new UCMShip();
		this.ufo = null;
		this.ufo = new Ufo(this);
		this.rand = new Random(this.seed);
		this.alienCycleCounter = 0;
		
	}
	
	
	private void shootBombs() {
		this.dAlienList.automaticShoot();
	}
	
	private void processBombs() {
		this.dAlienList.bombProcess();
		this.shootBombs();
	}
	
	public void update() {
		boolean aliensCheck = this.manager.readyToDescend(); //boolean variable that tells the processHit() to check collision between the laser and the aliens, it is true when aliens are ready to descend only
		this.ufo.callUfo(); // this method is in charge of performing all UFO movements as well as checking if it must appear or not
		this.laser_move(); // method in charge of moving the laser
		processHIT(aliensCheck); //method in charge of checking laser collision with ay other object in the board. This is only the first calling, laser already moved but aliens and bombs did not
		if ((this.alienCycleCounter == (this.level.getSpeed() + 1))) { //Check if aliens must move or not in this cycle
			this.alienCycleCounter = 0;
			this.moveAliens(); //method to move aliens
		}
		this.processBombs(); //Method in charge of shooting/moving bombs
		
		this.processHIT(true); //Second calling of the processHIT() method. Laser and aliens already moved.
		this.playerProcessHit(); //In charge of checking collisions between bombs and player, no need to call 2 times.
		this.dAlienList.deleteDeactivatedBombs(); //calls function to delete the bombs that are no longer active
		
	}
	
	public void setShockwave(boolean value)
	{
		this.shockwave = value;
	}
	
	public boolean shootShockwave() {
		boolean completed = false;
		if (shockwave == true)
		{
			completed = true;
			this.dAlienList.shockwaveHit();
			this.rAlienList.shockwaveHit();
			this.setShockwave(false);
		}
		return completed;
	}
	
	
	public void markPoints(int points) {
		this.score += points;
	}
	
	
	/*
	 Game.playerProcessHit() is the method concerning the collision and damage effects between the bombs and the player. Is important to notice that
	 a difference between this function and the laser�s Game.processHIT() is that is not called two times. This is due to the implementation of Bomb.performAttcak(UCMShip other)
	 that allows us to call it only once.
	*/
	public void playerProcessHit() {
		Bomb bomb = null;
		for (int j = 0; j < this.dAlienList.getNum(); ++j) {
			bomb = this.dAlienList.getBombFrom(j);
			if (bomb != null && bomb.is_active())
				bomb.performAttack(player);
				
		}
	}
	
	public boolean inPlayerPos(Position aux) { //method implemented for the Bomb class
		return aux.isEqual(player.getPos());
	}
	
	public double ndd() { //produces and returns the next double of the Random object in Game
		double nextDouble = this.rand.nextDouble();
		return nextDouble;
	}
	
	
	
	

	

	public boolean isFinished() //COMENTAR
	{
		if (!finished && (this.playerWin() || this.aliensWin()))
			finished = true;
			
		return finished;
	}

	public void exit() { //COMENTAR
		finished = true;
	}

	@Override
	public String infoToString() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
