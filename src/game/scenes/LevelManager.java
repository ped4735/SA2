package game.scenes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.engine.Utils;
import game.GameStates;
import game.JetpackGame;

public class LevelManager {
	
	private List<Gameplay> levels = new ArrayList<Gameplay>();
	private int currentLevel;
	
	String[] fileNames;
	
	
	public LevelManager(String levels) {
		currentLevel = 0;
		//String[] fileNames = levels.split(",");
		fileNames = levels.split(",");
		
//		for (int i = 0; i < fileNames.length; i++) {
			this.levels.add(new Gameplay(fileNames[currentLevel]));
//		}
		
	}

	
	public Gameplay getCurrentLevel(){
		return levels.get(currentLevel);
	}
	
	public void nextLevel(){
		if (currentLevel+1 < fileNames.length){
			this.levels.add(new Gameplay(fileNames[currentLevel+1]));
			currentLevel++;
		}else{
			JetpackGame.currentGameState = GameStates.GameOver;
			currentLevel = 0;
			levels.clear();
			this.levels.add(new Gameplay(fileNames[currentLevel]));
		}
		
		System.out.println(levels.size());
	}
	
}
