package game.scenes;

import br.senai.sc.engine.Utils;
import game.AnimatedObject;
import game.Button;
import game.GameStates;

public class MainMenu extends Scene {

	private int sizeX;
	private int sizeY;
	private int offsetX;
	private int offsetY;
	private int posX1;
	private int posX2;
	private int posY1;
	private int posY2;

	public MainMenu() {
		this.getPositions();

		// background
		getObjsInScene().add(new AnimatedObject("background/Space.jpg", 0, 0, 1, 1));

		// logo
		AnimatedObject logo;
		logo = new AnimatedObject("opening/Logo_02.png", 100, 50, 1, 1);
		logo.setPosX(Utils.getInstance().getWidth()/2 - logo.getWidth()/2);
		getObjsInScene().add(logo);

		
		
		// buttons
		getObjsInScene().add(new Button("startButton.png", posX1, posY1, 1, 2, GameStates.Loading));
		getObjsInScene().add(new Button("creditsButton.png", posX1, posY2, 1, 2, GameStates.Ranking));
		getObjsInScene().add(new Button("helpButton.png", posX2, posY1, 1, 2, GameStates.Help));
		//ranking button
		
		getObjsInScene().add(new Button("exitButton.png", posX2, posY2, 1, 2, GameStates.Exit));

	}
	
	

	private void getPositions() {
		Button tempToGetSizes = new Button("helpButton.png", -100, -1000, 1, 2, GameStates.Help);
		sizeX = tempToGetSizes.getWidth();
		sizeY = tempToGetSizes.getHeight();

		offsetX = 0;
		offsetY = 0;

		posX1 = Utils.getInstance().getWidth() / 3 - sizeX / 2 - offsetX;
		posX2 = Utils.getInstance().getWidth() * 2 / 3 - sizeX / 2 + offsetX;

		posY1 = Utils.getInstance().getHeight() / 2 - sizeY / 2 - offsetY;
		posY2 = Utils.getInstance().getHeight() * 3 / 4 - sizeY / 2 + offsetY;

	}
}
