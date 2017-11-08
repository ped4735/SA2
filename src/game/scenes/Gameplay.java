package game.scenes;

import game.Hero;
import game.tiles.Spike;
import game.tiles.Stone;

public class Gameplay extends Scene {

	public Gameplay() {

		getObjs().add(new Hero("coinssonic.png", 100, 0, 4, 4));
		getObjs().add(new Hero("coinssonic.png", 200, 0, 4, 4));
		getObjs().add(new Hero("coinssonic.png", 300, 0, 4, 4));
		getObjs().add(new Hero("coinssonic.png", 400, 0, 4, 4));
		
		
		getObjs().add(new Stone(100, 200));
		getObjs().add(new Stone(300, 200));
		getObjs().add(new Stone(500, 200));
		
		
		getObjs().add(new Spike(500, 400));
		
		
	}

}
