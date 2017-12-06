package game;

import game.interfaces.Clickable;
import game.scenes.LevelManager;

import java.awt.Rectangle;

public class Button extends GameObject implements Clickable{

	private GameStates changeToState;

	public Button(String sprite, int posX, int posY, int colFrames, int lineFrames, GameStates gameStateToChange) {
		super(sprite, posX, posY, colFrames, lineFrames);
		this.changeToState = gameStateToChange;
	}

	@Override
	public void click(int posX, int posY) {

		if (getRectangle().intersects(new Rectangle(posX, posY, 1, 1))) {
			//setFrameY(0);
			/*if(changeToState == GameStates.Gameplay){
				LevelManager.getInstance().nextLevel();
			}else{
				JetpackGame.currentGameState = changeToState;	
			}*/			
			JetpackGame.currentGameState = changeToState;
			
		}
	}

	@Override
	public void isOverObject(int posX, int posY) {
		if (getRectangle().intersects(new Rectangle(posX, posY, 1, 1))) {
			setFrameY(1);
		}else{
			setFrameY(0);
		}
	}

}
