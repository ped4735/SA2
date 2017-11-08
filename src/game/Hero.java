package game;

import game.interfaces.Collidable;
import game.interfaces.Controllable;
import game.interfaces.Interactable;
import game.interfaces.Updatable;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Hero extends GameObject implements Controllable, Updatable, Collidable {

	private int life;

	private List<GameObject> objsInInteraction = new ArrayList<GameObject>();
	
	// Controls
	private boolean wPressed;
	private boolean aPressed;
	private boolean sPressed;
	private boolean dPressed;

	// veloci
	private int velX;
	private int velY;

	private int posXinit;
	private int posYinit;

	// Flags
	private boolean isGrounded;

	public Hero(String spriteFileName, int posX, int posY, int colFrames, int lineFrames) {
		super(spriteFileName, posX, posY, colFrames, lineFrames);
		this.posXinit = posX;
		this.posYinit = posY;

		this.init();
	}

	// Methods
	private void init() {
		this.life = 3;

		velX = 5;
		velY = 5;
	}

	// Get Set
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;

	}

	public boolean iswPressed() {
		return wPressed;
	}

	public void setwPressed(boolean wPressed) {
		this.wPressed = wPressed;
	}

	public boolean isaPressed() {
		return aPressed;
	}

	public void setaPressed(boolean aPressed) {
		this.aPressed = aPressed;
	}

	public boolean issPressed() {
		return sPressed;
	}

	public void setsPressed(boolean sPressed) {
		this.sPressed = sPressed;
	}

	public boolean isdPressed() {
		return dPressed;
	}

	public void setdPressed(boolean dPressed) {
		this.dPressed = dPressed;
	}

	public boolean isGrounded() {
		return isGrounded;
	}

	public void setGrounded(boolean isGrounded) {
		this.isGrounded = isGrounded;
	}

	public void takeDamage() {
		setLife(getLife() - 1);
		if (getLife() <= 0) {
			JetpackGame.currentGameState = GameStates.GameOver;
		} else {
			// TODO reset position
			this.setPosX(posXinit);
			this.setPosY(posYinit);
		}
	}

	@Override
	public void update() {
		if (aPressed == true) {

			this.setPosX(getPosX() - velX);
		}
		if (dPressed == true) {
			this.setPosX(getPosX() + velX);
		}

		if (wPressed == true) {
			this.setPosY(getPosY() - velY);
		}
		if (sPressed == true) {
			this.setPosY(getPosY() + velY);
		}

		if (!isGrounded) {
			this.setPosY(getPosY() + 2);
		}

		setFrameX(getFrameX() + 1);
		if (getFrameX() >= getColFrames()) {
			setFrameX(0);
			setFrameY(getFrameY() + 1);
			if (getFrameY() >= getLineFrames()) {
				setFrameY(0);
			}
		}

	}

	@Override
	public void pressAction(KeyEvent key) {

		if (key.getKeyCode() == KeyEvent.VK_W) {
			setwPressed(true);
		}
		if (key.getKeyCode() == KeyEvent.VK_S) {
			setsPressed(true);
		}
		if (key.getKeyCode() == KeyEvent.VK_A) {
			setaPressed(true);
		}
		if (key.getKeyCode() == KeyEvent.VK_D) {
			setdPressed(true);
		}

	}

	@Override
	public void releaseAction(KeyEvent key) {

		if (key.getKeyCode() == KeyEvent.VK_W) {
			setwPressed(false);
		}
		if (key.getKeyCode() == KeyEvent.VK_S) {
			setsPressed(false);
		}
		if (key.getKeyCode() == KeyEvent.VK_A) {
			setaPressed(false);
		}
		if (key.getKeyCode() == KeyEvent.VK_D) {
			setdPressed(false);
		}

	}
	
	
	

	@Override
	public void collisionEnter(GameObject objInCol) {
		
			if(!objsInInteraction.contains(objInCol)){
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

}
