package game.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.text.DecimalFormat;

import br.senai.sc.engine.CustomFont;
import br.senai.sc.engine.Utils;
import game.GameObject;
import game.JetpackGame;
import game.PlaySound;

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
	Image heatingScale;
	Image baseUI;
	

	public Ui(game.TrueHero hero) {
		this.hero = hero;

		dial = Utils.getInstance().loadImage("images/bgDial.png");
		heatingScale = Utils.getInstance().loadImage("images/heatingScale.png");
		baseUI = Utils.getInstance().loadImage("images/baseUI.png");

		centerDialVelX = Utils.getInstance().getWidth() / 2 - 50;
		centerDialVelY = Utils.getInstance().getHeight() - heightBar + 40;

		centerDialAceX = Utils.getInstance().getWidth() / 2 + 50;
		centerDialAceY = Utils.getInstance().getHeight() - heightBar + 40;

		lineSize = 38;

		heatingBarPosX = 15 - 1;
		heatingBarPosY = (Utils.getInstance().getHeight() - heightBar) + 10 ;

		textInfoPosX = Utils.getInstance().getWidth() - 80;
		textInfoPosY = (Utils.getInstance().getHeight() - heightBar) + 15;


	}

	public void draw(Graphics2D g) {
		//black background to cover the boundries tiles
		g.setColor(Color.BLACK);
		g.fillRect(0, Utils.getInstance().getHeight() - heightBar,Utils.getInstance().getWidth(), heightBar);

		
		//heating bar
		g.setColor(Color.white);
		drawHeatingBar(g);

		
		
		//ui base interface
		g.drawImage(baseUI, 0, 0, baseUI.getWidth(null), baseUI.getHeight(null), 0, 0, baseUI.getWidth(null),
				baseUI.getHeight(null), null);

		
		// desenha as coisas da interface
		drawSpeedDial(g);
		drawAceDial(g);
		drawTextInfo(g);


	}

	private void drawSpeedDial(Graphics2D g) {
		float module = (float) Math.hypot(hero.getVelX(), hero.getVelY());

		//draw twice to add contrast
		g.setColor(Color.red);
		g.drawLine(centerDialVelX, centerDialVelY, centerDialVelX + (int) (lineSize * hero.getVelX() / module),
				centerDialVelY + (int) (lineSize * hero.getVelY() / module));
		g.drawLine(centerDialVelX, centerDialVelY, centerDialVelX +1+ (int) ((lineSize-1) * hero.getVelX() / module),
				centerDialVelY +1+ (int) ((lineSize-1) * hero.getVelY() / module));

	}

	private void drawAceDial(Graphics2D g) {
		float module = (float) Math.hypot(hero.getAceX(), hero.getAceY());

		g.setColor(Color.BLUE);
		//draw twice to add contrast
		g.drawLine(centerDialAceX, centerDialAceY, centerDialAceX + (int) (lineSize * hero.getAceX() / module),
				centerDialAceY + (int) ((lineSize-1) * hero.getAceY() / module));
		g.drawLine(centerDialAceX, centerDialAceY, centerDialAceX +1+ (int) ((lineSize-1) * hero.getAceX() / module),
				centerDialAceY +1+ (int) ((lineSize-1) * hero.getAceY() / module));

	}

	float oldCurrentHeating;

	private void drawHeatingBar(Graphics2D g) {
		float currentHeating = hero.getHeating();
		float maxHeating = hero.getMaxHeating();

		int imageSizeX = (int) (heatingScale.getWidth(null) * 1.8f) + 1;
		int imageSizeY = (int) (heatingScale.getHeight(null) / 5f);

		// desenha a barra
		g.drawImage(heatingScale, heatingBarPosX, heatingBarPosY, heatingBarPosX + imageSizeX,
				heatingBarPosY + imageSizeY, 0, 0, heatingScale.getWidth(null), heatingScale.getHeight(null), null);

		// desenha o bloqueio
		g.setColor(Color.BLACK);
		g.fillRect(heatingBarPosX + (int) (imageSizeX * (currentHeating / maxHeating)), heatingBarPosY,
				(int) (imageSizeX - (int) (imageSizeX * (currentHeating / maxHeating))), imageSizeY);

		// desenha um contorno temporario
		g.setColor(Color.WHITE);
		g.drawRect(heatingBarPosX, heatingBarPosY, imageSizeX, imageSizeY);

		// alertas
		//if (currentHeating > 0.85 * maxHeating) {
		if (hero.isWarningOverheating()) {

			g.setColor(Color.RED);
			g.fillRect(319, 679, 449 - 316, 701 - 676);

			g.setColor(Color.WHITE);
			g.drawString("Overheating!", 319 + 20, 679 + 15);
		} else {
			g.setColor(new Color(71, 0, 0));
			g.fillRect(319, 679, 449 - 316, 701 - 676);
		}

		
		// if (hero.isCooling() && oldCurrentHeating > currentHeating) {
		if (oldCurrentHeating > currentHeating) {
			g.setColor(Color.BLUE);
			g.fillOval(heatingBarPosX + 180, heatingBarPosY + imageSizeY + 20, 10, 10);
			g.fillRect(170, 675, 135, 30);

			g.setColor(Color.WHITE);
			g.drawString("Cooling!", heatingBarPosX + 180, heatingBarPosY + imageSizeY + 23);
		} else {
			g.setColor(new Color(0, 7, 71));
			g.fillRect(170, 675, 135, 30);
		}

		// text info
		g.setColor(Color.white);

		DecimalFormat df = new DecimalFormat("#.##");
		g.drawString("Heating level:" + df.format((double) hero.getHeating()) + "%", heatingBarPosX + 25,
				heatingBarPosY + imageSizeY + 23);

		oldCurrentHeating = currentHeating;

	}

	private void drawTextInfo(Graphics2D g) {
		int lineSize = 13;

		g.setFont(new Font("Arial", Font.PLAIN, 15));
		if (LevelManager.getInstance().getColletableInScene() > 0) {
			g.setColor(Color.WHITE);
		} else {
			g.setColor(Color.GREEN);
		}

		g.translate(-150, 0);
		g.drawString("Itens Remaining:", textInfoPosX, textInfoPosY + 1 * lineSize);
		g.translate(0, +15);
		g.drawString("" + LevelManager.getInstance().getColletableInScene(), textInfoPosX + 30,
				textInfoPosY + 1 * lineSize);
		g.translate(+150, -15);

		g.setFont(new Font("Arial", Font.PLAIN, 12));
		g.setColor(Color.WHITE);

		g.translate(0, -5);
		g.drawString("Lifes:" + hero.getLife(), textInfoPosX, textInfoPosY + 1 * lineSize);
		g.drawString("Score:" + game.GameManager.getInstance().getScore(), textInfoPosX, textInfoPosY + 2 * lineSize);
		g.drawString("Level:" + LevelManager.getInstance().getNumLevel(), textInfoPosX, textInfoPosY + 3 * lineSize);
		// g.translate(0,+5);

	}

}
