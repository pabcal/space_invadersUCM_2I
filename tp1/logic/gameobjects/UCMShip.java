package tp1.logic.gameobjects;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.Game;
import tp1.logic.Move;

public class UCMShip extends Ship{
	
	private int health;
	private Position pos = new Position();
	Move dir = null;
	

	public UCMShip() {
		UCMShip(4, 7, Game.UCM_HEALTH);
		
	}

	private void UCMShip(int col, int row, int health)
	{
		this.health = health;
		this.pos.setCol(col);
		this.pos.setRow(row);
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
	
	public String getAppearance() {
		String appearance;
		if (this.health == 0)
			appearance = Messages.UCMSHIP_DEAD_SYMBOL;
		else 
			appearance = Messages.UCMSHIP_SYMBOL;
		return appearance;
	}
	
	public Position getPos() {
		return this.pos;
	}
	
	public void setPos(int col, int row) {
		this.pos.setCol(col);
		this.pos.setRow(row);
	}
	
	public int getHealth()
	{
		return this.health;
	}
	
	public boolean performMovement(Move dir)
	{
		boolean performed = false;
		this.dir = dir;
		
		if (this.pos.getCol() + dir.getX() >= 0 && this.pos.getCol() + dir.getX() < Game.DIM_X &&
				this.pos.getRow() + dir.getY() >= 0 && this.pos.getRow() + dir.getY() < Game.DIM_Y	) {
			dir.updatePosition(this.pos);
			performed = true;
		}	
		return performed;
	}
	
	
	public void setHealthToZero()
	{
		this.health = 0;
	}
	
	public boolean receiveAttack(Bomb bomb) {
		this.getHit(bomb.getDamage());
		return true;
	}
	
	public void getHit(int damage){
		this.health -= damage;
	}
	
	
	public Move getDir() {
		return this.dir;
	}
		
}
























