package tp1.logic.gameobjects;



import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;


public class Ufo extends EnemyShip{

	//TODO fill your code

	private boolean enabled;
	private Game game;
	private int points = Game.UFO_POINTS;
	private String appearance = Messages.UFO_SYMBOL;
	private Move dir = Move.LEFT;
	private int health = Game.UFO_HEALTH;
	private Position pos;
	
	//TODO fill your code
	public Ufo(Game game) {
		this.pos = new Position (Game.DIM_X, 0);
		this.enabled = false;
		this.game = game;
		
	}

	public void computerAction() {
		if(!enabled && canGenerateRandomUfo()) {
			enable();
		}
	}
	
	
	
	private void enable() { //AlienManager
		this.pos = new Position(Game.DIM_X, 0);
		this.enabled = true;
		this.health = 1;
	}

	public void onDelete() {
		this.enabled = false;
	}
	
	
	public void callUfo() {
		this.computerAction();
		if(this.Alive()) {
			this.performMovement();
		}
	}
	
	/**
	 * Checks if the game should generate an ufo.
	 * 
	 * @return <code>true</code> if an ufo should be generated.
	 */
	
	
	
	private boolean canGenerateRandomUfo(){
////		Random rand = ;
//		Level level = ;
		return this.game.ndd() < game.getLevel().getUfoFrequency(); //game.getRandom().nextDouble()
	}
	
	public String getAppearance() {
		return this.appearance + "[" + (this.health < 10 ? "0": "") + this.health + "]";
	}
	
	public Position getPosition() {
		return this.pos;
	}
	
	public boolean Alive() {
		return this.health > 0 && this.enabled;
	}
	
	public void performMovement() {
		this.dir.updatePosition(this.pos);
		if (this.pos.getCol() == -1)
			this.onDelete();
	}
	
	public boolean inPosition(Position pos1)
	{
		return this.pos.isEqual(pos1);
	}
	
	public boolean receiveAttack(Laser other) {
		return this.inPosition(other.getPos());
	}
	
	public void hit(int damage) {
		this.health -= damage;
		if (this.health <= 0)
		{
			this.onDelete();
			game.setShockwave(true);
			this.game.markPoints(this.points);
		}
			
	}
	
	
	
	
	
}
