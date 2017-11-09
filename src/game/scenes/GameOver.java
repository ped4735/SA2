package game.scenes;

import game.AnimatedObject;

public class GameOver extends Scene {

	public GameOver() {
		getObjs().add(new AnimatedObject("bgImage.png", 0,0,1,1));
	}

}
