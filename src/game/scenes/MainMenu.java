package game.scenes;

import br.senai.sc.engine.Utils;
import game.AnimatedObject;
import game.Button;
import game.GameStates;

public class MainMenu extends Scene {

	public MainMenu() {
		getObjsInScene().add(new AnimatedObject("bgImage.png", 0, 0, 1, 1));
		getObjsInScene().add(new Button("startButton.png", 0, 200, 1, 2, GameStates.Gameplay));
		getObjsInScene().add(new Button("creditsButton.png",0, 300, 1, 2, GameStates.Credits));
		getObjsInScene().add(new Button("helpButton.png", 0, 400, 1, 2, GameStates.Help));
		getObjsInScene().add(new Button("exitButton.png", 0, 500, 1, 2, GameStates.Exit));
		
	}
}
