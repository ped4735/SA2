package game.scenes;

import java.awt.Color;
import java.awt.Graphics2D;

import br.senai.sc.engine.Utils;
import game.GameObject;

public class Ui{

	public int heightBar = 80;
	

	
	public void draw(Graphics2D g){
		g.setColor(Color.white);
		g.drawRect(0, Utils.getInstance().getHeight() - heightBar, Utils.getInstance().getWidth(), heightBar);
		g.drawString("nada para ver aqui, jovem", 100, Utils.getInstance().getHeight() - heightBar + 20);
	}
	
}
