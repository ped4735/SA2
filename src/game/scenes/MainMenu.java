package game.scenes;

import game.AnimatedObject;
import game.Button;
import game.GameStates;

public class MainMenu extends Scene {

	public MainMenu() {
		getObjs().add(new AnimatedObject("bgImage.png", 0, 0, 1, 1));
		getObjs().add(new Button("startButton.png", 100, 200, 1, 2, GameStates.Gameplay));
		getObjs().add(new Button("creditsButton.png", 100, 500, 1, 2, GameStates.Credits));
		getObjs().add(new Button("helpButton.png", 400, 200, 1, 2, GameStates.Help));
		getObjs().add(new Button("exitButton.png", 400, 500, 1, 2, GameStates.Exit));
		getObjs().add(new AnimatedObject("coinssonic.png", 500, 100, 4, 4));
	}
}
