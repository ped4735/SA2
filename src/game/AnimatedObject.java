package game;

import br.senai.sc.engine.Utils;
import game.interfaces.Updatable;

public class AnimatedObject extends GameObject implements Updatable {

	private boolean[][] boolMatrix;
	private int delayToRun = 5;
	


	private int delayCont;

	public AnimatedObject(String spriteFileName, int posX, int posY, int colFrames, int lineFrames) {
		super(spriteFileName, posX, posY, colFrames, lineFrames);
	}

	public AnimatedObject(String spriteFileName, int posX, int posY, int colFrames, int lineFrames, int[] bolMatrix) {
		this(spriteFileName, posX, posY, colFrames, lineFrames);
		this.boolMatrix = Utils.getInstance().makeBooleanMatrix(colFrames, lineFrames, bolMatrix);
	}

	@Override
	public void update() {
		this.runSpriteSheet();

	}

	public void runSpriteSheet() {
		// sem a necessidad de subreescrer o update em outros lugares, mesmo que
		// ele seja diferente
		// o truehero, por exemplo, sobreescrevia o update, daí ele acabava
		// perdendo essa parte de percorrer o spritesheet
		delayCont++;
		if (delayCont == delayToRun) {
			delayCont = 0;
			
			if (boolMatrix == null) {
				setFrameX(getFrameX() + 1);
				if (getFrameX() >= getColFrames()) {
					setFrameX(0);
					setFrameY(getFrameY() + 1);
					if (getFrameY() >= getLineFrames()) {
						setFrameY(0);
					}
				}

				
			} else if (getFrameX() + 1 >= boolMatrix[0].length) {
				//System.out.println(boolMatrix.length);
				setFrameX(0);
			} else if (boolMatrix[getFrameY()][getFrameX() + 1]) {
				setFrameX(getFrameX() + 1);
			}
		}
	}
	
	public int getDelayToRun() {
		return delayToRun;
	}

	public void setDelayToRun(int delayToRun) {
		this.delayToRun = delayToRun;
	}
	
	public void changeAnim(int n){
		
		if(!boolMatrix[n][getFrameX()]){
			setFrameX(0);
		}
		
		setFrameY(n);
	}
	

}
