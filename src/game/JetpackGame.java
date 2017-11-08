package game;

import game.scenes.Credits;
import game.scenes.Gameplay;
import game.scenes.MainMenu;
import game.scenes.Scene;

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

	public static GameStates currentGameState = GameStates.MainMenu;
	private Scene menu, credits, help, gameplay, currentScene, gameOver;

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
		menu = new MainMenu();
		credits = new Credits();
		gameplay = new Gameplay();
		//gameOver = new 
		
	}

	@Override
	public void gameLoop() {

		System.out.println("oi");
		
		switch (currentGameState) {
		case MainMenu:
			currentScene = menu;
			currentScene.draw(getGraphics2D());
			currentScene.update();
			break;
		case Gameplay:
			currentScene = gameplay;
			currentScene.draw(getGraphics2D());
			currentScene.update();
			currentScene.collision();
			break;
		case Credits:
			currentScene = credits;
			currentScene.draw(getGraphics2D());
			currentScene.update();
			break;			
		case Help:
			//Help
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
			currentScene.pressAction(e);
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE ){
				System.exit(0);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			currentScene.releaseAction(e);
		}
	}

	
}
