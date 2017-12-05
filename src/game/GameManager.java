package game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class GameManager {
	
	private static GameManager instance;
	private int gameScore;
	private String playerName;
	
	public static  GameManager getInstance() {
		
		if(instance == null){
			instance = new GameManager();
		}
		return instance;
		
	}
	
	
	public void addScore(int p){
		gameScore+=p;
	}
	
	public int getScore(){
		return gameScore;
	}

	public void setScore(int p){
		this.gameScore = p;		
	}
	
	
	public void resetGame(GameStates gameStateToOver){
		JetpackGame.currentGameState = gameStateToOver;
		GameManager.getInstance().getNameToWriteInScoreFile();
		this.gameScore = 0;	
	}
	
	
	
	private String enterName() {
		Object gameOver = new String("Player");
		if (JetpackGame.currentGameState == GameStates.GameOver) {
			gameOver = JOptionPane.showInputDialog(null, "Enter Your Name!", "Game Over", 1, null, null, "Player");

		} else if(JetpackGame.currentGameState == GameStates.Victory){
			gameOver = JOptionPane.showInputDialog(null, "Enter Your Name!", "Victory!", 1, null, null, "Player");
		}

		if (gameOver == null) {
			gameOver = new String("PLAYER");
		} else {

			playerName = gameOver.toString().toUpperCase();

			int limitSize = 10;
			if (playerName.length() > limitSize) {
				playerName = playerName.substring(0, limitSize);
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

			pw.println(this.enterName() + "," + GameManager.getInstance().getScore());
			
			pw.close();

		} catch (IOException ioe) {
			System.out.println("Exception occurred:");
			ioe.printStackTrace();
		} finally {
			JetpackGame.currentGameState = GameStates.Ranking;
		}
	}
	

}


