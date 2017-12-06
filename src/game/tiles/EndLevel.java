package game.tiles;

import game.GameObject;
import game.GameStates;
import game.JetpackGame;
import game.interfaces.Interactable;
import game.scenes.LevelManager;

public class EndLevel extends GameObject implements Interactable {

	public EndLevel(int posX, int posY) {
		super("endLevel.png", posX, posY, 1, 1);
	}

	public EndLevel(int posX, int posY, float theta) {
		this(posX, posY);
		super.setTheta(theta);

	}

	@Override
	public void actionEnter(GameObject gameobj) {
		if (game.TrueHero.class.isInstance(gameobj)) {
			JetpackGame.currentGameState = GameStates.Loading;
		}
	}

	@Override
	public void actionStay(GameObject gameobj) {

	}

	@Override
	public void actionExit(GameObject gameobj) {

	}

}
