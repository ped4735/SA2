package game.scenes;

import game.AnimatedObject;
import game.Button;
import game.GameStates;

public class Credits extends Scene {

	public Credits() {
		
		getObjsInScene().add(new AnimatedObject("bgImage.png", 0, 0, 1, 1));
		getObjsInScene().add(new Button("exitButton.png", 0, 0, 1, 2, GameStates.MainMenu));
		getObjsInScene().add(new AnimatedObject("coinssonic.png", 300, 100, 4, 4));		
		
	}
}
