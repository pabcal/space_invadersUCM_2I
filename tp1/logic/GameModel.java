package tp1.logic;

public interface  GameModel {
	public void exit();
	public boolean isFinished();
	public void listCommand();
	public boolean movePlayer(Move dir);
	public void reset();
	public boolean shootShockwave();
	public boolean enableLaser();
	public boolean enableSuperLaser();
}
