package tp1.logic.gameobjects;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.Game;
import tp1.logic.Move;

public class UCMShip extends Ship{
	
	

	public UCMShip(Game game) {
		super(game, new Position(4, 7), Game.UCM_HEALTH);
		armor = 0; // change
	}


//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
	
	@Override
	public String getSymbol() {
		String appearance;
		if (this.life == 0)
			appearance = Messages.UCMSHIP_DEAD_SYMBOL;
		else 
			appearance = Messages.UCMSHIP_SYMBOL;
		return appearance;
	}
//	
//	public Position getPos() {
//		return this.pos;
//	}
	
//	public void setPos(int col, int row) {
//		this.pos.setCol(col);
//		this.pos.setRow(row);
//	}
	
//	public int getHealth()
//	{
//		return this.health;
//	}
	
	public boolean performMovement(Move dir)
	{
		boolean performed = false;
		this.dir = dir;
		prevPos = new Position(pos.getCol(), pos.getRow());
		if (this.pos.getCol() + dir.getX() >= 0 && this.pos.getCol() + dir.getX() < Game.DIM_X &&
				this.pos.getRow() + dir.getY() >= 0 && this.pos.getRow() + dir.getY() < Game.DIM_Y	) {
			dir.updatePosition(this.pos);
			performed = true;
		}	
		return performed;
	}
	
	public boolean isDead() {
		return getLife() == 0;
	}
	
	public void setHealthToZero()
	{
		die();
	}
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		hit(weapon);
		return true;
	}
	
//	public void getHit(int damage){
//		this.health -= damage;
//	}
	
	
	public Move getDir() {
		return this.dir;
	}
	
	
	

	@Override
	public boolean performAttack(GameItem other, boolean cross) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	protected int getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	protected void performMovement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void automaticMove() {
		// TODO Auto-generated method stub
		
	}

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
























