package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public class SuperLaser extends UCMWeapon{

	public SuperLaser(GameWorld game) {
		super(game, new Position());
		this.damage = 2;
		Position auxPos = game.getPlayerPos();
		this.pos = new Position(auxPos.getCol(), auxPos.getRow() - 1);
		this.dir = Move.UP;
	}

	@Override
	public String getSymbol() {
		return "ǁǁ";
	}

	@Override
	public void computerAction() {
		// TODO Auto-generated method stub
		
	}

	

	
}

