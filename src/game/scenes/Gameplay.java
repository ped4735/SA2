package game.scenes;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import game.Hero;
import game.TrueHero;
import game.tiles.BlackHole;
import game.tiles.Spike;
import game.tiles.Stone;

public class Gameplay extends Scene {

	private int TILE_SIZE = 32;
	private int COLS = 40;
	private int ROWS = 22;
	private int[][] tileMapMatrix;
	private String tmxFile;

	Ui ui;
	
	public Gameplay(String tmxFile) {

		getObjsInScene().add(new BlackHole(300, 100));

		this.tmxFile = tmxFile;
		tileMapMatrix = new int[ROWS][COLS];
		lerArquivo();

		ui = new Ui();
		
		

		
	}

	private void lerArquivo() {
		try { // try ler arquivo tmx
			File file = new File("src/levels/" + tmxFile + ".tmx");
			System.out.println("src/levels/" + tmxFile + ".tmx 100%" );
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			int rowCount = 0;
			while (linha != null && !linha.isEmpty()) {
				String[] split = linha.split(",");
				// System.out.println(split.length);
				for (int i = 0; i < split.length; i++) {
					tileMapMatrix[rowCount][i] = Integer.parseInt(split[i]);
				}
				rowCount++;
				linha = br.readLine();
			}
			br.close();
			fr.close();

			// instanciar objetos pelo tile
			for (int r = 0; r < tileMapMatrix.length; r++) {
				for (int c = 0; c < tileMapMatrix[r].length; c++) {
					// System.out.print(tileMapMatrix[r][c] + " ");
					int code = tileMapMatrix[r][c];

					switch (code) {
					case 1:
						getObjsInScene().add(new Stone(c * TILE_SIZE, r * TILE_SIZE));
						break;
					case 4:
						getObjsInScene().add(new Spike(c * TILE_SIZE, r * TILE_SIZE));
						break;
					case 3:
						//game.JetpackGame.hero = new TrueHero("rocket.png", c * TILE_SIZE, r * TILE_SIZE, 2, 1);
						game.JetpackGame.hero.start(c * TILE_SIZE, r * TILE_SIZE);
						getObjsInScene().add(game.JetpackGame.hero);

						break;
					//temporario. provavelmente vai dar problema depois
					case 5:
						getObjsInScene().add(new Spike(c * TILE_SIZE, r * TILE_SIZE,(float) Math.PI));

						

					default:
						break;
					}
				}
				// System.out.print("\n");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		ui.draw(g);
		
	}
}

