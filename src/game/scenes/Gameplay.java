package game.scenes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import game.Hero;
import game.TrueHero;
import game.tiles.Spike;
import game.tiles.Stone;

public class Gameplay extends Scene {

	private int TILE_SIZE = 32;
	private int COLS = 40;
	private int ROWS = 22;
	private int[][] tileMapMatrix;

	public Gameplay() {

		/*
		 * getObjsInScene().add(new Hero("coinssonic.png", 100, 0, 4, 4));
		 * getObjsInScene().add(new Hero("coinssonic.png", 200, 0, 4, 4));
		 * getObjsInScene().add(new Hero("coinssonic.png", 300, 0, 4, 4));
		 * getObjsInScene().add(new Hero("coinssonic.png", 400, 0, 4, 4));
		 * 
		 * getObjsInScene().add(new Stone(100, 200)); getObjsInScene().add(new
		 * Stone(300, 200)); getObjsInScene().add(new Stone(500, 200));
		 * 
		 * getObjsInScene().add(new Spike(500, 400));
		 */

		tileMapMatrix = new int[ROWS][COLS];
		lerArquivo();

	}

	private void lerArquivo() {
		try { // try ler arquivo tmx
			File file = new File("tileset.tmx");
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
						getObjsInScene().add(new Stone(c * 32, r * 32));
						break;
					case 4:
						getObjsInScene().add(new Spike(c * 32, r * 32));
						break;
					case 3:
						getObjsInScene().add(new TrueHero("rocket.png", c * 32, r * 32, 2, 1));
						break;

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

}
