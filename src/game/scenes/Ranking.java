package game.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import br.senai.sc.engine.Utils;
import game.AnimatedObject;
import game.Button;
import game.GameManager;
import game.GameStates;
import game.JetpackGame;
import game.Score;
import game.ScoreIO;

public class Ranking extends Scene{
	AnimatedObject logo;
	public Ranking(){

		getObjsInScene().add(new AnimatedObject("background/rankingBackground.jpg", 0, 0, 1, 1));
		
		
		logo = new AnimatedObject("ranking.png", 0, 0, 1, 1);
		logo.setPosX(Utils.getInstance().getWidth()/2 - logo.getWidth()/2);
		getObjsInScene().add(logo);
		
		
		Button exit = new Button("exitButton.png", 500, 500, 1, 2, GameStates.MainMenu);
		exit.setPosTopRight(10,10);
		getObjsInScene().add(exit);


		
		
	}
	
	@Override
	public void keyPressed(KeyEvent key) {
		JetpackGame.currentGameState = GameStates.Credits;
	}
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 40));
		//g.drawString("Ranking", 600, 60);
		
		
		
		for (int i = 0; i < GameManager.getInstance().getScoreIO().getScores().size(); i++) {
			g.drawString(GameManager.getInstance().getScoreIO().getScores().get(i).getNome(), 400, i*80 + 200);
			g.drawString(""+GameManager.getInstance().getScoreIO().getScores().get(i).getScore(), 800, i*80 + 200);
		}
			
		
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Press any key to continue", 500, 650);
		
	}
	

	
}
