package game.scenes;

import game.AnimatedObject;
import game.Button;
import game.GameStates;

public class Credits extends Scene {

	public Credits() {
		
		getObjs().add(new AnimatedObject("bgImage.png", 0, 0, 1, 1));
		getObjs().add(new Button("exitButton.png", 0, 0, 1, 2, GameStates.MainMenu));
		getObjs().add(new AnimatedObject("coinssonic.png", 300, 100, 4, 4));		
		
	}
}
