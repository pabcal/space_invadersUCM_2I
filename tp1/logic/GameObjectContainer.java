package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameObject;

public class GameObjectContainer {

	private List<GameObject> objects;

	public GameObjectContainer() {
		objects = new ArrayList<>();
	}

	public void add(GameObject object) {
		objects.add(object);
	}

	public void remove(GameObject object) {
		objects.remove(object);
	}

	public void automaticMoves() {
		int size = objects.size();
		for (int i = size - 1; i >= 0; i--) {
				GameObject object = objects.get(i);
				object.automaticMove();
			//TODO fill with your code
		}
		//TODO fill with your code
	}
	
	public GameObject getInPos(Position pos)
	{
		GameObject objectInPos = null;
		int i = 0;
		boolean found = false;
		while (i < getSize() && !found)
		{
			objectInPos = objects.get(i);
			found = objectInPos.isOnPosition(pos);
			i++;
		}
		return  found ? objectInPos: null;
			
	}
	
	public void computerActions() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).computerAction();
		}	
	}

	public int getSize() {
		return objects.size();
	}
	
	public boolean objectIsDead(int index) {
		return !objects.get(index).isAlive();
	}
	
	public GameObject getObject(int index) {
		return objects.get(index);
	}
	//TODO fill with your code
	
	
	
	public String getSymbolInPos(Position pos) {
		int i = 0;
		boolean found = false;
		GameObject aux = null;
		while (i < objects.size() && !found) {
			aux = objects.get(i);
			if (pos.isEqual(aux.getPos()))
				found = true;
			++i;
		}
		
		return (!found ? "" : aux.getSymbol());
	}
	
}
