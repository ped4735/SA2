package game.tiles;

import game.AnimatedObject;
import game.GameManager;
import game.GameObject;
import game.GameStates;
import game.JetpackGame;
import game.PlaySound;
import game.interfaces.Interactable;

public class CompleteLevel extends AnimatedObject implements Interactable{

	
	private PlaySound soundComplete;
	
	public CompleteLevel(int posX, int posY) {
		super("complete.png", posX, posY, 6, 1);
		soundComplete = new PlaySound("endfase.wav");
	}

	
	@Override
	public void actionEnter(GameObject gameobj) {
		if (game.TrueHero.class.isInstance(gameobj)) {
			JetpackGame.currentGameState = GameStates.Loading;
			soundComplete.start();
		}
			
	}

	@Override
	public void actionStay(GameObject gameobj) {
		// TODO Auto-generated method stub
		//LevelManager.getInstance().nextLevel();	
	}

	@Override
	public void actionExit(GameObject gameobj) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
