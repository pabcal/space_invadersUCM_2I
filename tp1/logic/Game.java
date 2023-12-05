package tp1.logic;

import java.util.Random;

import tp1.logic.gameobjects.Laser;
import tp1.logic.gameobjects.UCMShip;
import tp1.logic.gameobjects.UCMWeapon;
import tp1.logic.gameobjects.Ufo;
import tp1.logic.gameobjects.Bomb;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.Shockwave;
import tp1.logic.gameobjects.SuperLaser;
import tp1.logic.lists.DestroyerAlienList;
import tp1.logic.lists.RegularAlienList;
import tp1.view.Messages;

//changes 1.1: UCMLaser integration to board. Changes in UCMLaser class and in Game class, referred in code with comments.
//In class Game UCMLaser object was added and method positionToString was modified so that the laser could be displayed in board 



// TODOimplementarlo
public class Game implements GameStatus, GameModel, GameWorld{
	
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
	public static final int SHOCKWAVE_DAMAGE = 1;

	
	
	private int cycle = 0;
	private Level level; 
	private long seed;
	private UCMShip player = new UCMShip(this);
	private Laser laser; //changes 1.1
	private SuperLaser superLaser;
	private AlienManager manager; 
//	private RegularAlienList rAlienList;
//	private DestroyerAlienList dAlienList;
	private Random rand;
//	private boolean shockwave = false;
	private int alienCycleCounter = 0;
	private boolean finished = false;
	private Shockwave shockwave = new Shockwave(this);
	boolean enableLaser = false;
	boolean enableSuperLaser = false;
	boolean reseted = false;
	private GameObjectContainer container;

	
	
	
	private int score = 0;
	//TODO fill your code

	public Game(Level level, long seed) {
		this.level = level;
		this.seed = seed;
		this.manager = new AlienManager (this, level);
		this.rand = new Random(this.seed);
		init();
		
		
	}
	
	private void init() { //nada
		container = manager.initialize();
		container.add(player);
//		this.rAlienList  = this.manager.initializeRegularAliens();
//		this.dAlienList = this.manager.initializeDestroyerAliens();
//		this.ufo = new Ufo(this);
//		for (int i = 0; i < rAlienList.getNum(); ++i)
//			container.add(rAlienList.getAlien(i));
	}

	public String stateToString() { //status
		StringBuilder buffer = new StringBuilder();
		buffer
		.append("Life: ").append(player.getLife()).append(System.lineSeparator())
		.append("Points: ").append(score).append(System.lineSeparator())
		.append("Shockwave: ").append((shockwave.getShockwaveStatus() ? "ON" : "OFF")).append(System.lineSeparator());
		return buffer.toString();

	}

	public int getCycle() { //status
		return this.cycle;
	}

	public int getRemainingAliens() { //used in game printer - status
		return this.manager.getRemainingAliens();
	}

	public String positionToString(int col, int row) { //status
		String what;
		Position posAux = new Position(col, row);
//		RegularAlien auxRAlien = this.rAlienList.anObjectInPos(posAux);
//		DestroyerAlien auxDAlien = this.dAlienList.anObjectInPos(posAux);
//		Bomb auxBomb = this.dAlienList.searchBombInPos(posAux);
//		
//		if(auxRAlien != null)
//			what = auxRAlien.getSymbol();
//		else if (auxDAlien != null)
//			what = auxDAlien.getSymbol();
//		else if (posAux.isEqual(this.player.getPos()))
//			what = player.getSymbol();
//		else if (laser != null && this.laser.isAlive() && posAux.isEqual(this.laser.getPos())) 
//			what = this.laser.getSymbol();
//		else if (this.superLaser != null && this.superLaser.isAlive() && posAux.isEqual(this.superLaser.getPos()) )
//			what = superLaser.getSymbol();
//		else if (auxBomb != null)
//			what = auxBomb.getSymbol();
//		else if (posAux.isEqual(this.ufo.getPos()) && this.ufo.isAlive()) 
//			what = this.ufo.getSymbol();
//		else
//			what = "";
		
		what = container.getSymbolInPos(posAux);
		
		
		return what;
	}

	public boolean playerWin() { //status
		return this.manager.getRemainingAliens() == 0;
	}

