package game.tiles;

import game.GameObject;
import game.GameTags;
import game.interfaces.Interactable;

public class Stone extends GameObject implements Interactable {

	public Stone(int posX, int posY) {

		super("stone.png", posX, posY, 1, 1, GameTags.Ground);

	}

	

	@Override
	public void actionEnter(GameObject gameobj) {
		if (game.Hero.class.isInstance(gameobj)) {
			game.Hero hero = (game.Hero) gameobj;
			
				hero.setGrounded(true);
			
		}
		
	}

	@Override
	public void actionExit(GameObject gameobj) {
		if (game.Hero.class.isInstance(gameobj)) {
			game.Hero hero = (game.Hero) gameobj;
			
				hero.setGrounded(false);
			
		}
		
	}

}
