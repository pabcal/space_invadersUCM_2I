package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.ShipFactory;
import tp1.logic.gameobjects.Ufo;

/**
 * 
 * Manages alien initialization and
 * used by aliens to coordinate movement
 *
 */
public class AlienManager {
	
	private Level level;
	private Game game;
	private int remainingAliens;
	
//	
//	private int shipsOnBorder; //not used
	private boolean onBorder;
	private boolean descend = false;
	private boolean alreadyDescended = false;
	private boolean inFinalRow;

	public AlienManager(Game game, Level level) {
		this.level = level;
		this.game = game;
		this.remainingAliens = 0;
	}
	
	/**
	 * Initializes the game.container GameObjectContainer and the aliens of the game. Depending on the 
	 * input config it will initialize the game normally (depending on the three levels of the game) or customized.
	 * @param config
	 * @return GameObjectContainer
	 */
	public  GameObjectContainer initialize(InitialConfiguration config) {
		this.remainingAliens = 0;
		GameObjectContainer container = new GameObjectContainer();
		
		initializeOvni(container);
		if (config == null) {
			initializeRegularAliens(container);
			initializeDestroyerAliens(container);
		}
		else
			costumedInitialization(container, config);
		//TODO fill with your code
		
		
		return container;
	}
	
	/**
	 * Initializes the UFO in the game and adds it to the container.
	 * @param container
	 */
	private void initializeOvni(GameObjectContainer container) {
		container.add(new Ufo(game));
	}
	
	/**
	 * Initializes the regular aliens of the game and adds them to the container.
	 * @param container
	 */
	private void initializeRegularAliens (GameObjectContainer container) {
		int nRAliens = level.getNumOfRegAliens();
		int rArows = level.getNumberOfRows();
		RegularAlien regAlien;
		
		for (int i = 0; i < rArows; ++i) {
			for (int j = 0; j < nRAliens / rArows; ++j) {
				Position auxPos = new Position(5 - j, 1 + i);
				regAlien = new RegularAlien(game, auxPos, this);
				container.add(regAlien);
				this.remainingAliens++;
			}
		}
		
		//TODO fill with your code
		//		container.add(new RegularAlien(....));
	}
	
	/**
	 * Initializes the destroyer aliens of the game and adds them to the container. 
	 * @param container
	 */
	private void initializeDestroyerAliens(GameObjectContainer container) {
		int nDAliens = this.level.getNumberOfDesAliens();
		DestroyerAlien desAlien;
		int colInit = ((level == Level.INSANE) ? 1 : 0);
		
		for (int j = 0; j < nDAliens; ++j) {
			Position auxPos = new Position(4 + colInit - j, 1 + level.getNumberOfRows());
			desAlien = new DestroyerAlien(game, auxPos, this);
			container.add(desAlien);
			this.remainingAliens++; 
		}
	}

	/**
	 * Initializes the corresponding aliens of the game (RegularAlien, DestroyerAlien, ExplodingShip) depending on the configuration given by InitialConfiguration conf
	 * @param container
	 * @param conf
	 */
	private void costumedInitialization(GameObjectContainer container, InitialConfiguration conf) {
		for (String shipDescription : conf.getShipDescription()) {
			String[] words = shipDescription.toLowerCase().trim().split("\\s+");
			container.add(ShipFactory.spawnAlienShip(words[0], game,
					new Position(Integer.valueOf(words[1]), Integer.valueOf(words[2])), this));
			this.remainingAliens++;
		}
	}
	
	// CONTROL METHODS
		
	/**
	 * Sets the boolean attribute onBorder to true
	 */
	public void shipOnBorder() {
		if(!onBorder) {
			onBorder = true;
//			shipsOnBorder = remainingAliens;
		}
	}
	
	/**
	 * Sets the boolean attribute onBorder to false
	 */
	public void notOnBorder() {
		
		if(onBorder) {
			onBorder = false;
//			shipsOnBorder = 0;
		}
		 
		
	}
	
//	public void checkInBorder(boolean value) {
//		this.onBorder = this.onBorder || value;
//	}
	
	/**
	 * Getter method for onBorder (boolean) attribute
	 * @return onBorder
	 */
	public boolean onBorder() {
		
		return onBorder;
	}
	
	/**
	 * Getter method for remainingAliens (int) attribute
	 * @return reaminingAliens
	 */
	public int getRemainingAliens()
	{
		return this.remainingAliens;
	}
	
	/**
	 * Substracts 1 from remainingAliens (int) attribute. Called when an aliens dies.
	 */
	public void alienDied() {
		this.remainingAliens--;
	}
	
	/**
	 * Setter method for descend (boolean) attribute.
	 * @param value (boolean)
	 */
	public void setDescend(boolean value)
	{
		this.descend = value;
		
	}
	
	/**
	 * Setter method for alreadyDescended (boolean) attribute.
	 * @param value (boolean)
	 */
	public void setAlreadyDescended(boolean value) {
		this.alreadyDescended = value;
	}
	
	/**
	 * Getter method for alreadyDescended (boolean) attribute.
	 * @return alreadyDescended
	 */
	public boolean alreadyDescended() {
		return this.alreadyDescended;
	}
	
	/**
	 * Getter method for descend (boolean) attribute.
	 * @return descend
	 */
	public boolean readyToDescend() {
		return this.descend;
	}
	
	/**
	 * Sets inFinalRow (boolean) to true.
	 */
	public void sendInFinalRow() {
		this.inFinalRow = true;
	}
	
	/**
	 * Getter method for inFinalRow (boolean) attribute.
	 * @return inFinalRow
	 */
	public boolean inFinalRow()
	{
		return this.inFinalRow;
	}
	
	
}
