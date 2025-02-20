package tp1.logic;

/**
 * 
 * Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	private int col;
	private int row;
	
	
	public Position() {
		this(0, 0);
	}
	

	public Position(int col, int row) {
		this.row = row;
		this.col = col;
	}
	
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	/**
	 * Returns true if pos2 has same attributes that this object Position.
	 * @param pos2
	 * @return boolean
	 */
	public boolean isEqual(Position pos2) {
		return col == pos2.getCol() && row == pos2.getRow();
	}
	
	

	
	//TODO fill your code

}
