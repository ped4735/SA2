package game.tiles;

import game.AnimatedObject;
import game.GameObject;
import game.TrueHero;
import game.interfaces.Interactable;

public class BlackHole extends AnimatedObject implements Interactable {

	
	
	
	public BlackHole(int posX, int posY) {
		super("blackhole.png", posX, posY, 8, 1);
		
		super.setPosX(getPosX() - 18);
		super.setPosY(getPosY() - 18);
		
		super.getHeight();
		super.getWidth();
		
		this.setScale(0.5f);
	}

	
	
	@Override
	public void actionEnter(GameObject gameobj) {		
		if (gameobj.getClass().equals(TrueHero.class)){
			TrueHero heroi = (TrueHero)gameobj;
			
		}
		
		
	}

	@Override
	public void actionStay(GameObject gameobj) {
		if (gameobj.getClass().equals(TrueHero.class)){
			TrueHero heroi = (TrueHero)gameobj;
			heroi.scaleDown();
		}		
	}

	@Override
	public void actionExit(GameObject gameobj) {
		// TODO Auto-generated method stub
		
	}

}
