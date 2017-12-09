package game.scenes;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import br.senai.sc.engine.Utils;
import game.AnimatedObject;
import game.Button;
import game.GameStates;
import game.JetpackGame;

public class Credits extends Scene {
	AnimatedObject logo;

	public Credits() {
		
		getObjsInScene().add(new AnimatedObject("background/rankingBackground2.jpg", 0, 0, 1, 1));
		
		logo = new AnimatedObject("creditslogo.png", 0, 0, 1, 1);
		logo.setPosX(Utils.getInstance().getWidth()/2 - logo.getWidth()/2);
		getObjsInScene().add(logo);
		
		Button b = new Button("exitButton.png", 500, 500, 1, 2, GameStates.MainMenu);
		b.setPosTopRight(10,10);
		getObjsInScene().add(b);	
		

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
