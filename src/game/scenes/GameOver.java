package game.scenes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import br.senai.sc.engine.Utils;
import game.AnimatedObject;
import game.GameManager;
import game.GameStates;
import game.JetpackGame;

public class GameOver extends Scene {

	String playerName;

	public GameOver() {

		getObjsInScene().add(new AnimatedObject("backgroundAlpha.png", 0, 0, 1, 1));

	}

	private String enterName() {
		Object gameOver;// = new String("Player");
		if (game.JetpackGame.hero.getLife() <= 0) {
			gameOver = JOptionPane.showInputDialog(null, "Enter Your Name!", "Game Over", 1, null, null, "Player");

		} else {
			gameOver = JOptionPane.showInputDialog(null, "Enter Your Name!", "Victory!", 1, null, null, "Player");
		}

		if (gameOver == null) {
			gameOver = new String("PLAYER");
		} else {

			playerName = gameOver.toString().toUpperCase();

			int limitSize = 10;
			if (playerName.length() > limitSize) {
				playerName = playerName.substring(0, 10);
			}
		}
		return playerName;

	}

	private String getScore() {
		return String.valueOf(game.JetpackGame.hero.getScore());
	}

	public void getNameToWriteInScoreFile() {
		try {
			File file = new File("Ranking.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			pw.println(this.enterName() + "," + GameManager.getInstance().getScore());
			pw.close();

		} catch (IOException ioe) {
			System.out.println("Exception occurred:");
			ioe.printStackTrace();
		} finally {
			JetpackGame.currentGameState = GameStates.Ranking;
		}
	}

	@Override
	public void update() {
		super.update();
		this.getNameToWriteInScoreFile();

	}

}
