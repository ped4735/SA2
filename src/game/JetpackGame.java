package game;

import game.scenes.Credits;
import game.scenes.GameOver;
import game.scenes.Gameplay;
import game.scenes.MainMenu;
import game.scenes.Opening;
import game.scenes.Scene;
import game.scenes.levelManager;

import java.awt.Color;
import java.awt.Event;
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
	private Scene opening, menu, credits, help, currentScene, gameOver;
	private levelManager levels;
	
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

		menu = new MainMenu();
		credits = new Credits();
		//levels = new levelManager("level_3,level_1,level_2");
		opening = new Opening();
		gameOver = new GameOver();
		currentGameState = GameStates.OpeningPreMenu;
		
		
		
		//TEMPORARIAMENTE:
		//cansei de ficar passando pelo menu e a abertura para testar a mecânica
		// e de esperar carregar 3 mapas sendo que só usamos 1!
		currentGameState = GameStates.Gameplay;
		levels = new levelManager("level_3");
		
		
		



		
	}

	@Override
	public void gameLoop() {

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
			currentScene = levels.getCurrentLevel();
			currentScene.draw(getGraphics2D());
			currentScene.update();
			currentScene.collision();
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
		case Help:
			// Help
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
		}

		@Override
		public void keyReleased(KeyEvent e) {
			currentScene.keyReleased(e);
		}
	}

}
