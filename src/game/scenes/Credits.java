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
		getObjsInScene().add(new Button("exitButton.png", 0, 0, 1, 2, GameStates.MainMenu));
		getObjsInScene().add(new AnimatedObject("coinssonic.png", 300, 100, 4, 4));		
		
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
