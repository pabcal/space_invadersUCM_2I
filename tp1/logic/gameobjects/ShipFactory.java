package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.logic.Position;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;

public class ShipFactory {
	
	private static final List<AlienShip> AVAILABLE_ALIEN_SHIPS = Arrays.asList(
			new RegularAlien(),
			new DestroyerAlien(),
			new ExplodingShip()
		);
	
	public static AlienShip spawnAlienShip(String input, GameWorld game, Position pos, AlienManager am) {
		AlienShip alienShip = null;
		for (AlienShip a: AVAILABLE_ALIEN_SHIPS) {
			if (alienShip == null && input.equalsIgnoreCase(a.getSymbolFact()))
				alienShip = a.copy(game, pos, am);
		}
		
		return alienShip;

	}
	
}
