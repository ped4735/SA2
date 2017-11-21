package game.interfaces;

import game.GameObject;

public interface Collidable {

	
	void collisionEnter(GameObject objInCol);
	void collisionStay(GameObject objInCol);
	void collisionExit(GameObject objExtCol);
}
