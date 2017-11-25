package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.senai.sc.engine.Utils;
import game.interfaces.Collidable;
import game.interfaces.Controllable;
import game.interfaces.Interactable;
import game.interfaces.Updatable;

public class TrueHero extends AnimatedObject implements Controllable, Updatable, Collidable {

	// propriedades
	private float sizeX = super.getWidth();
	private float sizeY = super.getHeight();
	private int life;
	// states
	private boolean isGrounded;
	private boolean isGravityOn;
	// lists
	private List<GameObject> objsInInteraction = new ArrayList<GameObject>();
	// position
	private float posX;
	private float posY;
	private final float thetaInit = (float) ((-1f) * Math.PI / 2f);
	private float theta;// = (float) ((1f) * Math.PI / 2f);
	private int posXinit;
	private int posYinit;
	// speed
	private float velX;
	private float velY;
	private float turnRate = (float) Math.PI / 64f;
	// aceleration
	private float aceX;
	private float aceY;
	private float aceTotal = 40;
	// controls
	private boolean wPressed;
	private boolean aPressed;
	private boolean sPressed;
	private boolean dPressed;

	// TODO: deltatime?
	private float deltaTime = 0.05f;

	// TODO: tirar essas coisas do personagem
	private float gravity = 20;

	public TrueHero(String spriteFileName, int posX, int posY, int colFrames, int lineFrames) {
		//super(spriteFileName, posX, posY, colFrames, lineFrames);
		super(spriteFileName, posX, posY, colFrames, lineFrames, new int[] {1,1});
		setTag(GameTags.Player);
		init();
	}

	private void init() {
		this.posX = (float) super.getPosX();
		this.posY = (float) super.getPosY();
		this.posXinit = (int) this.posX;
		this.posYinit = (int) this.posY;
		this.life = 3;
		isGravityOn = true;
		theta = thetaInit;
		setScale(1f);
	}

	@Override
	public void update() {

		super.update();
		aceX = 0.0f;
		aceY = 0.0f;
		
		if (wPressed) {
			this.forward();
		}

		if (aPressed || dPressed) {
			this.turn();
					}

		if (isGravityOn) {
			this.fall();
		}

		// TODO criar um metodo para fazer isso
		velX += aceX * deltaTime;
		velY += aceY * deltaTime;

		posX += velX * deltaTime;
		posY += velY * deltaTime;

		this.floatToInts();

	}

	private void floatToInts() {
		super.setPosX((int) posX);
		super.setPosY((int) posY);

	}

	public void takeDamage() {
		setLife(getLife() - 1);
		if (getLife() <= 0) {
			JetpackGame.currentGameState = GameStates.GameOver;
		} else {

			this.setPosX(posXinit);
			this.setPosY(posYinit);
			posX = getPosX();
			posY = getPosY();
			theta = 0;
			setVelX(0);
			setVelY(0);
		}
	}

	private void fall() {
		aceY += gravity;
	}

	private void turn() {
		if (aPressed) {
			theta -= turnRate;
		} else if (dPressed) {
			theta += turnRate;
		}
	}

	private void forward() {
		//float a = (float) (Math.PI / 2f) - theta;
		aceX += aceTotal * (float) Math.cos(-theta);
		aceY += aceTotal * (-1) * (float) Math.sin(-theta);

		// velX += aceX * deltaTime;
		// velY += aceY * deltaTime;
	}

	public void halt() {
		this.velX = 0;
		this.velY = 0;

	}

	@Override
	public void draw(Graphics2D g) {

		//setScale(1f);

		drawDebug(g);

		float a = (float) ((1f) * Math.PI / 2f);
		g.rotate(theta + a, posX + (sizeX * getScale()) / 2, posY + (sizeY * getScale()) / 2);
		super.draw(g);
		g.rotate((-1) * (theta + a), posX + (sizeX * getScale()) / 2, posY + (sizeY * getScale()) / 2);

		g.draw(getRectangle());
	
	}
	
	@Override
	public Rectangle getRectangle(){
		return Utils.getInstance().reshapeRectangleByAngle(super.getRectangle(), theta,thetaInit);
		
	}
	
	
	public void scaleDown(){
		setScale(getScale() - 0.05f);
		if (getScale() <= 0.1f){
			this.takeDamage();
			setScale(1f);
		}
	}
	
	public void scaleUp(){
		setScale(getScale() + 0.005f);
		if (getScale() >= 0.1f){
			setScale(1f);
		}
	}
	
	public void scaleUpTillNormal(){

	}
	
	

	private void drawDebug(Graphics2D g) {
		g.setColor(Color.white);
		g.drawString("PosX: " + posX, 20, 120);
		g.drawString("PosY: " + posY, 20, 140);
		g.drawString("velX:" + velX, 20, 20);
		g.drawString("velY:" + velY, 20, 40);
		g.drawString("Theta: " + theta, 20, 60);
		g.drawString("AceX: " + aceX, 20, 80);
		g.drawString("AceY: " + aceY, 20, 100);

		int offset = 30;
		g.setColor(Color.red);
		g.drawLine((int) posX - offset, (int) posY, (int) posX + (int) (velX * 0.5f) - offset,
				(int) posY + (int) (velY * 0.5f));
		g.drawLine((int) posX - offset, (int) posY, (int) posX + (int) (velX * 0.5f) - offset,
				(int) posY + (int) (velY * 0.5f));
		g.setColor(Color.green);
		g.drawLine((int) posX - offset, (int) posY, (int) posX + (int) (aceX * 1f) - offset,
				(int) posY + (int) (aceY * 1f));

	}

	
	
	// colisões
	@Override
	public void collisionEnter(GameObject objInCol) {

		if (!objsInInteraction.contains(objInCol)) {
			objsInInteraction.add(objInCol);

			Interactable objectToInteract = (Interactable) objInCol;
			objectToInteract.actionEnter(this);
		}
	}

	@Override
	public void collisionStay(GameObject objInCol) {
		
		Interactable objectToInteract = (Interactable) objInCol;
		objectToInteract.actionStay(this);
	}
	

	@Override
	public void collisionExit(GameObject objExtCol) {
		Iterator<GameObject> itr = objsInInteraction.listIterator();
		while (itr.hasNext()) {
			GameObject tempObj = itr.next();

			if (tempObj.equals(objExtCol)) {
				Interactable objectToExitInteract = (Interactable) objExtCol;
				objectToExitInteract.actionExit(this);

				itr.remove();

			}

		}
	}

	// key action
	@Override
	public void pressAction(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			wPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			sPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			aPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			dPressed = true;
		}
	}

	@Override
	public void releaseAction(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			wPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			sPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			aPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			dPressed = false;
		}

	}

	// setters and getters
	public boolean isGrounded() {
		return isGrounded;
	}

	public void setGrounded(boolean isGrounded) {
		this.isGrounded = isGrounded;
		if (isGrounded) {
			isGravityOn = false;
		} else {
			isGravityOn = true;
		}
		
		
		//System.out.println(isGravityOn);
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public float getAceX() {
		return aceX;
	}

	public void setAceX(float aceX) {
		this.aceX = aceX;
	}

	public float getAceY() {
		return aceY;
	}

	public void setAceY(float aceY) {
		this.aceY = aceY;
	}
	
	

}
