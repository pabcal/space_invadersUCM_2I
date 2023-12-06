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
	
	private void initializeOvni(GameObjectContainer container) {
		container.add(new Ufo(game));
	}
	
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

	private void costumedInitialization(GameObjectContainer container, InitialConfiguration conf) {
		for (String shipDescription : conf.getShipDescription()) {
			String[] words = shipDescription.toLowerCase().trim().split("\\s+");
			container.add(ShipFactory.spawnAlienShip(words[0], game,
					new Position(Integer.valueOf(words[1]), Integer.valueOf(words[2])), this));
			this.remainingAliens++;
		}
	}
		
	// INITIALIZER METHODS
	
	/**
	 * Initializes the list of regular aliens
	 * @return the initial list of regular aliens according to the current level
	 */
//	protected RegularAlienList initializeRegularAliens() {
//		int nRAliens = this.level.getNumOfRegAliens();
//		int rArows = this.level.getNumberOfRows();
//		RegularAlienList alienList = new RegularAlienList(nRAliens, game);
//		RegularAlien regAlien;
//		
//		for (int i = 0; i < rArows; ++i) {
//			for (int j = 0; j < nRAliens / rArows; ++j) {
//				Position auxPos = new Position(5 - j, 1 + i);
//				regAlien = new RegularAlien(game, auxPos, this);
//				alienList.add(regAlien);
//				this.remainingAliens++;
//			}
//		}
//		return alienList;
//	}

	/**
	 * Initializes the list of destroyer aliens
	 * @return the initial list of destroyer aliens according to the current level
	 */
//	protected  DestroyerAlienList initializeDestroyerAliens() {
//		int nDAliens = this.level.getNumberOfDesAliens();
//		DestroyerAlienList alienList = new DestroyerAlienList(nDAliens, game);
//		DestroyerAlien desAlien;
//		int colInit = ((level == Level.INSANE) ? 1 : 0);
//
//		for (int j = 0; j < nDAliens; ++j) {
//			Position auxPos = new Position(4 + colInit - j, 1 + level.getNumberOfRows());
//			desAlien = new DestroyerAlien(game, auxPos, this);
//			alienList.add(desAlien);
//			this.remainingAliens++; 
//
//		}
//		
//		return alienList;
//	}

	
	// CONTROL METHODS
		
	public void shipOnBorder() {
		if(!onBorder) {
			onBorder = true;
//			shipsOnBorder = remainingAliens;
		}
	}
	
	public void notOnBorder() {
		
		if(onBorder) {
			onBorder = false;
//			shipsOnBorder = 0;
		}
		 
		
	}
	
//	public void checkInBorder(boolean value) {
//		this.onBorder = this.onBorder || value;
//	}

	public boolean onBorder() {
		
		return onBorder;
	}
	
	public int getRemainingAliens()
	{
		return this.remainingAliens;
	}
	
	public void alienDied() {
		this.remainingAliens--;
	}
	
	public void setDescend(boolean value)
	{
		this.descend = value;
		
	}
	
	public void setAlreadyDescended(boolean value) {
		this.alreadyDescended = value;
	}
	
	public boolean alreadyDescended() {
		return this.alreadyDescended;
	}
	
	public boolean readyToDescend() {
		return this.descend;
	}
	
	public void sendInFinalRow() {
		this.inFinalRow = true;
	}
	
	public boolean inFinalRow()
	{
		return this.inFinalRow;
	}
	
	
}
