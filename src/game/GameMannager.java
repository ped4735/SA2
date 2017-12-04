package game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class GameMannager {
	
	private static GameMannager instance;
	private int gameScore;
	private int gameLife;
	private String playerName;
	
	public static  GameMannager getInstance() {
		
		if(instance == null){
			instance = new GameMannager();
		}
		return instance;
		
	}
	
	
	public void addScore(int p){
		gameScore+=p;
	}
	
	public int getScore(){
		return gameScore;
	}


	public int getGameLife() {
		return gameLife;
	}


	public void setGameLife(int gameLife) {
		this.gameLife = gameLife;
	}

	public void addAmountLife(int lifeToadd){
		this.gameLife+=lifeToadd;
	}
	
	public void removeAmoutLife(int lifeToremove){
		this.gameLife-=lifeToremove;
	}
	
	public void resetGame(){
		JetpackGame.currentGameState = GameStates.GameOver;
		GameMannager.getInstance().getNameToWriteInScoreFile();
		this.gameLife = 0;
		this.gameScore = 0;	
	}
	
	
	
	private String enterName() {
		Object gameOver;// = new String("Player");
		if (GameMannager.getInstance().getGameLife() <= 0) {
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

	public void getNameToWriteInScoreFile() {
		try {
			File file = new File("Ranking.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			pw.println(this.enterName() + "," + GameMannager.getInstance().getScore());
			pw.close();

		} catch (IOException ioe) {
			System.out.println("Exception occurred:");
			ioe.printStackTrace();
		} finally {
			JetpackGame.currentGameState = GameStates.Ranking;
		}
	}
	
	
	
}
