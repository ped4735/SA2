package game.interfaces;

import game.GameObject;

public interface Interactable {
	
	void actionEnter(GameObject gameobj);
	void actionStay(GameObject gameobj);
	void actionExit(GameObject gameobj);

}
