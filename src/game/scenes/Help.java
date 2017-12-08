package game.scenes;

import java.awt.event.KeyEvent;

import br.senai.sc.engine.Utils;
import game.AnimatedObject;
import game.Button;
import game.GameObject;
import game.GameStates;
import game.JetpackGame;

public class Help extends Scene{
	
	public Help(){
		getObjsInScene().add(new AnimatedObject("background/helpBackground.jpg", 0, 0,1,1));
		
		Button exit = new Button("exitButton.png", 500, 500, 1, 2, GameStates.MainMenu);
		exit.setPosTopRight(10,10);
		getObjsInScene().add(exit);

		
				
	}

	
	@Override
	public void keyPressed(KeyEvent key) {
		JetpackGame.currentGameState = GameStates.MainMenu;	
	}
}
