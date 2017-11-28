package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.rmi.CORBA.Util;

import br.senai.sc.engine.Utils;

public abstract class GameObject {

	private Image sprite;
	private int frameX;
	private int frameY;
	private int colFrames;
	private int lineFrames;
	private int posXLocal;
	private int posYLocal;
	private int width;
	private int height;
	private GameTags tag;
	private boolean destroyed;
	private float scale;
	private float thetaLocal;

	// Constructors
	public GameObject(String spriteFileName, int posX, int posY, int colFrames, int lineFrames) {
		this.sprite = Utils.getInstance().loadImage("images/" + spriteFileName);
		this.posXLocal = posX;
		this.posYLocal = posY;
		this.frameX = 0;
		this.frameY = 0;
		this.colFrames = colFrames;
		this.lineFrames = lineFrames;
		this.width = sprite.getWidth(null) / colFrames;
		this.height = sprite.getHeight(null) / lineFrames;
		this.tag = GameTags.Default;
		this.scale = 1f;
		thetaLocal = 0;
		
	}

	public GameObject(String spriteFileName, int posX, int posY, int colFrames, int lineFrames, GameTags tag) {
		this(spriteFileName, posX, posY, colFrames, lineFrames);
		this.tag = tag;
	}


	public GameObject(String spriteFileName, int posX, int posY, int colFrames, int lineFrames, float scale) {
		this(spriteFileName, posX, posY, colFrames, lineFrames);
		this.scale = scale;

	}

	// Getters and Setters
	public int getWidth() {
		return width;
	}

	public int getFrameX() {
		return frameX;
	}

	public void setFrameX(int frameX) {
		this.frameX = frameX;
	}

	public int getFrameY() {
		return frameY;
	}

	public void setFrameY(int frameY) {
		this.frameY = frameY;
	}

	public int getColFrames() {
		return colFrames;
	}

	public void setColFrames(int colFrames) {
		this.colFrames = colFrames;
	}

	public int getLineFrames() {
		return lineFrames;
	}

	public void setLineFrames(int lineFrames) {
		this.lineFrames = lineFrames;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public GameTags getTag() {
		return tag;
	}

	public void setTag(GameTags tag) {
		this.tag = tag;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public int getPosX() {
		return posXLocal;
	}

	public void setPosX(int posX) {
		this.posXLocal = posX;
	}

	public int getPosY() {
		return posYLocal;
	}

	public void setPosY(int posY) {
		this.posYLocal = posY;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	

	public float getTheta() {
		return thetaLocal;
	}

	public void setTheta(float thetaLocal) {
		
		this.thetaLocal = thetaLocal;
	}

	// Methods
	public void draw(Graphics2D g) {
		 /*correto
		g.drawImage(getSprite(), 
		getPosX(),
		getPosY(),
		getPosX() + (int) (getWidth() * scale),				
		getPosY() + (int) (getHeight() * scale),
		getFrameX() * getWidth(), getFrameY() * getHeight(),
		getFrameX() * getWidth() + getWidth(),
		getFrameY() * getHeight() + getHeight(),
		null);
		*/
		g.rotate(getTheta(),getPosX()+getWidth()/2,getPosY()+getHeight()/2);
		g.drawImage(getSprite(), 
		getPosX(),
		getPosY(),
		getPosX() + (int) (getWidth() * scale),				
		getPosY() + (int) (getHeight() * scale),
		getFrameX() * getWidth(), getFrameY() * getHeight(),
		getFrameX() * getWidth() + getWidth(),
		getFrameY() * getHeight() + getHeight(),
		null);
		
		g.rotate((-1)*getTheta(),getPosX()+getWidth()/2,getPosY()+getHeight()/2);

		
		
		
/*		
		float globalScale = Utils.getInstance().getGlobalScale();
		g.drawImage(getSprite(),
				(int) (globalScale*getPosX()), 
				(int) (globalScale*getPosY()),
				(int) (globalScale*(getPosX() + (getWidth() * scale))),
				(int) (globalScale *(getPosY() + (getHeight() * scale))),
				getFrameX() * getWidth(),
				getFrameY() * getHeight(),
				getFrameX() * getWidth() + getWidth(),
				getFrameY() * getHeight() + getHeight(), 
				null);
*/

		
		if (Utils.getInstance().isDebug() && !this.getClass().equals(TrueHero.class)) {
			g.setColor(Color.GREEN);
			g.draw(getRectangle());
		}

	}

	public Rectangle getRectangle() {
		// return hitBox;
		return new Rectangle(posXLocal, posYLocal, (int) (width * scale), (int) (height * scale));

	}

}
