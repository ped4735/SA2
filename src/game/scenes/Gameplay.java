package game.scenes;

import game.Hero;
import game.tiles.Spike;
import game.tiles.Stone;

public class Gameplay extends Scene {

	public Gameplay() {

		getObjsInScene().add(new Hero("coinssonic.png", 100, 0, 4, 4));
		getObjsInScene().add(new Hero("coinssonic.png", 200, 0, 4, 4));
		getObjsInScene().add(new Hero("coinssonic.png", 300, 0, 4, 4));
		getObjsInScene().add(new Hero("coinssonic.png", 400, 0, 4, 4));
		
		
		getObjsInScene().add(new Stone(100, 200));
		getObjsInScene().add(new Stone(300, 200));
		getObjsInScene().add(new Stone(500, 200));
		
		
		getObjsInScene().add(new Spike(500, 400));
		
		
	}

}
