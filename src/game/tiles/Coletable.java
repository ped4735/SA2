package game.tiles;



import game.AnimatedObject;
import game.GameManager;
import game.GameObject;
import game.PlaySound;
import game.interfaces.Interactable;
import game.scenes.LevelManager;

public class Coletable extends AnimatedObject implements Interactable{

	PlaySound ps;

	public Coletable(int posX, int posY) {
		super("jewel.png", posX, posY, 4, 2);
		setScale(0.6f);
		ps =  new PlaySound("coin.wav");
	}

	@Override
	public void actionEnter(GameObject gameobj) {
		if(game.TrueHero.class.isInstance(gameobj)){
			game.TrueHero hero = (game.TrueHero) gameobj;
			ps.start();
			GameManager.getInstance().addScore(10);
			System.out.println(GameManager.getInstance().getScore());
			
			LevelManager.getInstance().removeColletableInScene();
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
