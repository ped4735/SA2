package game.scenes;

import java.util.Random;

import game.AnimatedObject;
import game.Missile;
import game.MovableBackground;



public class Loading extends Scene{
	
	int count = 0;
	int contMax = 30;
	Missile m;
	MovableBackground background;
	
	public Loading() {
		//getObjsInScene().add(new AnimatedObject("loadingScene.png", 0, 0, 1, 1));	
		
		
		moveableBackground();

	}
	
	
	@Override
	public void update() {
			
		super.update();
		
		count++;
		if(count >= contMax){
			LevelManager.getInstance().nextLevel();
			count = 0;
			moveableBackground();

		}
		
		
		
		
	}
	
	private void moveableBackground(){
		getObjsInScene().clear();
		Random r = new Random();
		
		String name = "loadingbg/Space" + r.nextInt(5) + ".jpg";
		System.out.println("name: " + name);
		background = new MovableBackground(name);
		

		getObjsInScene().add(background);	
		
	}
	


}
