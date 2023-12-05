package tp1.logic;

import tp1.logic.gameobjects.GameObject;

public interface GameWorld {
	public Position getPlayerPos();
	public Level getLevel();
	public void enableShockwave();
	public void markPoints(int points);
	public boolean getAliensMove();
	public void addObject(GameObject obj);
	public void deleteObject(GameObject obj);
	public double ndd();
	public boolean aliensWin();
	public GameObject objectsExploded(Position pos);
}
