package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class AlienShip extends EnemyShip {
	protected AlienManager alienManager;
	protected boolean descended = false;

	public AlienShip(Game game, Position pos, int life, int points, AlienManager alienManager) {
		super(game, pos, life, points);
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
		if (game.getAliensMove()) { //
			prevPos = new Position(pos.getCol(), pos.getRow());
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
			if (onBorder() && !this.descended) {
				this.alienManager.shipOnBorder();
			}
			if (finalRow() && !this.alienManager.inFinalRow())
				this.alienManager.sendInFinalRow();
		}
	}
	
	//Sees if alien is in border (only side borders)
	@Override 
	public boolean onBorder() {
		return (this.pos.getCol() == 0 || this.pos.getCol() == Game.DIM_X - 1);
	}
	
	public boolean finalRow ()
	{
		return this.pos.getRow() == Game.DIM_Y - 1;
	}
	
	public boolean descended() {
		return this.alienManager.alreadyDescended();
	}
	
	public void callDead() {
		this.alienManager.alienDied();
	}
	
	@Override
	public void onDelete() {
		game.deleteObject(this);
		game.markPoints(getPoints());
	}
	
}