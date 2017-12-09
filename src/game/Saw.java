package game;

import br.senai.sc.engine.Utils;
import game.interfaces.Interactable;

public class Saw extends Enemy implements Interactable{

	public Saw(int posX, int posY, int velX, int velY) {
		super("saw.png", posX, posY, 2, 2, velX, velY);
		setDelayToRun(2);
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

		if (objInCol.getTag() == GameTags.Ground && objInCol.getFrameY() == 0) {
			
			
			switch (Utils.getInstance().collisionEnterIn(getRectangle(), objInCol.getRectangle())) {
			case left_right:
				super.setVelX(Math.abs(super.getVelX()));	
				super.setTheta(super.getTheta() + (float) Math.PI);
				break;
			case right_left:
				super.setVelX(Math.abs(super.getVelX())*-1);	
				super.setTheta(super.getTheta() - (float) Math.PI);
				break;
			case top_bot:
				super.setVelY(Math.abs(super.getVelY()));
				super.setTheta(super.getTheta() + (float) Math.PI);
				break;
			case bot_top:
				super.setVelY(Math.abs(super.getVelY())*-1);
				super.setTheta(super.getTheta() - (float) Math.PI);
				break;
			default:
				break;
			}
			
			
			
			/*if(Utils.getInstance().)
			
			
			super.setVelX(super.getVelX() * (-1));
			super.setVelY(super.getVelY() * (-1));
			super.setTheta(super.getTheta() + (float) Math.PI);*/
			

		}
	}


	@Override
	public void actionEnter(GameObject gameobj) {
		if (game.TrueHero.class.isInstance(gameobj)) {
			game.TrueHero hero = (game.TrueHero) gameobj;
			hero.takeDamage();
			//setDestroyed(true);

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
