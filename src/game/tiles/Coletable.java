package game.tiles;



import game.AnimatedObject;
import game.GameManager;
import game.GameObject;
import game.interfaces.Interactable;

public class Coletable extends AnimatedObject implements Interactable{

	public Coletable(int posX, int posY) {
		super("coinssonic.png", posX, posY, 4, 4);
		setScale(0.5f);
	}

	@Override
	public void actionEnter(GameObject gameobj) {
		if(game.TrueHero.class.isInstance(gameobj)){
			game.TrueHero hero = (game.TrueHero) gameobj;
			GameManager.getInstance().addScore(10);
			System.out.println(GameManager.getInstance().getScore());
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
