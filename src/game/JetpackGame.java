package game;

import game.scenes.Credits;
import game.scenes.GameOver;
import game.scenes.Gameplay;
import game.scenes.MainMenu;
import game.scenes.Opening;
import game.scenes.Ranking;
import game.scenes.Scene;
import game.scenes.LevelManager;
import game.scenes.Loading;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import br.senai.sc.engine.Game;
import br.senai.sc.engine.Utils;

public class JetpackGame extends Game {

	public static GameStates currentGameState;
	private Scene opening, menu, credits, help, currentScene, gameOver, ranking, loading;
	// private LevelManager levels;
	// public static TrueHero hero;

	public JetpackGame() {
		super("JetPack", 1280, 720);
		addMouseListener(new MouseInputHandler());
		addKeyListener(new KeyInputHandler());
		addMouseMotionListener(new MouseInputHandler());
	}

	public static void main(String[] args) {
		JetpackGame jogo = new JetpackGame();
		jogo.startGame();
	}

	@Override
	public void init() {
		Utils.getInstance().setGlobalScale(1f);

		addNewFont("Space", "game/fonts/spaceAge.otf", 25, Font.PLAIN);
		addNewFont("Andromeda", "game/fonts/Andromeda.ttf", 25, Font.PLAIN);

		

		menu = new MainMenu();
		credits = new Credits();
		LevelManager.getInstance().setLevelFiles("level2,level_2,level0,level1,level_1,level_3");
		opening = new Opening();
		gameOver = new GameOver();
		ranking = new Ranking();
		loading = new Loading();
		currentGameState = GameStates.MainMenu;
	}

	@Override
	public void gameLoop() {
//		setFont("Space");
		//setFont("Andromeda");

		switch (currentGameState) {
		case OpeningPreMenu:
			currentScene = opening;
			currentScene.draw(getGraphics2D());
			currentScene.update();
			break;

		case MainMenu:
			currentScene = menu;
			currentScene.draw(getGraphics2D());
			currentScene.update();
			break;

		case Gameplay:
			currentScene = LevelManager.getInstance().getLevel();
			currentScene.draw(getGraphics2D());
			currentScene.collision();
			currentScene.update();
			break;

		case Credits:
			currentScene = credits;
			currentScene.draw(getGraphics2D());
			currentScene.update();
			break;
			
		case GameOver:
			currentScene = gameOver;
			currentScene.draw(getGraphics2D()); 
			currentScene.update();			

			break;
			
		case Ranking:
			currentScene = ranking;
			currentScene.draw(getGraphics2D());
			currentScene.update();
			break;
			
		case Help:
			currentScene = ranking;
			currentScene.draw(getGraphics2D());
			currentScene.update();
			break;
			
		case Loading:
			currentScene = loading;
			currentScene.draw(getGraphics2D());
			currentScene.update();
			break;
			
		case Exit:
			System.exit(0);
			break;
			
		default:
			break;

		}


	}

	private class MouseInputHandler extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			currentScene.click(e.getX(), e.getY());
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			currentScene.isOverObject(e.getX(), e.getY());
		}
	}

	private class KeyInputHandler extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			currentScene.keyPressed(e);

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_F9) {
				Utils.getInstance().setDebug(!Utils.getInstance().isDebug());
			}
			if (e.getKeyCode() == KeyEvent.VK_P) {
				LevelManager.getInstance().nextLevel();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			currentScene.keyReleased(e);
		}
	}

}
