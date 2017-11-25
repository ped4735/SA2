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
		
		if(game.TrueHero.class.isInstance(gameobj)){
			game.TrueHero hero = (game.TrueHero) gameobj;
			hero.setGrounded(true);
			hero.halt();
			//hero.setVelX((-2f)*hero.getVelX()/hero.getVelX());
		}
		
	}
	
	
	@Override
	public void actionStay(GameObject gameobj) {
		
		if(game.TrueHero.class.isInstance(gameobj)){
			game.TrueHero hero = (game.TrueHero) gameobj;
//			int dirX = (int)hero.getPosX() - this.getPosX();
//			
//			if (dirX<(int)hero.getVelX() || dirX>(int)hero.getVelX()){
//				System.out.println("naaao");
//				hero.setVelX(0);
//			}
		}
		
		
	}

	@Override
	public void actionExit(GameObject gameobj) {
		if (game.Hero.class.isInstance(gameobj)) {
			game.Hero hero = (game.Hero) gameobj;
				hero.setGrounded(false);
		}
		if(game.TrueHero.class.isInstance(gameobj)){
			game.TrueHero hero = (game.TrueHero) gameobj;
			hero.setGrounded(false);
		}
			
	}



	

}
