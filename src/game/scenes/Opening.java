package game.scenes;

import java.awt.event.KeyEvent;

import game.AnimatedObject;
import game.GameStates;
import game.JetpackGame;

public class Opening extends Scene {

	public Opening() {
		getObjsInScene().add(new AnimatedObject("opening/openingBackground.png", 0, 0, 1, 1));
		
		//getObjsInScene().add(new AnimatedObject("opening/Ship.png",0,0,1,1));
				
		
	}

	@Override
	public void update() {
		super.update();
		cont();

	}

	// contador temporario para controlar a animacao
	int cont = 0;

	private void cont() {
		cont++;
		System.out.println(cont);
		if (cont == 80) {
			JetpackGame.currentGameState = GameStates.MainMenu;
		}
	}

	@Override
	public void keyPressed(KeyEvent key) {

		JetpackGame.currentGameState = GameStates.MainMenu;
	}

}
