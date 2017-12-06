package game.tiles;

import game.AnimatedObject;
import game.GameObject;
import game.GameStates;
import game.JetpackGame;
import game.interfaces.Interactable;
import game.scenes.LevelManager;

public class CompleteLevel extends AnimatedObject implements Interactable{

	public CompleteLevel(int posX, int posY) {
		super("complete.png", posX, posY, 6, 1);
	}

	
	@Override
	public void actionEnter(GameObject gameobj) {
		if (game.TrueHero.class.isInstance(gameobj)) {
			JetpackGame.currentGameState = GameStates.Loading;
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
