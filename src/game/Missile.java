package game;

import java.awt.event.KeyEvent;

import game.interfaces.Collidable;
import game.interfaces.Controllable;
import game.interfaces.Interactable;
import game.interfaces.Updatable;

public class Missile extends Enemy implements Interactable {

	public Missile(int posX, int posY, int velX, int velY,double Theta) {
		super("rocket.png", posX, posY, 2, 1, velX, velY);
		super.setTheta((float)Theta);

	}

	@Override
	public void update() {
		super.update();
		super.setPosX(super.getPosX() + super.getVelX());
		super.setPosY(super.getPosY() + super.getVelY());

	}

	@Override
	public void collisionEnter(GameObject objInCol) {
		super.collisionEnter(objInCol);

		if (objInCol.getTag() == GameTags.Ground) {
			super.setVelX(super.getVelX() * (-1));
			super.setVelY(super.getVelY() * (-1));
			super.setTheta(super.getTheta() + (float) Math.PI);


		}
	}

	@Override
	public void actionEnter(GameObject gameobj) {

		if (game.TrueHero.class.isInstance(gameobj)) {
			game.TrueHero hero = (game.TrueHero) gameobj;
			hero.takeDamage();
			setDestroyed(true);

		}
	}

	@Override
	public void actionStay(GameObject gameobj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionExit(GameObject gameobj) {
		// TODO Auto-generated method stub

	}

}
