package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class Bomb extends EnemyWeapon {
	private Move dir = Move.DOWN;
	private Game game;
	private String appearance = Messages.BOMB_SYMBOL;
	private Position pos;
	private int damage = Game.DESTROYER_ALIEN_DAMAGE;
	private boolean active = true;
	private boolean inPlayerPos = false;
	
	public Bomb( Game game, Position pos, int life) {
		super(game, pos, life);

	}
	
//	public void automaticMove () {
//		if (this.active) {
//			performMovement(dir);
//			if(isOut())
//				die();q
//		}
//	}
//
//	
	
//	public int getDamage()
//	{
//		return this.damage;
//	}
//	
//	public boolean inPosition(Position pos) {
//		return this.pos.isEqual(pos);
//	}
//	
	
	// PERFORM ATTACK METHODS
	
	
	
	
//	private void die() { 
//		this.active = false;
//	}

//	private boolean isOut() { //
//		return this.pos.getRow() > Game.DIM_Y - 1;
//	}
//
//	private void performMovement(Move dir) {
//		dir.updatePosition(this.pos);
//		this.inPlayerPos = this.game.inPlayerPos(this.pos);  //implement in another place 
//	}
	
//	public boolean performAttack(UCMShip other) { //ASK -----------------------------------------------------------------
//		boolean attacked = false;
////		Position prevPos = new Position(this.pos.getCol() - this.dir.getX(), this.pos.getRow() - this.dir.getY());
//		if (this.pos.isEqual(other.getPos()) || (other.getDir() == Move.UP && prevPos.isEqual(other.getPos()))) {
//			attacked = weaponAttack(other);
//			this.die();
//		}
//		//TODO fill your code
//		return attacked;
//	}
//	
//	public boolean performAttack(Laser other) { //ASK -----------------------------------------------------------------
//		boolean attacked = false;
//		
//		if (this.pos.isEqual(other.getPos())) {
//			attacked = weaponAttack(other);
//			this.die();
//		}
//		//TODO fill your code
//		return attacked;
//	}
	
//	private boolean weaponAttack(UCMShip other) { //ASK RETURN TRUE -----------------------------------------------------------------------------------------------
//		return other.receiveAttack(this);	
//	}
//	
//	private boolean weaponAttack(Laser other) { //ASK RETURN TRUE -----------------------------------------------------------------------------------------------
//		return other.receiveAttack(this);	
//	}
	
	public String getAppearance() {
		return this.appearance;
	}
	
	
//	public boolean is_active() {
//		return this.active;
//	}
//	
//	public Position getPos() {
//		return this.pos;
//	}
	
	public boolean getInPlayerPos() {
		return this.inPlayerPos;
	}
	
	
}
