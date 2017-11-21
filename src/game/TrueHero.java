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
	private float theta = 0.0f;
	private int posXinit;
	private int posYinit;
	// speed
	private float velX;
	private float velY;
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
	private float deltaTime = 0.05f;

	// TODO: tirar essas coisas do personagem
	private float gravity = 5;

	public TrueHero(String spriteFileName, int posX, int posY, int colFrames, int lineFrames) {
		super(spriteFileName, posX, posY, colFrames, lineFrames);
		// TODO corrigir para o construtor da matriz!

		init();
	}

	private void init() {
		this.posX = (float) super.getPosX();
		this.posY = (float) super.getPosY();
		this.posXinit = (int) this.posX;
		this.posYinit = (int) this.posY;
		this.life = 3;
		isGravityOn = true;
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

		// posYint = (int) posY;
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

		// velX += aceX * deltaTime;
		// velY += aceY * deltaTime;
	}

	public void halt() {
		this.velX = 0;
		this.velY = 0;

	}

	// draw
	// float abc=0.0f;
	public void draw(Graphics2D g) {
		// float scale = (float)Math.sin(abc) + 1.5f;
		// abc+=0.1;
		float scale = 1f;

		drawDebug(g);

		g.rotate(theta, posX + (sizeX * scale) / 2, posY + (sizeY * scale) / 2);

		g.drawImage(getSprite(), getPosX(), getPosY(), getPosX() + (int) (getWidth() * scale),
				getPosY() + (int) (getHeight() * scale), getFrameX() * getWidth(), getFrameY() * getHeight(),
				getFrameX() * getWidth() + getWidth(), getFrameY() * getHeight() + getHeight(), null);

		g.rotate((-1) * theta, posX + (sizeX * scale) / 2, posY + (sizeY * scale) / 2);

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
		if (isGrounded) {
			isGravityOn = false;
		} else {
			isGravityOn = true;
		}
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

}
