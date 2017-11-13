package game.scenes;

import br.senai.sc.engine.Utils;
import game.AnimatedObject;

public class GameOver extends Scene {

	public GameOver() {
		
		getObjsInScene().add(new AnimatedObject("bgImage.png", 0,0,1,1));
		
	}

}
