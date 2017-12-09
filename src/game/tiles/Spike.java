package game.tiles;

import game.GameObject;
import game.interfaces.Interactable;

import java.awt.Rectangle;

import br.senai.sc.engine.Utils;

public class Spike extends GameObject implements Interactable{

	
	public Spike(int posX, int posY) {
		super("spike.png", posX, posY, 1, 1);
		
	}
	
	public Spike(int posX, int posY,float theta){
		this(posX, posY);
		super.setTheta(theta);
		
	}


	@Override
	public Rectangle getRectangle() {
		Rectangle r = new Rectangle(getPosX(), getPosY()+getHeight()/2, getWidth(), getHeight()/2);
		
		return Utils.getInstance().rotateRectangleByAngle(r, super.getTheta(), getPosY()+getHeight()/2, getHeight()/2);
	}


	@Override
	public void actionEnter(GameObject gameobj) {		
		
		if(game.TrueHero.class.isInstance(gameobj)){
			game.TrueHero hero = (game.TrueHero) gameobj;
			hero.takeDamage();
			hero.halt();
		}
		
	}
	
	@Override
	public void actionStay(GameObject gameobj) {
		
	}


	@Override
	public void actionExit(GameObject gameobj) {
		
	}


	
	
	
	
}
