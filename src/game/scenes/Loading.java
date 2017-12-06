package game.scenes;

import java.awt.Graphics2D;

import game.AnimatedObject;

public class Loading extends Scene{
	
	int count = 0;
	
	public Loading() {
		getObjsInScene().add(new AnimatedObject("loadingScene.png", 0, 0, 1, 1));	
	}
	
	
	@Override
	public void update() {
				
		super.update();
		count++;
		if(count == 10){
			LevelManager.getInstance().nextLevel();
			count = 0;
		}
		
	}
	


}
