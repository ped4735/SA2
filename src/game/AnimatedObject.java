package game;

import br.senai.sc.engine.Utils;
import game.interfaces.Updatable;

public class AnimatedObject extends GameObject implements Updatable {

	private boolean[][] boolMatrix;

	public AnimatedObject(String spriteFileName, int posX, int posY, int colFrames, int lineFrames) {
		super(spriteFileName, posX, posY, colFrames, lineFrames);
	}

	public AnimatedObject(String spriteFileName, int posX, int posY, int colFrames, int lineFrames, int[] bolMatrix) {
		this(spriteFileName, posX, posY, colFrames, lineFrames);
		this.boolMatrix = Utils.getInstance().makeBooleanMatrix(colFrames, lineFrames, bolMatrix);

	}

	@Override
	public void update() {
		if (boolMatrix == null){
			setFrameX(getFrameX()+1);
			if(getFrameX() >= getColFrames()){
				setFrameX(0);
				setFrameY(getFrameY()+1);
				if(getFrameY() >= getLineFrames()){
					setFrameY(0);
				}
			}
			
		}else if (getFrameX() + 1 >= boolMatrix.length) {
			setFrameX(0);
		} else if (boolMatrix[getFrameX() + 1][getFrameY()]) {
			setFrameX(getFrameX() + 1);
		}
		
		

	}

}
