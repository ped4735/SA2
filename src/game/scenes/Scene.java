package game.scenes;

import game.GameObject;
import game.interfaces.Clickable;
import game.interfaces.Collidable;
import game.interfaces.Controllable;
import game.interfaces.Interactable;
import game.interfaces.Updatable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import br.senai.sc.engine.Utils;

public abstract class Scene {

	private List<GameObject> objsInScene = new ArrayList<GameObject>();

	
	public List<GameObject> getObjsInScene() {
		return objsInScene;
	}

	public void setObjs(List<GameObject> objs) {
		this.objsInScene = objs;
	}
	

	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Utils.getInstance().getWidth(), Utils.getInstance().getHeight());
		
		
		Iterator<GameObject> itr = objsInScene.iterator();
		while (itr.hasNext()) {
			itr.next().draw(g);
		}

	}

	public void update() {

		Iterator<GameObject> itr = objsInScene.listIterator();
		while (itr.hasNext()) {
			GameObject tempObj = itr.next();

			if (Updatable.class.isInstance(tempObj)) {
				Updatable updatable = (Updatable) tempObj;
				updatable.update();

			}

		}

	}

	public void click(int posX, int posY) {

		Iterator<GameObject> itr = objsInScene.listIterator();
		while (itr.hasNext()) {
			GameObject tempObj = itr.next();

			if (Clickable.class.isInstance(tempObj)) {
				Clickable clicavel = (Clickable) tempObj;
				clicavel.click(posX, posY);
			}

		}
	}

	public void collision() {

		Iterator<GameObject> itr = objsInScene.listIterator();
		while (itr.hasNext()) {
			
			GameObject tempObj = itr.next();

			if (Collidable.class.isInstance(tempObj)) {
				Collidable collidable = (Collidable) tempObj;

				Iterator<GameObject> itr2 = objsInScene.listIterator();
				while (itr2.hasNext()) {
					GameObject tempObj2 = itr2.next();
					if (Interactable.class.isInstance(tempObj2)) {
						
						if (tempObj.getRectangle().intersects(tempObj2.getRectangle()) && !tempObj.equals(tempObj2)) {
							collidable.collisionEnter(tempObj2);
							collidable.collisionStay(tempObj2);
						}else{
							collidable.collisionExit(tempObj2);
						}
						
												
					}
					
					
				}
			}
			
			if(tempObj.isDestroyed()){
				itr.remove();
			}

		}

	}

	public void isOverObject(int posX, int posY) {

		Iterator<GameObject> itr = objsInScene.listIterator();
		while (itr.hasNext()) {
			GameObject tempObj = itr.next();

			if (Clickable.class.isInstance(tempObj)) {
				Clickable clicavel = (Clickable) tempObj;
				clicavel.isOverObject(posX, posY);
			}

		}
	}

	public void keyPressed(KeyEvent key) {
		
		Iterator<GameObject> itr = objsInScene.listIterator();
		while (itr.hasNext()) {
			GameObject tempObj = itr.next();

			if (Controllable.class.isInstance(tempObj)) {
				Controllable controlable = (Controllable) tempObj;
				controlable.pressAction(key);
			}

		}
	}

	public void keyReleased(KeyEvent key) {

		Iterator<GameObject> itr = objsInScene.listIterator();
		while (itr.hasNext()) {
			GameObject tempObj = itr.next();

			if (Controllable.class.isInstance(tempObj)) {
				Controllable controlable = (Controllable) tempObj;
				controlable.releaseAction(key);
			}

		}

	}

	
}
