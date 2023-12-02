package tp1.logic.gameobjects;



import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;


public class Ufo extends EnemyShip{

	//TODO fill your code

//	private boolean enabled;
//	private Game game;
//	private int points = Game.UFO_POINTS;
//	private String appearance = Messages.UFO_SYMBOL;
//	private Move dir = Move.LEFT;
//	private int health = Game.UFO_HEALTH;
//	private Position pos;
	
	//TODO fill your code
	public Ufo(Game game) {
		super(game, new Position(Game.DIM_X, 0), Game.UCM_HEALTH, Game.UFO_POINTS);
//		this.pos = ;
//		this.enabled = false;
//		this.game = game;
		
	}

	public void computerAction() {
		if(!isAlive() && canGenerateRandomUfo()) {
			enable();
		}
	}
	
	
	
	private void enable() { //AlienManager
		this.pos = new Position(Game.DIM_X, 0);
		life = 1;
//		this.enabled = true;
//		this.health = 1;
	}

	public void onDelete() {
		game.setShockwave(true);
		this.game.markPoints(this.points);
	}
	
	
	public void callUfo() {
		computerAction();
		if(isAlive()) {
			performMovement();
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
	
	public String getSymbol() {
		return Messages.UFO_SYMBOL + "[" + (life < 10 ? "0": "") + life + "]";
	}
	
//	public Position getPosition() {
//		return this.pos;
//	}
//	
//	public boolean Alive() {
//		return this.health > 0 && this.enabled;
//	}
	
	public void performMovement() {
		this.dir.updatePosition(this.pos);
		if (onBorder())
			this.onDelete();
	}
	
//	public boolean inPosition(Position pos1)
//	{
//		return this.pos.isEqual(pos1);
//	}
	
//	public boolean receiveAttack(Laser other) {
//		return this.isOnPosition(other.getPos());
//	}

	
	@Override
	protected boolean onBorder() {
		// TODO Auto-generated method stub
		return this.pos.getCol() == -1;
	}
	
	
	
	
	//IGNORE
	
	
	@Override
	public boolean performAttack(GameItem other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected int getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getArmor() {
		// TODO Auto-generated method stub
		return 0;
	}


	
	
	
//	public void hit(int damage) {
//		this.health -= damage;
//		if (this.health <= 0)
//		{
//			this.onDelete();
//			game.setShockwave(true);
//			
//		}
//			
//	}
	
	
	
	
	
}
