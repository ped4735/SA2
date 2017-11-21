package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

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

	// Constructors
	public GameObject(String spriteFileName, int posX, int posY, int colFrames, int lineFrames, GameTags tag) {
		this.sprite = Utils.getInstance().loadImage("images/" + spriteFileName);
		this.posXLocal = posX;
		this.posYLocal = posY;
		this.frameX = 0;
		this.frameY = 0;
		this.colFrames = colFrames;
		this.lineFrames = lineFrames;
		this.width = sprite.getWidth(null) / colFrames;
		this.height = sprite.getHeight(null) / lineFrames;
		this.tag = tag;
		this.scale = 1;
	}

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
		this.scale = 1;
	}

	public GameObject(String spriteFileName, int posX, int posY, int colFrames, int lineFrames, float scale) {
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

	// Methods
	public void draw(Graphics2D g) {
		g.drawImage(getSprite(), getPosX(), getPosY(), getPosX() + (int) (getWidth() * scale),
				getPosY() + (int) (getHeight() * scale), getFrameX() * getWidth(), getFrameY() * getHeight(),
				getFrameX() * getWidth() + getWidth(), getFrameY() * getHeight() + getHeight(), null);

		//g.draw(getRectangle());
	}

	public Rectangle getRectangle() {
		return new Rectangle(posXLocal, posYLocal, width, height);
	}

}
