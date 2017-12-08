package game.scenes;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import br.senai.sc.engine.Utils;
import game.AnimatedObject;
import game.Button;
import game.GameStates;
import game.JetpackGame;

public class Credits extends Scene {

	public Credits() {
		
		getObjsInScene().add(new AnimatedObject("bgImage.png", 0, 0, 1, 1));
		
		Button exit = new Button("exitButton.png", 500, 500, 1, 2, GameStates.MainMenu);
		exit.setPosTopRight(10,10);
		getObjsInScene().add(exit);
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		g.drawString("Credits", 600, 30);
	}
	
	@Override
	public void keyPressed(KeyEvent key) {
		JetpackGame.currentGameState = GameStates.MainMenu;	
	}
}
