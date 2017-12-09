package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.font.TransformAttribute;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.NoninvertibleTransformException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.senai.sc.engine.Utils;
import game.interfaces.Collidable;
import game.interfaces.Controllable;
import game.interfaces.Interactable;
import game.interfaces.Updatable;
import game.scenes.Gameplay;
import game.scenes.LevelManager;
import game.scenes.Scene;

public class TrueHero extends AnimatedObject implements Controllable, Updatable, Collidable {

	// propriedades
	private float sizeX = super.getWidth();
	private float sizeY = super.getHeight();
	private int life;
	private int score;
	private float heating;
	private float maxHeating = 100f;
	private float heatingUpRate = 0.5f;
	private float heatingDownRate = 0.4f;
	// para os sons e ui
	private boolean warningOverheating;
	private boolean cooling;

	// SceneRef
	private Gameplay onScene;

	// states
	private boolean isGrounded;
	private boolean isGravityOn;
	// lists
	private List <GameObject> objsInInteraction = new ArrayList <GameObject>();
	// position
	private float posX;
	private float posY;
	private final float thetaInit = (float) ((-1f) * Math.PI / 2f);
	private float theta;// = (float) ((1f) * Math.PI / 2f);
	private int posXinit;
	private int posYinit;
	private boolean fliped;
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
	private float gravityInit = Utils.getInstance().getGravidade();
	private float gravity = gravityInit;

	// sounds
	PlaySound overheatingWarning;

	public TrueHero(String spriteFileName, int posX, int posY, int colFrames, int lineFrames, Scene onScene) {
		super(spriteFileName, posX, posY, colFrames, lineFrames);
		// super(spriteFileName, posX, posY, colFrames, lineFrames, new int[] {
		// 1, 1 });
		if (Gameplay.class.isInstance(onScene)) {
			this.onScene = (Gameplay) onScene;
		}

		setTag(GameTags.Player);
		overheatingWarning = new PlaySound("overheating.wav");
		init();
	}

	public void init() {
		this.posX = (float) super.getPosX();
		this.posY = (float) super.getPosY();
		this.posXinit = (int) this.posX;
		this.posYinit = (int) this.posY;
		this.life = LevelManager.getInstance().getGameLife();
		isGravityOn = true;
		theta = thetaInit;
		setScale(1f);
		// GameManager.getInstance().setScore(0);
		this.heating = 0;
	}

	public void start(int posX, int posY) {
		System.out.println("START!");
		super.setPosX(posX);
		super.setPosY(posY);
		this.posX = (float) super.getPosX();
		this.posY = (float) super.getPosY();

		this.posXinit = (int) getPosX();
		;
		this.posYinit = (int) getPosY();

		this.velX = 0;
		this.velY = 0;
		this.aceX = 0;
		this.aceY = 0;

		isGravityOn = true;
		theta = thetaInit;
		setScale(1f);
		heating = 0;
	}

