package game.tiles;

import game.GameObject;
import game.interfaces.Interactable;

public class GravityUp extends GameObject implements Interactable {

	public GravityUp(int posX, int posY) {
		super("gravityUp.png", posX, posY, 1, 1);

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
			hero.goUp();
			
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
