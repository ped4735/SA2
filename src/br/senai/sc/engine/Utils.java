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
		// O getClassLoader inicia a busca do recurso a partir da raiz do
		// classpath, enquanto o getResourse busca, a partir do caminho
		// informado por par�metro, um objeto contendo a URL do recurso (imagem)
		// solicitado.
		URL imgUrl = getClass().getClassLoader().getResource(fileName);
		// Caso a URL for null, n�o encontrou nada no caminho informado.
		if (imgUrl == null) {
			System.out.println("Erro ao carregar a imagem!");
		} else {
			try {
				// Utiliza o m�todo static read da classe ImageIO para obter o
				// objeto Image da imagem a partir da URL informada,
				// retornando essa informa��o.
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


}
