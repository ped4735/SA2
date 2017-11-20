package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
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
	private float sizeX;
	private float sizeY;
	private int life;
	// states
	private boolean isGrounded;
	private boolean isGravityOn;
	// lists
	private List<GameObject> objsInInteraction = new ArrayList<GameObject>();
	// position
	private float posX;
	private float posY;
	private float theta = 0.0f;
	// speed
	private float velX;
	private float velY;
	private float velXadded;
	private float velYadded;
	private float velTotal;
	private float turnRate = (float) Math.PI / 64f;
	// aceleration
	private float aceX;
	private float aceY;
	private float aceTotal = 20;
	// controls
	private boolean wPressed;
	private boolean aPressed;
	private boolean sPressed;
	private boolean dPressed;

	// TODO: deltatime?
	private float deltaTime = 0.015f;

	// TODO: tirar essas coisas do personagem
	private float gravity = 1;

	
	
	public TrueHero(String spriteFileName, int posX, int posY, int colFrames, int lineFrames) {
		super(spriteFileName, posX, posY, colFrames, lineFrames);
		// TODO corrigir para o construtor da matriz!
		
		init();
	}
	
	private void init(){
		this.posX = (float) super.getPosX();
		this.posY = (float) super.getPosY();
	}

	
	
	@Override
	public void update() {

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

		// temp 
		setFrameX(getFrameX() + 1);
		if (getFrameX() >= getColFrames()) {
			setFrameX(0);
			setFrameY(getFrameY() + 1);
			if (getFrameY() >= getLineFrames()) {
				setFrameY(0);
			}
		}
	}

	private void floatToInts() {
		super.setPosX((int) posX);
		super.setPosY((int) posY);

		// posYint = (int) posY;
	}

	public void takeDamage() {
		setLife(getLife() - 1);
		if (getLife() <= 0) {
			JetpackGame.currentGameState = GameStates.GameOver;
		} else {
			// TODO reset position
			// this.setPosX(posXinit);
			// this.setPosY(posYinit);
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
		aceX += aceTotal * (float) Math.cos(-theta);
		aceY += aceTotal * (-1) * (float) Math.sin(-theta);

//		velX += aceX * deltaTime;
//		velY += aceY * deltaTime;
	}
	
	public void halt(){
		this.velX = 0;
		this.velY = 0;
	}
	
	
	
	//draw
	public void draw(Graphics2D g){
		
		drawDebug(g);
		
		g.drawImage(getSprite(), getPosX(), getPosY(), getPosX()+getWidth(), getPosY()+getHeight(),
				getFrameX()*getWidth(), getFrameY()*getHeight(), getFrameX()*getWidth()+getWidth(), getFrameY()*getHeight()+getHeight(), null);
	}
	
	
	private void drawDebug(Graphics2D g) {
		g.setColor(Color.white);
		g.drawString("PosX: " + posX, 20,120);
		g.drawString("PosY: " + posY, 20,140);
		g.drawString("velX:" + velX, 20, 20);
		g.drawString("velY:" + velY, 20, 40);
		g.drawString("Theta: " + theta, 20, 60);
		g.drawString("AceX: "+ aceX, 20, 80);
		g.drawString("AceY: "+ aceY, 20, 100);

		
		int offset = 30;
		g.setColor(Color.red);
		g.drawLine((int)posX - offset, (int)posY, (int)posX + (int) (velX * 0.5f) - offset, (int)posY + (int) (velY * 0.5f));
		g.drawLine((int)posX - offset, (int)posY, (int)posX + (int) (velX * 0.5f) - offset, (int)posY + (int) (velY * 0.5f));
		g.setColor(Color.green);
		g.drawLine((int)posX - offset, (int)posY, (int)posX + (int) (aceX*1f) - offset, (int)posY + (int) (aceY*1f));

	}
	// colisão
	@Override
	public void collisionEnter(GameObject objInCol) {

		if (!objsInInteraction.contains(objInCol)) {
			objsInInteraction.add(objInCol);

			Interactable objectToInteract = (Interactable) objInCol;
			objectToInteract.actionEnter(this);
		}
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
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

}
