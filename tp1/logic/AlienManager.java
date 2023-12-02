package tp1.logic;

import tp1.logic.gameobjects.DestroyerAlien;
//import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.lists.DestroyerAlienList;
//import tp1.logic.lists.DestroyerAlienList;
import tp1.logic.lists.RegularAlienList;

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
	
	
	private int shipsOnBorder; //not used
	private boolean onBorder;
	private boolean descend = false;
	private boolean alreadyDescended = false;
	private boolean inFinalRow;

	public AlienManager(Game game, Level level) {
		this.level = level;
		this.game = game;
		this.remainingAliens = 0;
	}
		
	// INITIALIZER METHODS
	
	/**
	 * Initializes the list of regular aliens
	 * @return the initial list of regular aliens according to the current level
	 */
	protected RegularAlienList initializeRegularAliens() {
		int nRAliens = this.level.getNumOfRegAliens();
		int rArows = this.level.getNumberOfRows();
		RegularAlienList alienList = new RegularAlienList(nRAliens, game);
		RegularAlien regAlien;
		
		for (int i = 0; i < rArows; ++i) {
			for (int j = 0; j < nRAliens / rArows; ++j) {
				Position auxPos = new Position(5 - j, 1 + i);
				regAlien = new RegularAlien(game, auxPos, this);
				alienList.add(regAlien);
				this.remainingAliens++;
			}
		}
		return alienList;
	}

	/**
	 * Initializes the list of destroyer aliens
	 * @return the initial list of destroyer aliens according to the current level
	 */
	protected  DestroyerAlienList initializeDestroyerAliens() {
		int nDAliens = this.level.getNumberOfDesAliens();
		DestroyerAlienList alienList = new DestroyerAlienList(nDAliens, game);
		DestroyerAlien desAlien;
		int colInit = ((level == Level.INSANE) ? 1 : 0);

		for (int j = 0; j < nDAliens; ++j) {
			Position auxPos = new Position(4 + colInit - j, 1 + level.getNumberOfRows());
			desAlien = new DestroyerAlien(game, auxPos, this);
			alienList.add(desAlien);
			this.remainingAliens++; 

		}
		
		return alienList;
	}

	
	// CONTROL METHODS
		
	public void shipOnBorder() {
		if(!onBorder) {
			onBorder = true;
			shipsOnBorder = remainingAliens;
		}
	}
	
	public void notOnBorder() {
		
		if(onBorder) {
			onBorder = false;
			shipsOnBorder = 0;
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
