package game.scenes;

import java.awt.event.KeyEvent;

import game.GameStates;
import game.JetpackGame;

public class Help extends Scene{
	
	//desenhar uma imagem estatica com comentarios sobre a mecânica?
	

	
	@Override
	public void keyPressed(KeyEvent key) {
		JetpackGame.currentGameState = GameStates.MainMenu;	
	}
}
