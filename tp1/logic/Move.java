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


	public int moveToString() {
		return 0;
	
	}
	
	public void updatePosition(Position pos){
		
		pos.setCol(pos.getCol() + this.getX()); //ask
		pos.setRow(pos.getRow() + this.getY());

	}
	
	static public Move getMovement (String mov)
	{
		Move move = null;
		if (mov.equals(Move.RIGHT.toString()) || mov.equals("LEFT") || //ASK
				mov.equals("LLEFT") || mov.equals("RRIGHT") ||
				mov.equals("DOWN") || mov.equals("UP") ||
				mov.equals("NONE"))
			move = Move.valueOf(mov);
		return move;
	}
	
	
	
}
