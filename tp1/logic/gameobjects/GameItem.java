package tp1.logic.gameobjects;

import tp1.logic.Position;

public interface GameItem {
	
	
	public boolean performAttack(GameItem other, boolean cross);
	
	public boolean receiveAttack(UCMWeapon weapon);
	public boolean receiveAttack(EnemyWeapon weapon);

	public boolean isAlive();
	public boolean isOnPosition(Position pos);
	public boolean theyCrossed(GameObject obj);

}
