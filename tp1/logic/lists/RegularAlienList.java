package tp1.logic.lists;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.gameobjects.RegularAlien;


/**
 * Container of regular aliens, implemented as an array with a counter
 * It is in charge of forwarding petitions from the game to each regular alien
 * 
 */
public class RegularAlienList {

	private RegularAlien[] objects;
	private int num = 0;
	private int capacity;
	private Game game;
	
	
	public RegularAlienList(int size, Game game) {
		this.objects = new RegularAlien[size];
		this.capacity = size;
		this.game = game;
	}
	

	public void add(RegularAlien regAlien) {
		if (num < capacity) {
			this.objects[num] = regAlien;
			num++;
		}
	}
	
	
	public int getNum() {
		return this.num;
	}
	
	public void swap(int pos1, int pos2) {
		RegularAlien auxAlien;
		if (pos1 >= 0 && pos1 < this.capacity && pos2 >= 0 && pos2 < this.capacity && pos1 != pos2) {
			auxAlien = this.objects[pos1];
			this.objects[pos1] = this.objects[pos2];
			this.objects[pos2] = auxAlien;
		}
	}
	
	public void removeDead() {
		int deadObjects = 0;
		for (int i = this.num - 1; i >= 0 ; --i) {
			if (!this.objects[i].Alive()) {
				this.game.markPoints(this.objects[i].getPoints());
				this.objects[i].callDead();
				this.objects[i] = null;
				if (i != this.num - 1 - deadObjects)
					this.swap(i, this.num - 1 - deadObjects);
				deadObjects++;
			}
		}
		
		this.num -= deadObjects;
		
//		int i = 0;
//		while (i < num && this.objects[i].Alive())
//			i++;
//		if (i != num) {
//			this.objects[i] = null;
//			swap(i, num - 1);
//			--num;
//		}
	}
	
	public void automaticMoves(){
		for (int i = 0; i < num; ++i)
			this.objects[i].automaticMove();
	}
	//TODO fill your code
	
	public RegularAlien anObjectInPos(Position pos) {
		RegularAlien alien = null;
		int i = 0;
		
		while (i < num && !this.objects[i].inPosition(pos))
			++i;
		if (i != num)
			alien = this.objects[i];
		
		return alien;
	}
	
	public RegularAlien getAlien (int index)
	{
		
		return this.objects[index];
	}
	
	
	public void shockwaveHit() {
		for (int i = 0; i < this.num; ++i) {
			this.objects[i].hit(1);
		}
		this.removeDead();
			
	}
	
	

}

