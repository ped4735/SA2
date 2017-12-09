package br.senai.sc.engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
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
	private float globalScale = 1f;
	private boolean debug = false;

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
				if (split[split.length - 1].equalsIgnoreCase("gif")) {
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

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public void setGraphics2d(Graphics2D graphics2d) {
		this.graphics2d = graphics2d;
	}

	public float getGlobalScale() {
		return globalScale;
	}

	public void setGlobalScale(float globalScale) {
		this.globalScale = globalScale;
	}

	public void desenharRetangulo(int x, int y, int width, int height, Color color) {
		getGraphics2d().setColor(color);
		getGraphics2d().fillRect(x, y, width, height);
	}

	public void desenharCirculo(int x, int y, int width, Color color) {
		getGraphics2d().setColor(color);
		getGraphics2d().fillOval(x, y, width, width);
	}

	public boolean[][] makeBooleanMatrix(int sizeY, int sizeX, int[] numOfTrues) {
		boolean[][] endMatrix = new boolean[sizeX][sizeY];
		for (int i = 0; i < endMatrix.length; i++) {
			for (int j = 0; j < numOfTrues[i]; j++) {
				endMatrix[i][j] = true;
			}
		}
		
		/*for (int i = 0; i < endMatrix.length; i++) {
			for (int j = 0; j < endMatrix[i].length; j++) {
				System.out.print("[" + endMatrix[i][j] + "]");
			}
			System.out.println();
		}*/
		
		return endMatrix;
	}

	public float[] rotate(int pontoX, int pontoY, float theta, int posCentralX, int posCentralY,
			float thetaInitialShift) {
		// roda o ponto (pontoX, pontoY), theta radianos em volta do ponto
		// (posCentralX, posCentralY)
		float xLinha = (pontoX - posCentralX) * (float) Math.cos(theta + thetaInitialShift)
				- (pontoY - posCentralY) * (float) Math.sin(theta + thetaInitialShift);
		float yLinha = (pontoX - posCentralX) * (float) Math.sin(theta + thetaInitialShift)
				+ (pontoY - posCentralY) * (float) Math.cos(theta + thetaInitialShift);

		xLinha += (float) posCentralX;
		yLinha += (float) posCentralY;

		return new float[] { xLinha, yLinha };
	}

	public float[] rotate(double pontoX, double pontoY, float theta, double posCentralX, double posCentralY,
			float thetaInitialShift) {
		return rotate((int) pontoX, (int) pontoY, theta, (int) posCentralX, (int) posCentralY, thetaInitialShift);
	}

	public Rectangle reshapeRectangleByAngle(Rectangle r, float theta, float thetaInitialShift) {

		// rotaciona os pontos
		float[] a = rotate(r.getMaxX(), r.getMaxY(), theta, r.getCenterX(), r.getCenterY(), thetaInitialShift);
		float[] b = rotate(r.getMaxX(), r.getMinY(), theta, r.getCenterX(), r.getCenterY(), thetaInitialShift);
		float[] c = rotate(r.getMinX(), r.getMaxY(), theta, r.getCenterX(), r.getCenterY(), thetaInitialShift);
		float[] d = rotate(r.getMinX(), r.getMinY(), theta, r.getCenterX(), r.getCenterY(), thetaInitialShift);

		// ordena os pontos para construir o retangulo. PosX (extremidade
		// esquerda) tem que ser sempre menor que o posX+width (extremidade
		// direita), por exemplo
		float xMin = Math.min(Math.min(a[0], b[0]), Math.min(c[0], d[0]));
		float yMin = Math.min(Math.min(a[1], b[1]), Math.min(c[1], d[1]));

		// desenha um em volta com as cordenadas
		r.setFrameFromCenter(new Point((int) r.getCenterX(), (int) r.getCenterY()), new Point((int) xMin, (int) yMin));
		
		return r;
	}

	public Rectangle reshapeRectangleByAngle(Rectangle r, float theta) {
		return reshapeRectangleByAngle(r, theta, 0);

	}
	
	public Rectangle rotateRectangleByAngle(Rectangle r, float theta, int centerX, int centerY){
		
		if (theta == 0){
			return r;
		}else if (theta == (float)Math.PI/2){
			
			return new Rectangle(
					(int)(r.getX()),
					(int)(r.getY()-r.getHeight()),
					(int)r.getHeight(),
					(int)r.getWidth()
					);
		
		}else if (theta == (float)Math.PI){
			return new Rectangle(
					(int)r.getX(),
					(int)(r.getY() - r.getHeight()),
					(int)r.getWidth(),
					(int)r.getHeight()
					);
			
		}else if (theta == (float)Math.PI*3f/2f){
			return new Rectangle(
					(int)(r.getX() + r.getHeight()),
					(int)(r.getY()- r.getHeight()),
					(int)r.getHeight(),
					(int)r.getWidth()
					);
		}
		
		return null;
	}
	
	public Rectangle rotateRectangleByAngle(Rectangle r, float theta){
		return rotateRectangleByAngle(r, theta,0,0);
	}

	public game.CollisionFace collisionEnterIn(Rectangle obj1, Rectangle obj2) {

		// System.out.println("Entrou!");
		// obj1 top_bot obj2

		if (getRectangleFace(obj1, game.CollisionFace.bot).intersects(getRectangleFace(obj2, game.CollisionFace.top))) {
			return game.CollisionFace.bot_top;
		}
		if (getRectangleFace(obj1, game.CollisionFace.top).intersects(getRectangleFace(obj2, game.CollisionFace.bot))) {
			return game.CollisionFace.top_bot;
		}
		if (getRectangleFace(obj1, game.CollisionFace.left)
				.intersects(getRectangleFace(obj2, game.CollisionFace.right))) {
			return game.CollisionFace.left_right;
		}
		if (getRectangleFace(obj1, game.CollisionFace.right)
				.intersects(getRectangleFace(obj2, game.CollisionFace.left))) {
			return game.CollisionFace.right_left;
		}

		return game.CollisionFace.none;

	}

	public Rectangle getRectangleFace(Rectangle rect, game.CollisionFace colFace) {
		int offset = 4;
		int scale = 4;

		switch (colFace) {
		case top:
			return new Rectangle(rect.x + offset, rect.y, (int) rect.getWidth() - offset * 2,
					(int) rect.getHeight() / scale);

		case bot:
			return new Rectangle(rect.x + offset, rect.y + (int) rect.getHeight() - ((int) rect.getHeight() / scale),
					(int) rect.getWidth() - offset * 2, (int) rect.getHeight() / scale);
		case right:
			return new Rectangle(rect.x + (int) rect.getWidth() - (int) rect.getWidth() / scale, rect.y + offset,
					(int) rect.getWidth() / scale, (int) rect.getHeight() - offset * 2);
		case left:
			return new Rectangle(rect.x, rect.y + offset, (int) rect.getWidth() / scale,
					(int) rect.getHeight() - offset * 2);

		default:
			break;
		}

		return null;
	}

}
