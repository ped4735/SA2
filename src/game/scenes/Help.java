package game.scenes;

import java.awt.event.KeyEvent;

import br.senai.sc.engine.Utils;
import game.AnimatedObject;
import game.Button;
import game.GameStates;
import game.JetpackGame;

public class Help extends Scene{
	AnimatedObject logo;

	public Help(){
		getObjsInScene().add(new AnimatedObject("background/helpBackground.jpg", 0, 0,1,1));

		getObjsInScene().add(new AnimatedObject("helpScreen.png", 0, 0,1,1));
		
		logo = new AnimatedObject("help.png", 0, 0, 1, 1);
		logo.setPosX(Utils.getInstance().getWidth()/2 - logo.getWidth()/2);
		getObjsInScene().add(logo);




		Button b = new Button("exitButton.png", 500, 500, 1, 2, GameStates.MainMenu);
		b.setPosTopRight(10,10);
		getObjsInScene().add(b);
		

		
	}
	

	
	@Override
	public void keyPressed(KeyEvent key) {
		JetpackGame.currentGameState = GameStates.MainMenu;	
	}
	
}
