package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class AlienShip extends EnemyShip {
	protected AlienManager alienManager;
	protected boolean descended;

	public AlienShip(Game game, Position pos, int life, AlienManager alienManager) {
		super(game, pos, life);
		this.alienManager = alienManager;
		
		// TODO Auto-generated constructor stub
	}
	
	//Is in charge of descending the alien
	public  void descent() { 
		Move dir2 = Move.DOWN;
		dir2.updatePosition(this.pos);
		if (!this.alienManager.alreadyDescended())
			this.alienManager.setAlreadyDescended(true);
		descended = true;
	}
	
	//Performs movement of alien
	@Override
	public  void performMovement() {
		if (this.alienManager.readyToDescend()) {
			this.descent();
		}
		else {
			if (this.descended) {
				if (this.dir == Move.LEFT)
					this.dir = Move.RIGHT;
				else
					this.dir = Move.LEFT;
				if (this.alienManager.onBorder())
					this.alienManager.notOnBorder();
				this.descended = false;
			}
			this.dir.updatePosition(this.pos);
		}
		if (this.onBorder() && !this.descended) {
			this.alienManager.shipOnBorder();
		}
		if (this.onBorder() && !this.alienManager.inFinalRow())
			this.alienManager.sendInFinalRow();
	}
	
	//Sees if alien is in border (only side borders)
	@Override 
	public boolean onBorder() {
		return (this.pos.getCol() == 0 || this.pos.getCol() == Game.DIM_X - 1);
	}
}