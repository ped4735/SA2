package game.scenes;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import game.AnimatedObject;
import game.Button;
import game.GameObject;
import game.GameStates;
import game.JetpackGame;

public class Help extends Scene{
	
	public Help(){
		getObjsInScene().add(new AnimatedObject("background/helpBackground.jpg", 0, 0,1,1));
		//getObjsInScene().add(new AnimatedObject("background/rankingBackground.jpg", 0, 0, 1, 1));

		Button b = new Button("exitButton.png", 500, 500, 1, 2, GameStates.Exit);
		b.setPosTopRight(10,10);
		getObjsInScene().add(b);
		
		Button c = new Button("exitButton.png", 500, 500, 1, 2, GameStates.MainMenu);
		c.setPosTopRight(10,10 + c.getHeight());
		getObjsInScene().add(c);
		
		
	}
	

	
	@Override
	public void keyPressed(KeyEvent key) {
		JetpackGame.currentGameState = GameStates.MainMenu;	
	}
	
}
