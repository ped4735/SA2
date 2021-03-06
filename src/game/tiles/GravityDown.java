package game.tiles;

import game.AnimatedObject;
import game.GameObject;
import game.interfaces.Interactable;

public class GravityDown extends AnimatedObject implements Interactable {

	public GravityDown(int posX, int posY) {
		super("gravityDownSprite.png", posX, posY, 5, 1);

	}

	@Override
	public void actionEnter(GameObject gameobj) {
//		if (game.TrueHero.class.isInstance(gameobj)) {
//			game.TrueHero hero = (game.TrueHero) gameobj;
//			//hero.goUp();
//			hero.setGravity(Math.abs(hero.getGravity())*(-1));
//			
//		}


	}

	@Override
	public void actionStay(GameObject gameobj) {
		if (game.TrueHero.class.isInstance(gameobj)) {
			game.TrueHero hero = (game.TrueHero) gameobj;
			hero.goDown();
			
		}
	}

	@Override
	public void actionExit(GameObject gameobj) {
//		if (game.TrueHero.class.isInstance(gameobj)) {
//			game.TrueHero hero = (game.TrueHero) gameobj;
//			//hero.goUp();
//			hero.setGravity(Math.abs(hero.getGravity()));
//			
//		}
	}

}

