package game.scenes;

import java.awt.event.KeyEvent;

import game.AnimatedObject;
import game.Button;
import game.GameObject;
import game.GameStates;
import game.JetpackGame;

public class Help extends Scene{
	
	public Help(){
		getObjsInScene().add(new AnimatedObject("background/helpBackground.jpg", 0, 0,1,1));
		
		getObjsInScene().add(new Button("exitButton.png", 500, 500, 1, 2, GameStates.Exit));

		
				
	}

	
	@Override
	public void keyPressed(KeyEvent key) {
		JetpackGame.currentGameState = GameStates.MainMenu;	
	}
}
