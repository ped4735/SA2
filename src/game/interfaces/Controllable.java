package game.interfaces;

import java.awt.event.KeyEvent;

public interface Controllable {
	
	void pressAction(KeyEvent key);
	void releaseAction(KeyEvent key);

}
