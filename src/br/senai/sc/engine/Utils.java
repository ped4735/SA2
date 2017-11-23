package br.senai.sc.engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Utils {

	private static Utils instance;

	private int width;
	private int height;
	private String nomeJogo;
	private Graphics2D graphics2d;
	
	private float gravidade = 10;
	

	public static Utils getInstance() {
		if (instance == null) {
			instance = new Utils();
		}
		return instance;
	}
	
	/**
	 * M�todo respons�vel por carregar uma imagem dispon�vel em packages.
	 * 
	 * @param fileName
	 *            Caminho da imagem que ser� carregada.
	 * @return Retorna a imagem carregada.
	 */
	public Image loadImage(String fileName) {
		URL imgUrl = getClass().getClassLoader().getResource(fileName);
		if (imgUrl == null) {
			System.out.println("Erro ao carregar a imagem!");
		} else {
			try {

				String[] split = fileName.split("\\.");
				if (split[split.length-1].equalsIgnoreCase("gif")) {
					return new ImageIcon(imgUrl).getImage();
				} else {
					return ImageIO.read(imgUrl);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getNomeJogo() {
		return nomeJogo;
	}

	public void setNomeJogo(String nomeJogo) {
		this.nomeJogo = nomeJogo;
	}

	public Graphics2D getGraphics2d() {
		return graphics2d;
	}
	

	public float getGravidade() {
		return gravidade;
	}

	public void setGravidade(float gravidade) {
		this.gravidade = gravidade;
	}

	public void setGraphics2d(Graphics2D graphics2d) {
		this.graphics2d = graphics2d;
	}
	
	
	
	
	
	public void desenharRetangulo(int x, int y, int width, int height, Color color) {
		getGraphics2d().setColor(color);
		getGraphics2d().fillRect(x, y, width, height);
	}
	
	public void desenharCirculo(int x, int y, int width, int height, Color color) {
		getGraphics2d().setColor(color);
		getGraphics2d().fillOval(x, y, width, height);
	}
	
	public boolean[][] makeBooleanMatrix(int sizeX, int sizeY, int[] numOfTrues) {
		boolean[][] endMatrix = new boolean[sizeX][sizeY];
		for (int i = 0; i < endMatrix.length; i++) {
			for (int j = 0; j < numOfTrues[i]; j++) {
				endMatrix[i][j] = true;
			}
		}
		return endMatrix;
	}

}
