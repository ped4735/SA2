package game.scenes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.engine.Utils;
import game.GameManager;
import game.GameStates;
import game.JetpackGame;

public class LevelManager {

	private static LevelManager instance;
	
	private Gameplay level;
	private int currentLevel;
	String[] fileNames;
	private int numLevel;
	
	
	private int colletableInScene;
	
	
	
	public static  LevelManager getInstance() {
		
		if(instance == null){
			instance = new LevelManager();
		}
		return instance;
	}

	public void setLevelFiles(String levels){
		fileNames = levels.split(",");
	}

	public Gameplay getLevel() {
		return level;
	}
	
	public void setCurrentLevel(int lvl){
		this.currentLevel = lvl;
		numLevel = lvl-1;
	}

	public void nextLevel() {
		
		
		if (currentLevel < fileNames.length) {
			System.out.println(fileNames.length);
			level = new Gameplay(fileNames[currentLevel]);
			currentLevel++;
			numLevel++;
			
		} else { // acabou as fases
			currentLevel = 0;
			level = new Gameplay(fileNames[currentLevel]);
			currentLevel = 1;
			numLevel=1;
			JetpackGame.currentGameState = GameStates.Victory;
		}
	}

	public int getNumLevel() {
		return numLevel;
	}

	
	public int getColletableInScene() {
		return colletableInScene;
	}

	public void setColletableInScene(int colletableInScene) {
		this.colletableInScene = colletableInScene;
	}
	
	public void addColletableInScene(){
		colletableInScene++;
	}
	public void removeColletableInScene(){
		colletableInScene--;
	}
	
}
