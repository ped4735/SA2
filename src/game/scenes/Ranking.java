package game.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import game.GameManager;
import game.GameStates;
import game.JetpackGame;
import game.Score;
import game.ScoreIO;

public class Ranking extends Scene{
	
	
	@Override
	public void keyPressed(KeyEvent key) {
		JetpackGame.currentGameState = GameStates.Credits;	
	}
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 40));
		g.drawString("Ranking", 600, 60);
		
	
		
		for (int i = 0; i < GameManager.getInstance().getScoreIO().getScores().size(); i++) {
			g.drawString(GameManager.getInstance().getScoreIO().getScores().get(i).getNome(), 400, i*80 + 200);
			g.drawString(""+GameManager.getInstance().getScoreIO().getScores().get(i).getScore(), 800, i*80 + 200);
		}
			
		
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Press any key to continue", 400, 600);
		
	}
	

	
}
