package game;

import game.scenes.LevelManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class GameManager {
	
	private static GameManager instance;
	
	private int gameScore;
	private String playerName;
	private ScoreIO scores = new ScoreIO();
	
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
	
	
	public void resetGame(){
		LevelManager.getInstance().setCurrentLevel(0);
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
		/*try {
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
		}*/
		scores.addScore(new Score(enterName(), gameScore));
		scores.printList();
		JetpackGame.currentGameState = GameStates.Ranking;
		
		saveScore();
		
	}
	
	public void saveScore(){
		try {
			FileOutputStream fOut = new FileOutputStream("Scores.ser");
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(scores);
			oOut.close();
			
		} catch (IOException ioe) {
			System.out.println("Exception occurred:");
			ioe.printStackTrace();
		} finally {
			JetpackGame.currentGameState = GameStates.Ranking;
		}	
	}
	
	
	public void loadScore(){
		
		try {
			FileInputStream fIn= new FileInputStream("Scores.ser");
			
			
			ObjectInputStream oIn= new ObjectInputStream(fIn);
			scores = (ScoreIO) oIn.readObject();
			oIn.close();
		} catch (Exception ioe) {
			System.out.println("Exception occurred:");
			ioe.printStackTrace();
		}	
		
	}
	
	
	public ScoreIO getScores(){
		return scores;
	}
	

}


