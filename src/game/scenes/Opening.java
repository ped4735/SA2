package game.scenes;

import java.awt.event.KeyEvent;

import game.AnimatedObject;
import game.GameStates;
import game.JetpackGame;

public class Opening extends Scene {

	private int delayTime;
	private GameStates stateToGO;
	
	public Opening(int delayTime, GameStates stateToGO) {
		this.delayTime = delayTime;
		this.stateToGO = stateToGO;
		initObjects();
	}
	
	public Opening() {
		this.delayTime = 80;
		this.stateToGO = GameStates.MainMenu;
		initObjects();
	}	
	
	private void initObjects(){
		getObjsInScene().add(new AnimatedObject("opening/openingBackground.png", 0, 0, 1, 1));	
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
		//System.out.println(cont);
		if (cont == this.delayTime) {
			JetpackGame.currentGameState = stateToGO;
		}
	}

	@Override
	public void keyPressed(KeyEvent key) {
		super.keyPressed(key);
		JetpackGame.currentGameState = stateToGO;
	}

}
