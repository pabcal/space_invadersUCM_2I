package tp1.logic.gameobjects;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Move;

public class UCMShip extends Ship{
	
	

	public UCMShip(GameWorld game) {
		super(game, new Position(4, 7), Game.UCM_HEALTH);
		armor = 0; // change
	}
	
	
	/**
	 Getter method for the UCMShip's symbol. If aliens won the game for any reason 
	 the UCMShip will return its dead symbol. Otherwise it will return its normal symbol.
	 @return String 
	*/
	@Override
	public String getSymbol() {
		String appearance;
		if (game.aliensWin())
			appearance = Messages.UCMSHIP_DEAD_SYMBOL;
		else 
			appearance = Messages.UCMSHIP_SYMBOL;
		return appearance;
	}
	
	
	/**
	 Method in charge of performing UCMShip's movement given a direction of type Move. 
	 @return If the resulting position of the movement is outside the boundaries of the board, 
	 then the movement will not be effective and the function will return false. Otherwise,
	 it will return true.
	 * */
	public boolean performMovement(Move dir)
	{
		boolean performed = false;
		this.dir = dir;
		prevPos = new Position(pos.getCol(), pos.getRow());
		if (pos.getCol() + dir.getX() >= 0 && pos.getCol() + dir.getX() < Game.DIM_X &&
				pos.getRow() + dir.getY() >= 0 && pos.getRow() + dir.getY() < Game.DIM_Y	) {
			dir.updatePosition(pos);
			performed = true;
		}	
		return performed;
	}
	
	
	
	/**
	 Method to receive attack from EnemyWeapon. Calls hit() method.
	 @return always return True
	 */
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		hit(weapon);
		return true;
	}

	
	/**
	 Getter method for the direction type Move. Returns attribute dir (Move).
	 */
	public Move getDir() {
		return dir;
	}
	
	
	

	
	////////////////////////////////////////////////////////////////////////////////////
	//EMPTY METHODS
	
	
	@Override
	public boolean performAttack(GameItem other, boolean cross) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 
	 
	 
	 */
	@Override
	protected int getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	/*
	 
	 
	 
	 */
	@Override
	protected void performMovement() {
		// TODO Auto-generated method stub
		
	}

	
	/*
	 
	 
	 
	 */
	@Override
	public void automaticMove() {
		// TODO Auto-generated method stub
		
	}

	/*
	 
	 
	 
	 */
	@Override
	protected boolean onBorder() {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		// TODO Auto-generated method stub
		return false;
	}
		
}
























