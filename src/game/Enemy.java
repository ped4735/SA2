package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.interfaces.Collidable;
import game.interfaces.Interactable;
import game.interfaces.Updatable;

public abstract class Enemy extends AnimatedObject implements Updatable, Collidable {

	private List<GameObject> objsInInteraction = new ArrayList<GameObject>();

	private int velX;
	private int velY;

	public Enemy(String spriteFileName, int posX, int posY, int colFrames, int lineFrames, int velX, int velY) {
		super(spriteFileName, posX, posY, colFrames, lineFrames);
		this.velX = velX;
		this.velY = velY;

	}

	// colision with tiles
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

	public List<GameObject> getObjsInInteraction() {
		return objsInInteraction;
	}

	public void setObjsInInteraction(List<GameObject> objsInInteraction) {
		this.objsInInteraction = objsInInteraction;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

}
