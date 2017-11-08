package game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import br.senai.sc.engine.Utils;

public abstract class GameObject{

	private Image sprite;
	private int frameX;
	private int frameY;
	private int colFrames;
	private int lineFrames;
	private int posX;
	private int posY;
	private int width;
	private int height;
	private GameTags tag;
	private boolean destroyed;
	
	
	//Constructors
	public GameObject(String spriteFileName, int posX, int posY, int colFrames, int lineFrames, GameTags tag) {
		this.sprite = Utils.getInstance().loadImage("images/" + spriteFileName);
		this.posX = posX;
		this.posY = posY;
		this.frameX = 0;
		this.frameY = 0;
		this.colFrames = colFrames;
		this.lineFrames = lineFrames;
		this.width = sprite.getWidth(null)/colFrames;
		this.height = sprite.getHeight(null)/lineFrames;
		this.tag = tag;
	}
	public GameObject(String spriteFileName, int posX, int posY, int colFrames, int lineFrames) {
		this.sprite = Utils.getInstance().loadImage("images/" + spriteFileName);
		this.posX = posX;
		this.posY = posY;
		this.frameX = 0;
		this.frameY = 0;
		this.colFrames = colFrames;
		this.lineFrames = lineFrames;
		this.width = sprite.getWidth(null)/colFrames;
		this.height = sprite.getHeight(null)/lineFrames;
		this.tag = GameTags.Default;
	}
	
	
	//Getters and Setters
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
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	
	//Methods
	public void draw(Graphics2D g){
		/*g.setColor(new Color(255, 255, 255));
		g.fillRect(posX, posY, width, height);*/
		
		g.drawImage(getSprite(), getPosX(), getPosY(), getPosX()+getWidth(), getPosY()+getHeight(),
				getFrameX()*getWidth(), getFrameY()*getHeight(), getFrameX()*getWidth()+getWidth(), getFrameY()*getHeight()+getHeight(), null);
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(posX, posY, width, height);
	}

	
}
