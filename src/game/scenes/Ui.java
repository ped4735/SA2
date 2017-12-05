package game.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.text.DecimalFormat;

import br.senai.sc.engine.CustomFont;
import br.senai.sc.engine.Utils;
import game.GameObject;
import game.Hero;
import game.JetpackGame;

public class Ui {

	// Ui sizeY (sizeX is the screen)
	public int heightBar = 80;
	game.TrueHero hero;

	private int centerDialVelX;// = Utils.getInstance().getWidth() / 2 - 50;
	private int centerDialVelY;// = Utils.getInstance().getHeight() - heightBar
								// + 40;

	private int centerDialAceX;// = Utils.getInstance().getWidth() / 2 + 50;
	private int centerDialAceY;// = Utils.getInstance().getHeight() - heightBar
								// + 40;

	private int lineSize;// = 38;

	private int heatingBarPosX;// = 30;
	private int heatingBarPosY;// = (Utils.getInstance().getHeight() -
								// heightBar) + 10;

	private int textInfoPosX;// = Utils.getInstance().getWidth() - 80;
	private int textInfoPosY;// = (Utils.getInstance().getHeight() - heightBar)
								// + 15;

	Image dial;
	Image image;

	public Ui(game.TrueHero hero) {
		this.hero = hero;
		dial = Utils.getInstance().loadImage("images/fundoDial.png");
		image = Utils.getInstance().loadImage("images/heatingScale.png");

		centerDialVelX = Utils.getInstance().getWidth() / 2 - 50;
		centerDialVelY = Utils.getInstance().getHeight() - heightBar + 40;

		centerDialAceX = Utils.getInstance().getWidth() / 2 + 50;
		centerDialAceY = Utils.getInstance().getHeight() - heightBar + 40;

		lineSize = 38;

		heatingBarPosX = 15;
		heatingBarPosY = (Utils.getInstance().getHeight() - heightBar) + 10;

		textInfoPosX = Utils.getInstance().getWidth() - 80;
		textInfoPosY = (Utils.getInstance().getHeight() - heightBar) + 15;

	}

	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		g.drawRect(0, Utils.getInstance().getHeight() - heightBar, Utils.getInstance().getWidth(), heightBar);

		// drawDial
		g.drawImage(dial, centerDialVelX - lineSize, centerDialVelY - lineSize,
				centerDialVelX - lineSize + dial.getWidth(null), centerDialVelY - lineSize + dial.getHeight(null), 0, 0,
				dial.getWidth(null), dial.getHeight(null), null);

		g.drawImage(dial, centerDialAceX - lineSize, centerDialAceY - lineSize,
				centerDialAceX - lineSize + dial.getWidth(null), centerDialAceY - lineSize + dial.getHeight(null), 0, 0,
				dial.getWidth(null), dial.getHeight(null), null);

		// desenha as coisas da interface
		drawSpeedDial(g);
		drawAceDial(g);
		drawHeatingBar(g);
		drawTextInfo(g);

		// g.setColor(Color.pink);
		// g.fillRect(0, Utils.getInstance().getHeight() - 80, 20 , 80);

	}

	private void drawSpeedDial(Graphics2D g) {
		float module = (float) Math.hypot(hero.getVelX(), hero.getVelY());

		g.setColor(Color.red);
		g.drawLine(centerDialVelX, centerDialVelY, centerDialVelX + (int) (lineSize * hero.getVelX() / module),
				centerDialVelY + (int) (lineSize * hero.getVelY() / module));

	}

	private void drawAceDial(Graphics2D g) {
		float module = (float) Math.hypot(hero.getAceX(), hero.getAceY());

		g.setColor(Color.green);
		g.drawLine(centerDialAceX, centerDialAceY, centerDialAceX + (int) (lineSize * hero.getAceX() / module),
				centerDialAceY + (int) (lineSize * hero.getAceY() / module));

	}

	float oldCurrentHeating;

	private void drawHeatingBar(Graphics2D g) {
		float currentHeating = hero.getHeating();
		float maxHeating = hero.getMaxHeating();

		int imageSizeX = (int) (image.getWidth(null) * 1.8f);
		int imageSizeY = (int) (image.getHeight(null) / 5f);

		// desenha a barra
		g.drawImage(image, heatingBarPosX, heatingBarPosY, heatingBarPosX + imageSizeX, heatingBarPosY + imageSizeY, 0,
				0, image.getWidth(null), image.getHeight(null), null);

		// desenha o bloqueio
		g.setColor(Color.BLACK);
		// g.fillRect(heatingBarPosX + (int) (imageSizeX * (currentHeating /
		// maxHeating)),
		// heatingBarPosY,
		// (int) (imageSizeX * (1f - (currentHeating / maxHeating))),
		// imageSizeY);
		//
		g.fillRect(heatingBarPosX + (int) (imageSizeX * (currentHeating / maxHeating)), heatingBarPosY,
				(int) (imageSizeX - (int) (imageSizeX * (currentHeating / maxHeating))), imageSizeY);

		// desenha um contorno temporario
		g.setColor(Color.WHITE);
		g.drawRect(heatingBarPosX, heatingBarPosY, imageSizeX, imageSizeY);

		// alerta
		if (currentHeating > 0.85 * maxHeating) {
			g.setColor(Color.RED);
			g.fillOval(heatingBarPosX + imageSizeX + 10, heatingBarPosY + 10, 10, 10);
			g.setColor(Color.WHITE);
			g.drawString("Overheating!", heatingBarPosX + imageSizeX + 20, heatingBarPosY + 15);

		}

		if (oldCurrentHeating > currentHeating) {
			g.setColor(Color.BLUE);
			g.fillOval(heatingBarPosX + imageSizeX + 10, heatingBarPosY + 30, 10, 10);
			g.setColor(Color.WHITE);
			g.drawString("Cooling!", heatingBarPosX + imageSizeX + 20, heatingBarPosY + 35);
		}

		// text info
		g.setColor(Color.white);

		DecimalFormat df = new DecimalFormat("#.##");
		g.drawString("Heating level:" + df.format((double) hero.getHeating()) + "%", heatingBarPosX + 20,
				heatingBarPosY + imageSizeY + 15);

		oldCurrentHeating = currentHeating;

	}

	private void drawTextInfo(Graphics2D g) {
		int lineSize = 10;
		g.setColor(Color.WHITE);
		g.drawString("Lifes:" + hero.getLife(), textInfoPosX, textInfoPosY + 0 * lineSize);
		g.drawString("Score:" + game.GameManager.getInstance().getScore(), textInfoPosX, textInfoPosY + 1 * lineSize);

	}


}
