package game;

import game.scenes.Credits;
import game.scenes.GameOver;
import game.scenes.Gameplay;
import game.scenes.Help;
import game.scenes.MainMenu;
import game.scenes.Opening;
import game.scenes.Ranking;
import game.scenes.Scene;
import game.scenes.Victory;
import game.scenes.LevelManager;
import game.scenes.Loading;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import br.senai.sc.engine.Game;
import br.senai.sc.engine.Utils;

public class JetpackGame extends Game {

	public static GameStates currentGameState;
	private Scene opening, menu, credits, help, currentScene, gameOver, ranking, loading, victory;
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

		addNewFont("Space", "fonts/spaceAge.otf", 25, Font.PLAIN);
		addNewFont("Andromeda", "fonts/Andromeda.ttf", 25, Font.PLAIN);

		// Add event when application is closed
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				GameManager.getInstance().saveScore();
				System.out.println("Saved");
			}
		});


		String oldmaps = "level_1,level1,level2,level1,level_1,level_3";
		String newmaps = "map1,map2,map3";
		String maps;
		try {
			//para sempre carregar pela ultima fase feita
			//Utils.getInstance().setDebug(!Utils.getInstance().isDebug());
			
			maps = readValidMaps();
			System.out.println(maps);
			
			//Utils.getInstance().setDebug(!Utils.getInstance().isDebug());

			LevelManager.getInstance().setLevelFiles(maps);
			System.out.println("Maps: " + maps);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		menu = new MainMenu();
		credits = new Credits();
		opening = new Opening();
		gameOver = new GameOver();
		ranking = new Ranking();
		loading = new Loading();
		victory = new Victory();
		help = new Help();
		currentGameState = GameStates.MainMenu;

		GameManager.getInstance().loadScore();

	}

	@Override
	public void gameLoop() {
		// setFont("Space");
		// setFont("Andromeda");

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
			currentScene = help;
			currentScene.draw(getGraphics2D());
			currentScene.update();
			break;

		case Loading:
			currentScene = loading;
			currentScene.draw(getGraphics2D());
			currentScene.update();
			break;

		case Victory:
			currentScene = victory;
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
			System.out.println("X: " + e.getX() + " Y: " + e.getY());
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
				if (currentGameState == GameStates.Gameplay) {
					currentGameState = GameStates.Loading;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
				alterarFramesPorSegundos(5);
			}
			if (e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
				alterarFramesPorSegundos(30);
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			currentScene.keyReleased(e);
		}
	}

	
	private String readValidMaps() throws IOException {
		File file = new File("src/levels/validMaps.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			
			//para sempre carregar pela ultima fase feita
			if (Utils.getInstance().isDebug()){
				line = br.readLine();
			}

			
			br.close();
			return line;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
}
