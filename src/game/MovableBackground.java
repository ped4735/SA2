package game;

import java.awt.Graphics2D;
import java.util.Random;

import br.senai.sc.engine.Utils;
import game.interfaces.Updatable;

public class MovableBackground extends GameObject implements Updatable {

	private float offsetXtop;
	private float offsetYtop;
	private float offsetXbot;
	private float offsetYbot;

	private float velX;
	private float velY;

	private float scaleVel;

	public MovableBackground(String spriteFileName, int posX, int posY, int colFrames, int lineFrames) {
		super(spriteFileName, posX, posY, colFrames, lineFrames);

	}

	public MovableBackground(String spriteFileName) {
		this(spriteFileName, 0, 0, 1, 1);

		Random r = new Random();

		velX = (float) Math.sqrt(r.nextDouble() * 25);
		velY = (float) Math.sqrt(r.nextDouble() * 16);
		System.out.println("VelX:" + velX + ": VelY:" + velY);

		offsetXtop = 0;
		offsetYtop = 0;
		offsetXbot = 0;
		offsetYbot = 0;
	}



	@Override
	public void update() {
		offsetXtop -= velX;
		offsetYtop -= velY;

		offsetXbot += velX;
		offsetYbot += velY;
	}

	@Override
	public void draw(Graphics2D g) {
		// super.draw(g);

		/*
		 * g.drawImage(getSprite(), 0+(int)offsetXtop, 0+(int)offsetYtop,
		 * Utils.getInstance().getWidth()+(int)offsetXbot,
		 * Utils.getInstance().getHeight()+(int)offsetYbot, 0, 0, 0 +
		 * (int)(Utils.getInstance().getWidth()), 0 +
		 * (int)(Utils.getInstance().getHeight()), null);
		 */
		g.drawImage(getSprite(), 0 + (int) offsetXtop, 0 + (int) offsetYtop,
				Utils.getInstance().getWidth() + (int) offsetXbot, Utils.getInstance().getHeight() + (int) offsetYbot,
				0, 0, getWidth(), getHeight(), null);

	}
}