	@Override
	public void update() {

		super.update();
		aceX = 0.0f;
		aceY = 0.0f;

		if (wPressed) {
			this.forward();
			this.heatingUp();
			this.cooling = false;
		} else {
			this.heatingDown();
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
		if (isWarningOverheating()) {
			overheatingWarning.startLoop();
		} else {
			overheatingWarning.stop();
		}

	}

	public void heatingUp() {
		cooling = false;
		this.heating += heatingUpRate;
		if (this.heating >= this.maxHeating * 0.85f) {
			warningOverheating = true;

		} else {
			warningOverheating = false;

		}

		if (this.heating >= this.maxHeating) {
			this.takeDamage();
			warningOverheating = false;
		}
	}

	public void heatingDown() {
		this.heating -= heatingDownRate;
		if (this.heating <= 0.0f) {
			this.heating = 0;
			cooling = false;
			return;
		}
		if (this.heating <= this.maxHeating * 0.85f) {
			warningOverheating = false;
		}
		cooling = true;
	}

	private void floatToInts() {
		super.setPosX((int) posX);
		super.setPosY((int) posY);

	}

	public void takeDamage() {
		overheatingWarning.stop();

		this.setPosX(posXinit);
		this.setPosY(posYinit);
		posX = getPosX();
		posY = getPosY();
		theta = thetaInit;
		setVelX(0);
		setVelY(0);
		this.heating = 0.0f;

		if (false) {
			//
			System.out.println("TakeDamage tá dando vida infinita!! não entregar assim!!");
			setLife(getLife() + 1);
			// para testar o level design
			// apagar tudo isso depois...
		}
		
		setLife(getLife() - 1);
		LevelManager.getInstance().setGameLife(getLife());
		if (getLife() <= 0) {
			JetpackGame.currentGameState = GameStates.GameOver;
		}
	}

	public void fall() {
		aceY += gravity;
	}

	public void goDown() {
		aceY += Utils.getInstance().getGravidade();
		velY += aceY * deltaTime;

	}

	public void goUp() {
		aceY -= gravity;
		velY += aceY * deltaTime;

	}

	private void turn() {
		if (aPressed) {
			fliped = true;
			theta -= turnRate;
		} else if (dPressed) {
			fliped = false;

			theta += turnRate;
		}
	}

	private void forward() {
		// float a = (float) (Math.PI / 2f) - theta;
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

		if (Utils.getInstance().isDebug()) {
			drawDebug(g);
		}

		float a = (float) ((1f) * Math.PI / 2f);
		g.rotate(theta + a, posX + (sizeX * getScale()) / 2, posY + (sizeY * getScale()) / 2);
		if (fliped) {
			g.drawImage(getSprite(), super.getPosX(), super.getPosY(),
					super.getPosX() + (int) (getWidth() * super.getScale()),
					super.getPosY() + (int) (getHeight() * super.getScale()), getFrameX() * getWidth() + getWidth(),
					getFrameY() * getHeight(), getFrameX() * getWidth(), getFrameY() * getHeight() + getHeight(), null);
		} else {
			super.draw(g);
		}
		g.rotate((-1) * (theta + a), posX + (sizeX * getScale()) / 2, posY + (sizeY * getScale()) / 2);

	}

	@Override
	public Rectangle getRectangle() {
		return Utils.getInstance().reshapeRectangleByAngle(super.getRectangle(), theta, thetaInit);
	}

	public void scaleDown() {
		setScale(getScale() - 0.05f);
		if (getScale() <= 0.1f) {
			this.takeDamage();
			setScale(1f);
		}
	}

	public void scaleUp() {
		setScale(getScale() + 0.005f);
		if (getScale() >= 0.1f) {
			setScale(1f);
		}
	}

	public void scaleUpTillNormal() {

	}

	private void drawDebug(Graphics2D g) {
		g.setColor(Color.white);
		g.drawString("velX:" + velX, 20, 20);
		g.drawString("velY:" + velY, 20, 40);
		g.drawString("Theta: " + theta, 20, 60);
		g.drawString("AceX: " + aceX, 20, 80);
		g.drawString("AceY: " + aceY, 20, 100);
		g.drawString("PosX: " + posX, 20, 120);
		g.drawString("PosY: " + posY, 20, 140);
		g.drawString("Life:" + this.getLife(), 20, 160);
		g.drawString("Heating: " + heating, 20, 180);

		int offset = 30;
		g.setColor(Color.red);
		g.drawLine((int) posX - offset, (int) posY, (int) posX + (int) (velX * 0.5f) - offset,
				(int) posY + (int) (velY * 0.5f));
		g.drawLine((int) posX - offset, (int) posY, (int) posX + (int) (velX * 0.5f) - offset,
				(int) posY + (int) (velY * 0.5f));
		g.setColor(Color.green);
		g.drawLine((int) posX - offset, (int) posY, (int) posX + (int) (aceX * 1f) - offset,
				(int) posY + (int) (aceY * 1f));

		if (Utils.getInstance().isDebug()) {
			g.draw(getRectangle());
			g.setColor(Color.BLUE);
			g.fill(Utils.getInstance().getRectangleFace(getRectangle(), CollisionFace.bot));
			g.setColor(Color.GREEN);
			g.fill(Utils.getInstance().getRectangleFace(getRectangle(), CollisionFace.top));
			g.setColor(Color.CYAN);
			g.fill(Utils.getInstance().getRectangleFace(getRectangle(), CollisionFace.left));
			g.setColor(Color.RED);
			g.fill(Utils.getInstance().getRectangleFace(getRectangle(), CollisionFace.right));
		}
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
		Iterator <GameObject> itr = objsInInteraction.listIterator();
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
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			wPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			sPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			aPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			dPressed = true;
		}
	}

	@Override
	public void releaseAction(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			wPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			sPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			aPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
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

		// System.out.println(isGravityOn);
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public float getMaxHeating() {
		return maxHeating;
	}

	public float getHeating() {
		return heating;
	}

	public void setHeating(float heating) {
		this.heating = heating;
	}

	public boolean isWarningOverheating() {
		return warningOverheating;
	}

	public void setWarningOverheating(boolean warningOverheating) {
		this.warningOverheating = warningOverheating;
	}

	public boolean isCooling() {
		return cooling;
	}

	public void setCooling(boolean cooling) {
		this.cooling = cooling;
	}

}
