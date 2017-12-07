package game;

import java.awt.Graphics2D;
import java.util.Random;

import br.senai.sc.engine.Utils;
import game.interfaces.Updatable;

public class MovableBackground extends GameObject implements Updatable {

	private int ImagePosX;
	private int ImagePosY;
	
	private float posX;
	private float posY;
	
	private float velX;
	private float velY;

	private float scaleVel;
	
	public MovableBackground(String spriteFileName, int posX, int posY, int colFrames, int lineFrames) {
		super(spriteFileName, posX, posY, colFrames, lineFrames);

	}


	public MovableBackground(String spriteFileName) {
		this(spriteFileName, 0, 0, 1, 1);
		//this.setWidth(1280);
		//this.setHeight(720);
		Random r = new Random();

		
		ImagePosX = (int)(r.nextFloat()*(getWidth() - 100) );
		ImagePosY = (int)(r.nextFloat()*(getHeight() - 100) );
		
		super.setScale(1f);
		
		velX = r.nextFloat()*5f - 2f;
		velX = r.nextFloat()*5f - 2f;
		//scaleVel = (r.nextFloat() -0.5f)/100f;

		System.out.println(scaleVel);

	}

	@Override
	public void update() {
		
		//setPosX((int)(posX + velX));
		//setPosY((int)(posY + velY));
		setScale(getScale() + scaleVel);
		
		ImagePosX += velX;
		velX = velX/2;
		ImagePosY += velY;
		velY = velY/2;
	}

	@Override
	public void draw(Graphics2D g) {
		//super.draw(g);



//		g.drawImage(getSprite(), 
//				getPosX(),
//				getPosY(), 
//				getPosX() + Utils.getInstance().getWidth(),
//				getPosY() + Utils.getInstance().getHeight(),
//				ImagePosX, 
//				ImagePosY, 
//				ImagePosX + Utils.getInstance().getWidth()*(int)getScale(), 
//				ImagePosY +Utils.getInstance().getHeight()*(int)getScale(),
//				null);
//
//	}
	
	g.drawImage(getSprite(), 
			0,
			0, 
			Utils.getInstance().getWidth(),
			Utils.getInstance().getHeight(),
			ImagePosX, 
			ImagePosY, 
			ImagePosX + (int)(Utils.getInstance().getWidth()*getScale()), 
			ImagePosY + (int)(Utils.getInstance().getHeight()*getScale()),
			null);
	
	System.out.println("" + (ImagePosX + (int)(Utils.getInstance().getWidth()*getScale()))  );

}
}
