package game;

import game.interfaces.Updatable;

public class MovableAnimatedObject extends GameObject implements Updatable {

	
	
	public MovableAnimatedObject(String spriteFileName, int posX, int posY, int colFrames, int lineFrames) {
		super(spriteFileName, posX, posY, colFrames, lineFrames);
		
	}

	@Override
	public void update() {
		// 
	}

}
