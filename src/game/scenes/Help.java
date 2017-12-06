package game.scenes;

import java.awt.event.KeyEvent;

import game.AnimatedObject;
import game.Button;
import game.GameObject;
import game.GameStates;
import game.JetpackGame;

public class Help extends Scene{
	
	//desenhar uma imagem estatica com comentarios sobre a mecânica?
	public Help(){
		getObjsInScene().add(new GameObject("Help.png", 0, 0));
		getObjsInScene().add(new Button("exitButton.png", 500, 500, 1, 2, GameStates.Exit));

		
				
	}

	
	@Override
	public void keyPressed(KeyEvent key) {
		JetpackGame.currentGameState = GameStates.MainMenu;	
	}
}
