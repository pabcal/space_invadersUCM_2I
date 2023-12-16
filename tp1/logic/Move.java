package tp1.logic;


/**
 * Represents the allowed movements in the game
 *
 */
public enum Move {
	LEFT(-1,0), LLEFT(-2,0), RIGHT(1,0), RRIGHT(2,0), DOWN(0,1), UP(0,-1), NONE(0,0);
	
	private int x;
	private int y;
	
	private Move(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	/**
	 * Given a position, it updates that same position using the attributes of the object (x and y)
	 * @param pos
	 */
	public void updatePosition(Position pos){
		
		pos.setCol(pos.getCol() + getX()); 
		pos.setRow(pos.getRow() + getY());

	}
	
	/**
	 * Given a string it returns a Move object in case the string is valid.
	 *  Otherwise, it returns null.
	 * @param mov
	 * @return Move or null
	 */
	static public Move getMovement (String mov) throws IllegalArgumentException
	{
		Move move = null;
		try {
		move = Move.valueOf(mov);
		}
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
		return move;
	}
	
	
	
}
