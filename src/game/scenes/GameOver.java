package game.scenes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import br.senai.sc.engine.Utils;
import game.AnimatedObject;
import game.GameManager;
import game.GameStates;
import game.JetpackGame;

public class GameOver extends Scene {

	int count = 0;
	
	public GameOver() {

		getObjsInScene().add(new AnimatedObject("backgroundAlpha.png", 0, 0, 1, 1));

	}
	
	@Override
	public void update() {
				
		super.update();
		count++;
		if(count == 10){
			GameManager.getInstance().resetGame();
			count = 0;
		}
		
	}

}
