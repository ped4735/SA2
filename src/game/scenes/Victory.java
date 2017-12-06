package game.scenes;

import game.AnimatedObject;
import game.GameManager;

public class Victory extends Scene{

	
int count = 0;
	
	public Victory() {

		getObjsInScene().add(new AnimatedObject("backgroundAlpha.png", 0, 0, 1, 1));

	}
	
	@Override
	public void update() {
				
		super.update();
		count++;
		if(count == 10){
			GameManager.getInstance().resetGame();
			count = 0;
		}
		
	}
}
