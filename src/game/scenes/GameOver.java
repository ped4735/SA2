package game.scenes;

import br.senai.sc.engine.Utils;
import game.AnimatedObject;

public class GameOver extends Scene {

	public GameOver() {
		
		getObjs().add(new AnimatedObject("bgImage.png", 0,0,1,1));
		
	}

}
