package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.ShipFactory;
import tp1.logic.gameobjects.Ufo;
import tp1.view.Messages;

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
	 * @throws InitializationException 
	 */
	public  GameObjectContainer initialize(InitialConfiguration config) throws InitializationException {//throws InitializationException
		remainingAliens = 0;
		GameObjectContainer container = new GameObjectContainer();
		
		initializeOvni(container);
		if (config == InitialConfiguration.NONE) {
			initializeRegularAliens(container);
			initializeDestroyerAliens(container);
		}
		else
				costumedInitialization(container, config);
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
		int nDAliens = level.getNumberOfDesAliens();
		DestroyerAlien desAlien;
		int colInit = ((level == Level.INSANE) ? 1 : 0);
		
		for (int j = 0; j < nDAliens; ++j) {
			Position auxPos = new Position(4 + colInit - j, 1 + level.getNumberOfRows());
			desAlien = new DestroyerAlien(game, auxPos, this);
			container.add(desAlien);
			remainingAliens++; 
		}
	}

	/**
	 * Initializes the corresponding aliens of the game (RegularAlien, DestroyerAlien, ExplodingShip) depending on the configuration given by InitialConfiguration conf
	 * @param container
	 * @param conf
	 */
	private void costumedInitialization(GameObjectContainer container, InitialConfiguration conf) throws InitializationException  { //throws InitializationException 
		for (String shipDescription : conf.getShipDescription()) {
			String[] words = shipDescription.toLowerCase().trim().split("\\s+");
			if (!words[0].equals("r") && !words[0].equals("e") && !words[0].equals("d"))
				throw new InitializationException("Unknown ship: " + "'" +  words[0] + "'");
			if (words.length != 3)
				throw new InitializationException("Incorrect entry " + "'" + words[0].toUpperCase() + " " + words[1] + "'." + " Insufficient parameters." );
			try {
				int col = Integer.valueOf(words[1]) , row = Integer.valueOf(words[2]);
				if (col < 0 || col >= game.DIM_X || row  < 0 || row >= game.DIM_Y)
					throw new InitializationException("Position " + "(" + words[1] + " , " + words[2] + ")" + " is off board" );
			container.add(ShipFactory.spawnAlienShip(words[0], game,
					new Position(col, row), this));
			}
			catch (NumberFormatException e)
			{
				throw new InitializationException("Invalid position " + "(" + words[1] + " , " + words[2] + ")");
			}
				
			
			
			
			
			
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

		}
		 
		
	}
	

	
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
		return remainingAliens;
	}
	
	/**
	 * Substracts 1 from remainingAliens (int) attribute. Called when an aliens dies.
	 */
	public void alienDied() {
		remainingAliens--;
	}
	
	/**
	 * Setter method for descend (boolean) attribute.
	 * @param value (boolean)
	 */
	public void setDescend(boolean value)
	{
		descend = value;
		
	}
	
	/**
	 * Setter method for alreadyDescended (boolean) attribute.
	 * @param value (boolean)
	 */
	public void setAlreadyDescended(boolean value) {
		alreadyDescended = value;
	}
	
	/**
	 * Getter method for alreadyDescended (boolean) attribute.
	 * @return alreadyDescended
	 */
	public boolean alreadyDescended() {
		return alreadyDescended;
	}
	
	/**
	 * Getter method for descend (boolean) attribute.
	 * @return descend
	 */
	public boolean readyToDescend() {
		return descend;
	}
	
	/**
	 * Sets inFinalRow (boolean) to true.
	 */
	public void sendInFinalRow() {
		inFinalRow = true;
	}
	
	/**
	 * Getter method for inFinalRow (boolean) attribute.
	 * @return inFinalRow
	 */
	public boolean inFinalRow()
	{
		return inFinalRow;
	}
	
	
}
