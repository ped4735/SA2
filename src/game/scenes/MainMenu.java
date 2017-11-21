package game.scenes;

import game.AnimatedObject;
import game.Button;
import game.GameStates;

public class MainMenu extends Scene {

	public MainMenu() {
		getObjsInScene().add(new AnimatedObject("bgImage.png", 0, 0, 1, 1));
		getObjsInScene().add(new Button("startButton.png", 100, 200, 1, 2, GameStates.Gameplay));
		getObjsInScene().add(new Button("creditsButton.png", 100, 500, 1, 2, GameStates.Credits));
		getObjsInScene().add(new Button("helpButton.png", 400, 200, 1, 2, GameStates.Help));
		getObjsInScene().add(new Button("exitButton.png", 400, 500, 1, 2, GameStates.Exit));
		//getObjsInScene().add(new AnimatedObject("coinssonic.png", 500, 100, 4, 4, new int[] {4,4,4,4}));
		//getObjsInScene().add(new AnimatedObject("blackhole.png", 300, 400, 8, 1));
		//getObjsInScene().add(new AnimatedObject("rocket.png", 300, 600, 2, 1));
	}
}
