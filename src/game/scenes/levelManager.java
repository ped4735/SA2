package game.scenes;

import java.util.ArrayList;
import java.util.List;

public class levelManager {
	
	private List<Gameplay> levels = new ArrayList<Gameplay>();
	private  int currentLevel;
	
	
	public levelManager(String levels) {
		currentLevel = 0;
		String[] fileNames = levels.split(",");
		
		for (int i = 0; i < fileNames.length; i++) {
			this.levels.add(new Gameplay(fileNames[i]));
		}
		
	}

	
	public Gameplay getCurrentLevel(){
		return levels.get(currentLevel);
	}
	
	public void nextLevel(){
		currentLevel++;
	}
	
}
