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
		
		pos.setCol(pos.getCol() + this.getX()); //ask
		pos.setRow(pos.getRow() + this.getY());

	}
	
	/**
	 * Given a string it returns a Move object in case the string is valid.
	 *  Otherwise, it returns null.
	 * @param mov
	 * @return Move or null
	 */
	static public Move getMovement (String mov)
	{
		Move move = null;
		if (mov.equals(Move.RIGHT.toString()) || mov.equals(Move.LEFT.toString()) || //ASK
				mov.equals(Move.RRIGHT.toString()) || mov.equals(Move.LLEFT.toString()) ||
				mov.equals(Move.DOWN.toString()) || mov.equals(Move.UP.toString()) ||
				mov.equals(Move.NONE.toString()))
			move = Move.valueOf(mov);
		return move;
	}
	
	
	
}
