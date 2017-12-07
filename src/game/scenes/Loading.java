package game.scenes;

import java.util.Random;

import game.AnimatedObject;
import game.Missile;
import game.MovableBackground;



public class Loading extends Scene{
	
	int count = 0;
	int contMax = 10;
	Missile m;
	MovableBackground background;
	
	public Loading() {
		//getObjsInScene().add(new AnimatedObject("loadingScene.png", 0, 0, 1, 1));	
		
		
		fundoMassa();

	}
	
	
	@Override
	public void update() {
			
		super.update();
		
		count++;
		if(count >= contMax){
			LevelManager.getInstance().nextLevel();
			count = 0;
			fundoMassa();

		}
		
		
		
		
	}
	
	private void fundoMassa(){
		getObjsInScene().clear();
		Random r = new Random();
		
		//String nome = "images/Space" + r.nextInt(2) + ".jpg";
		String name = "Space1.jpg";

		System.out.println("name: " + name);
		background = new MovableBackground(name);
		
		
		/*
		m = new Missile(0, 0, r.nextInt(5)-2, r.nextInt(5)-2, 0f);
		m.setColFrames(1);
		m.setLineFrames(1);
		m.setSprite(Utils.getInstance().loadImage(nome));
		m.setWidth((Utils.getInstance().loadImage(nome)).getWidth(null));
		m.setHeight((Utils.getInstance().loadImage(nome)).getHeight(null));
		*/
		
		getObjsInScene().add(background);	
		
	}
	


}
