package game.scenes;

import game.AnimatedObject;

public class Loading extends Scene{
	
	public Loading() {
		getObjsInScene().add(new AnimatedObject("loadingScene.png", 0, 0, 1, 1));	
	}
	

}
