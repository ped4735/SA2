package game;

import game.interfaces.Updatable;

public class AnimatedObject extends GameObject implements Updatable{

	
	public AnimatedObject(String spriteFileName, int posX, int posY, int colFrames, int lineFrames) {
		super(spriteFileName, posX, posY, colFrames, lineFrames);
	}

	
	@Override
	public void update() {		
		
		setFrameX(getFrameX()+1);
		if(getFrameX() >= getColFrames()){
			setFrameX(0);
			setFrameY(getFrameY()+1);
			if(getFrameY() >= getLineFrames()){
				setFrameY(0);
			}
		}
		//System.out.println(getFrameX() + " " + getFrameY());
	}
	
	
	
	
	

}