	public boolean aliensWin()  //status
	{
//		if (this.rAlienList.anObjectInPos(getPlayerPos()) != null ||
//				this.dAlienList.anObjectInPos(getPlayerPos()) != null ||
//				this.ufo.isOnPosition(getPlayerPos()))
//		this.player.setHealthToZero();
		return (!this.player.isAlive() || this.manager.inFinalRow());
		
	}

	
	public boolean enableLaser() { //enables UCMShip's laser - model
		boolean enabled = false;
		if ((this.laser == null || !this.laser.isAlive()) && (this.superLaser == null || !this.superLaser.isAlive())) {
			enableLaser = true;
			enabled = true;
		}
		return enabled;
	}
	
	public boolean enableSuperLaser() { //enables UCMShip's laser - model
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
	
	

//	public Random getRandom() { //ASK -----------------------------------------------------------
//		
//		return this.rand;
//	}
	
	
	public void incrCycle() { //nada
		++this.cycle;
		++this.alienCycleCounter;
	}
	
	public Position getPlayerPos() { //world
		return this.player.getPos();
	}
	
//	private void laser_move() {
//		if (this.laser.isAlive())
//			this.laser.automaticMove();
//	}
//	
//	private void SuperLaser_move() {
//		if (this.superLaser.isAlive())
//			this.superLaser.automaticMove();
//	}
	
	private void manageAliens() { //nada
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
	
	 /* 
	  Game.processHIT() is an important method in our implementation of the game. It is centered in the laser and the different objects with which it can collide in the game.
	  It is called 2 times in the Game.update() method, one after the laser moves and other when the aliens/bombs move. In some cases we want to check aliens/bombs did not cross each other 
	  vertically, which is the reason it is called two times. However, we just want the processHIT to check if they crossed each other vertically, which is why when aliens move horizontally 
	  the function must not check aliens collision with the laser the first time after the laser moves, only the second time when both aliens and laser moved. This is the reason why there is a boolean parameter
	  aliensCheck that indicates the function to check laser collision with aliens or not. Laser collision with bombs is checked always as both objects move always vertically in opposite directions. 
	  
	  
	 */
	private void processHIT(UCMWeapon weapon) //nada
	{
//		RegularAlien alien = null;
//		DestroyerAlien dAlien = null;
//		Bomb bomb = null;
		int i = 0;
		if (weapon != null && weapon.isAlive()) {
			while (i < container.getSize() && weapon.isAlive()) {
				weapon.performAttack(container.getObject(i), true);
//				if(!container.getObject(i).isAlive())
//					manager.alienDied();
				++i;
			}
			i = 0;
			while (i < container.getSize() && weapon.isAlive()) {
				weapon.performAttack(container.getObject(i), false);
//				if(!container.getObject(i).isAlive())
//					manager.alienDied();
				++i;
			}
//		if (laser != null && laser.isAlive()) {
//			while (i < this.dAlienList.getNum() && laser.isAlive()) {
//				bomb = this.dAlienList.getBombFrom(i);
//				if (bomb != null && bomb.isAlive())
//					bomb.performAttack(laser);
//				++i;
//			}
//			i = 0;
//			while (aliensCheck && i < this.rAlienList.getNum() && (laser.isAlive())) { // || superLaser.isAlive()
//				alien = this.rAlienList.getAlien(i);
//				if (laser.isAlive())
//					this.laser.performAttack(alien);
//				else if (superLaser.isAlive())
//					this.superLaser.performAttack(alien);
//				++i;
//			}
//			i = 0;
//			while (aliensCheck && i < this.dAlienList.getNum() && (laser.isAlive())) { // || superLaser.isAlive()
//				dAlien = this.dAlienList.getAlien(i);
//				if (laser.isAlive())
//					this.laser.performAttack(dAlien);
//				else if (superLaser.isAlive())
//					this.superLaser.performAttack(alien);
//				++i;
//			}
//			if ((laser.isAlive())) // || superLaser.isAlive()
//				if (laser.isAlive())
//					this.laser.performAttack(this.ufo);
//				else
//					this.superLaser.performAttack(this.ufo);
					
		}
	}
		
		
	
	
	public boolean movePlayer(Move dir) { //model
		boolean did = false;
		if (dir != null && dir != Move.UP && dir != Move.DOWN)
			did = player.performMovement(dir);
		return did;
	}
	
	
//	private void removeDead() { //removes dead aliens and laser if is not active
//		this.rAlienList.removeDead();
//		this.dAlienList.removeDead();
//		if (!laser.isAlive())
//			laser = null;
//		for (int i = 0; i < container.getSize(); ++i) 
//			if (container.objectIsDead(i)) {
//				System.out.println(i);
//				container.remove(container.getObject(i));
//		}
//	}
	
	public void listCommand() //model
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
	
//	public long getSeed() {
//		return this.seed;
//	}
	
	public Level getLevel() { //world
		return this.level;
	}

	public void reset() //model
	{
		this.score = 0;
		this.cycle = 0;
		this.laser = null;
		this.manager = null;
		this.manager = new AlienManager(this, this.level);
		container = null;
		this.player = new UCMShip(this);
		init();
		this.rand = new Random(this.seed);
		this.alienCycleCounter = 1;
		reseted = true;
	}
	
	
//	private void shootBombs() {
//		this.dAlienList.automaticShoot();
//	}
//	
//	private void processBombs() {
////		this.dAlienList.bombProcess();
//		this.shootBombs();
//	}
	
	public void update() { //nada
		if (!reseted) {
			if (laser != null && !laser.isAlive())
				laser = null;
			if (superLaser != null && !superLaser.isAlive()) //should be else if i think
				superLaser = null;
			container.automaticMoves();
			manageAliens();
			container.computerActions();
			
			
			
			 //boolean variable that tells the processHit() to check collision between the laser and the aliens, it is true when aliens are ready to descend only
	//		this.ufo.callUfo(); // this method is in charge of performing all UFO movements as well as checking if it must appear or not
	
			if (laser == null && enableLaser) {
				laser = new Laser(this);
				container.add(laser);
			}
			
			else if (superLaser == null && enableSuperLaser)
			{
				superLaser = new SuperLaser(this);
				container.add(superLaser);
			}
			
			
			UCMWeapon weapon;
			if (laser != null)
				weapon = laser;
			else if (superLaser != null)
				weapon = superLaser;
			else
				weapon = null;
				
				
			
			enableLaser = enableSuperLaser = false;
			processHIT(weapon); //method in charge of checking laser collision with ay other object in the board. This is only the first calling, laser already moved but aliens and bombs did not
	//		if ((this.alienCycleCounter == (this.level.getSpeed() + 1))) { //Check if aliens must move or not in this cycle
	//			this.alienCycleCounter = 0;
	//			this.moveAliens(); //method to move aliens
	//		}
	//		this.processBombs(); //Method in charge of shooting/moving bombs
			
	//		this.processHIT(true); //Second calling of the processHIT() method. Laser and aliens already moved.
			this.playerProcessHit(); //In charge of checking collisions between bombs and player, no need to call 2 times.
			incrCycle();
		}
		else
			reseted = false;
	}
	// changes 
	public void enableShockwave() //world
	{
		shockwave.setShockwave(true);
	}
	
	
	//changes
	public boolean shootShockwave() { //model
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
	
	
	public void markPoints(int points) { //world
		this.score += points;
	}
	
	
	/*
	 Game.playerProcessHit() is the method concerning the collision and damage effects between the bombs and the player. Is important to notice that
	 a difference between this function and the laser�s Game.processHIT() is that is not called two times. This is due to the implementation of Bomb.performAttcak(UCMShip other)
	 that allows us to call it only once.
	*/
	private void playerProcessHit() { //nada
		
		for (int i = 0; i < container.getSize(); ++i) {
			GameObject obj = container.getObject(i);
			if (!obj.performAttack(player, true))
				obj.performAttack(player, false);
		}
//		for (int j = 0; j < this.dAlienList.getNum(); ++j) {
//			bomb = this.dAlienList.getBombFrom(j);
//			if (bomb != null && bomb.isAlive())
//				bomb.performAttack(player);
//				
//		}
	}
	
//	public boolean inPlayerPos(Position aux) { //method implemented for the Bomb class
//		return aux.isEqual(player.getPos());
//	}
	
	public double ndd() { //produces and returns the next double of the Random object in Game - world
		double nextDouble = this.rand.nextDouble();
		return nextDouble;
	}
	
	
	
	public boolean getAliensMove() { //world
		return (this.alienCycleCounter == (this.level.getSpeed() + 1));
	}

	

	public boolean isFinished() //COMENTAR - model
	{
		if (!finished && (this.playerWin() || this.aliensWin()))
			finished = true;
			
		return finished;
	}

	public void exit() { //COMENTAR - model
		finished = true;
	}

	@Override
	public String infoToString() { // status
		// TODO Auto-generated method stub
		return null;
	}
	
	public GameObject objectsExploded (Position pos)
	{
		return container.getInPos(pos);
	}
	
	
	
	
	
	
	
	

	
	public void addObject(GameObject obj) { //world
		container.add(obj);
	}
	
	public void deleteObject(GameObject obj) { //world
		container.remove(obj);
	}
	
	
	
	
	
}

