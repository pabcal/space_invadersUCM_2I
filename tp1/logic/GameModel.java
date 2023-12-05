package tp1.logic;

import tp1.control.InitialConfiguration;

public interface  GameModel {
	public void exit();
	public boolean isFinished();
	public void listCommand();
	public boolean movePlayer(Move dir);
	public void reset(InitialConfiguration config);
	public boolean shootShockwave();
	public boolean enableLaser();
	public boolean enableSuperLaser();
}
