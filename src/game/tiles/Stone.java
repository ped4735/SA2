package game.tiles;

import java.awt.Color;
import java.awt.Graphics2D;

import br.senai.sc.engine.Utils;
import game.CollisionFace;
import game.GameObject;
import game.GameTags;
import game.interfaces.Interactable;

public class Stone extends GameObject implements Interactable {

	public Stone(int posX, int posY) {

		super("stone.png", posX, posY, 1, 1, GameTags.Ground);

	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		super.draw(g);
		
		if(Utils.getInstance().isDebug()){
			g.setColor(Color.RED);
			g.fill(Utils.getInstance().getRectangleFace(getRectangle(), CollisionFace.bot));
			g.fill(Utils.getInstance().getRectangleFace(getRectangle(), CollisionFace.top));
			g.fill(Utils.getInstance().getRectangleFace(getRectangle(), CollisionFace.left));
			g.fill(Utils.getInstance().getRectangleFace(getRectangle(), CollisionFace.right));
		}
			
	}
	

	@Override
	public void actionEnter(GameObject gameobj) {
		
		if(game.TrueHero.class.isInstance(gameobj)){
			game.TrueHero hero = (game.TrueHero) gameobj;
			
			CollisionFace colFace = Utils.getInstance().collisionEnterIn(gameobj.getRectangle(), getRectangle());
			
			/*if(colFace == CollisionFace.top_bot){
				hero.setVelY(5);
				System.out.println("Top Bot");
			}
			
			if(colFace == CollisionFace.left_right){
				hero.setVelX(5);
				System.out.println("Left Right");
			}*/
			
			
			/*switch (Utils.getInstance().collisionEnterIn(gameobj.getRectangle(), getRectangle())) {
			case top_bot:
				hero.setVelY(0);
				break;
			case left_right:
				hero.setVelX(0);
			case right_left:
				hero.setVelX(0);
				break;	
			default:
				break;
			}*/
			
			
			//hero.setGrounded(true);
			//hero.halt();
			//hero.setVelX((-2f)*hero.getVelX()/hero.getVelX());
		}
		
	}
	
	
	
	@Override
	public void actionStay(GameObject gameobj) {
		
		if(game.TrueHero.class.isInstance(gameobj)){
			game.TrueHero hero = (game.TrueHero) gameobj;
			CollisionFace colFace = Utils.getInstance().collisionEnterIn(gameobj.getRectangle(), getRectangle());			
			/*if(colFace == CollisionFace.top_bot){
				hero.setVelY(5);
				System.out.println("Top Bot");
			}
			
			if(colFace == CollisionFace.left_right){
				hero.setVelX(5);
				System.out.println("Left Right");
			}*/
			
			
			switch (Utils.getInstance().collisionEnterIn(gameobj.getRectangle(), getRectangle())) {
			case top_bot:
				hero.setVelY(5);
				//System.out.println("Top Bot");
				break;
			case left_right:
				hero.setVelX(5);
				//System.out.println("Left Right");
				break;
			case right_left:
				hero.setVelX(-5);
				//System.out.println("Right Left");
				break;
			case bot_top:
				hero.setVelY(-5);
				//System.out.println("Bot Top");
				break;
			default:
				//hero.halt();
				break;
			}
			
			
			
		}
		
		
	}

	@Override
	public void actionExit(GameObject gameobj) {
		if(game.TrueHero.class.isInstance(gameobj)){
			game.TrueHero hero = (game.TrueHero) gameobj;
			hero.setGrounded(false);
		}
			
	}



	

}
