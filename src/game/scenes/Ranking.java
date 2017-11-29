package game.scenes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import game.GameStates;
import game.JetpackGame;

public class Ranking extends Scene{
	
	
	@Override
	public void keyPressed(KeyEvent key) {
		JetpackGame.currentGameState = GameStates.Credits;	
	}
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		g.setColor(Color.WHITE);
		g.drawString("Ranking", 600, 30);
		g.drawString("Press any key to continue", 600, 600);
	}


	
}
