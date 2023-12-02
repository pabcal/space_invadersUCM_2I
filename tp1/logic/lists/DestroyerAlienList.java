package tp1.logic.lists;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.gameobjects.Bomb;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.Shockwave;


public class DestroyerAlienList {
	private Game game;
	private DestroyerAlien[] objects;
	private int num = 0;
	private int capacity;
	
	
	public DestroyerAlienList(int size, Game game) {
		this.objects = new DestroyerAlien[size];
		this.capacity = size;
		this.game = game;
	}
	

	public void add(DestroyerAlien regAlien) {
		if (num < capacity) {
			this.objects[num] = regAlien;
			num++;
		}
	}
	
	
	public int getNum() {
		return this.num;
	}
	
	public void swap(int pos1, int pos2) {
		DestroyerAlien auxAlien;
		if (pos1 >= 0 && pos1 < this.capacity && pos2 >= 0 && pos2 < this.capacity && pos1 != pos2) {
			auxAlien = this.objects[pos1];
			this.objects[pos1] = this.objects[pos2];
			this.objects[pos2] = auxAlien;
		}
	}
	
	public void removeDead() {
		int deadObjects = 0;
		for (int i = this.num - 1; i >= 0 ; --i) {
			if (!this.objects[i].isAlive()) {
				this.game.markPoints(this.objects[i].getPoints());
				this.objects[i].callDead();
				this.objects[i] = null;
				if (i != this.num - 1 - deadObjects)
					this.swap(i, this.num - 1 - deadObjects);
				deadObjects++;
			}
		}
		
		this.num -= deadObjects;
	}
	
	public void automaticMoves(){
		for (int i = 0; i < num; ++i)
			this.objects[i].automaticMove();
	}
	//TODO fill your code
	
	public DestroyerAlien anObjectInPos(Position pos) {
		DestroyerAlien alien = null;
		int i = 0;
		
		while (i < num && !this.objects[i].isOnPosition(pos))
			++i;
		if (i != num)
			alien = this.objects[i];
		
		return alien;
	}
	
	public DestroyerAlien getAlien (int index)
	{
		
		return this.objects[index];
	}
	
	public void automaticShoot() {
		for (int i = 0; i < this.num; ++i) {
			this.objects[i].shoot();
		}
			
	}
	
	public void shockwaveHit(Shockwave shockwave) {
		for (int i = 0; i < this.num; ++i) {
			this.objects[i].hit(shockwave);
		}
		this.removeDead();
			
	}
	
	public void bombProcess() {
		for (int i = 0; i < this.num; ++i) {
			this.objects[i].bombMove();
		}
	}
	
	public Bomb searchBombInPos(Position pos) {
		int i = 0;
		boolean inPos = false;
		Bomb bomb = null;
		Position bombPosition;
		while (i < num && !inPos) {
			bomb = this.objects[i].getBomb();
			if (bomb != null) {
				bombPosition = bomb.getPos();
				inPos = bombPosition.isEqual(pos);
			}
			i++;
		}
		
		return (inPos ? bomb : null);
	}
	
	public Bomb getBombFrom(int index) {
		return this.objects[index].getBomb();
	}
	
	public void deleteDeactivatedBombs() {
		for (int i = 0; i < this.num; ++i)
			if (!this.objects[i].validBomb())
				this.objects[i].deleteBomb();
	}

}
