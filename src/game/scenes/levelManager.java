package game.scenes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.engine.Utils;

public class levelManager {
	
	private List<Gameplay> levels = new ArrayList<Gameplay>();
	private int currentLevel;
	
	String[] fileNames;
	
	
	public levelManager(String levels) {
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
		this.levels.add(new Gameplay(fileNames[currentLevel+1]));
		currentLevel++;
	}
	
}
