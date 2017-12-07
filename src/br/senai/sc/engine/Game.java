package br.senai.sc.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Canvas � uma classe que representa uma �rea retangular onde a aplica��o pode desenhar formas geom�tricas ou sprites, 
 * ou receber entradas do mouse e teclado.
 */
public abstract class Game extends Canvas {

	private static final long serialVersionUID = 6058040659371962305L;

	private JFrame container;
	private BufferStrategy strategy;
	// Controle do GameLoop
	private boolean gameRunning = true;
	//private boolean sairAoTermino = false;

	private Graphics2D graphics2D;
	private Fps fps;

	private Map<String, Mp3> musicas;
	private Map<String, CustomFont> customFonts;

	public Game() {
		Dimension fullscreen = Toolkit.getDefaultToolkit().getScreenSize();	
		Utils.getInstance().setHeight(fullscreen.height);
		Utils.getInstance().setWidth(fullscreen.width);

		// Cria um JFrame, que representa a janela da interface gr�fica.
		container = new JFrame(Utils.getInstance().getNomeJogo());

		container.setUndecorated(true);
		
		// Todo JFrame possui pelo menos um JPanel, com isso, buscamos o JPanel
		// criado para este JFrame.
		JPanel panel = (JPanel) container.getContentPane();

		// Aqui definimos o tamanho do JPanel, utilizando as constantes da
		// classe Utils.
		panel.setPreferredSize(
				new Dimension(Utils.getInstance().getWidth(), Utils.getInstance().getHeight()));
		// Setando o layout do panel como null, podemos adicionar os componentes
		// a partir das posi��es x e y.
		panel.setLayout(null);

		// Aqui definimos o tamanho do ret�ngulo do canvas. Sendo que ele vai de
		// 0x0 at� WIDTHxHEIGHT das constantes Utils.
		setBounds(0, 0, Utils.getInstance().getWidth(), Utils.getInstance().getHeight());
		// Adicionamos o canvas no panel obtido.
		panel.add(this);

		// Solicito que o canvas ignore todas as fun��es de repaint nativas do
		// JAVA, desta forma, podemos controlar quando e o
		// que ser� desenhado na tela.
		setIgnoreRepaint(true);

		// Compacta o JFrame para o seu tamanho necess�rio.
		container.pack();
		// Impede que o usu�rio altere o tamanho da Janela.
		container.setResizable(false);

		// Adiciona o evento para fechar a janela quando clicamos no X.
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// //Adiciona ao canvas a classe que controla os eventos do mouse.
		// addMouseListener(new Mouse());
		// //Adiciona ao canvas a classe que controla os eventos do teclado.
		// addKeyListener(new Keyboard());

		fps = new Fps();
		musicas = new HashMap<String, Mp3>();
		customFonts = new HashMap<String, CustomFont>();

		// Chama o m�todo init(), definido mais abaixo.
		init();

		// Centraliza o JFrame na tela
		//container.setLocationRelativeTo(null);
		// Torna o JFrame vis�vel.
		container.setVisible(true);

		// Solicita o foco para o canvas.
		requestFocus();

		createBufferStrategy(2);
		strategy = getBufferStrategy();

	}
	
	public Game(String nomeJogo, int width, int height) {
		Utils.getInstance().setNomeJogo(nomeJogo);
		Utils.getInstance().setHeight(height);
		Utils.getInstance().setWidth(width);

		// Cria um JFrame, que representa a janela da interface gr�fica.
		container = new JFrame(Utils.getInstance().getNomeJogo());

		// Todo JFrame possui pelo menos um JPanel, com isso, buscamos o JPanel
		// criado para este JFrame.
		JPanel panel = (JPanel) container.getContentPane();

		// Aqui definimos o tamanho do JPanel, utilizando as constantes da
		// classe Utils.
		panel.setPreferredSize(
				new Dimension(Utils.getInstance().getWidth() - 10, Utils.getInstance().getHeight() - 10));
		// Setando o layout do panel como null, podemos adicionar os componentes
		// a partir das posi��es x e y.
		panel.setLayout(null);

		// Aqui definimos o tamanho do ret�ngulo do canvas. Sendo que ele vai de
		// 0x0 at� WIDTHxHEIGHT das constantes Utils.
		setBounds(0, 0, Utils.getInstance().getWidth(), Utils.getInstance().getHeight());
		// Adicionamos o canvas no panel obtido.
		panel.add(this);

		// Solicito que o canvas ignore todas as fun��es de repaint nativas do
		// JAVA, desta forma, podemos controlar quando e o
		// que ser� desenhado na tela.
		setIgnoreRepaint(true);

		// Compacta o JFrame para o seu tamanho necess�rio.
		container.pack();
		// Impede que o usu�rio altere o tamanho da Janela.
		
		container.setResizable(false);
		//container.setResizable(true);
		

		// Adiciona o evento para fechar a janela quando clicamos no X.
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// //Adiciona ao canvas a classe que controla os eventos do mouse.
		// addMouseListener(new Mouse());
		// //Adiciona ao canvas a classe que controla os eventos do teclado.
		// addKeyListener(new Keyboard());

		fps = new Fps();
		musicas = new HashMap<String, Mp3>();
		customFonts = new HashMap<String, CustomFont>();

		// Chama o m�todo init(), definido mais abaixo.
		init();

		// Centraliza o JFrame na tela
		container.setLocationRelativeTo(null);
		// Torna o JFrame vis�vel.
		container.setVisible(true);

		// Solicita o foco para o canvas.
		requestFocus();

		createBufferStrategy(2);
		strategy = getBufferStrategy();

	}

	/**
	 * M�todo chamado pelo construtor. Aqui devem ser iniciadas todas as imagens
	 * necess�rias para o jogo.
	 */
	public abstract void init();

	/**
	 * M�todo respons�vel pelo GameLoop.
	 */
	public void startGame() {
		while (gameRunning) {
			
			
			// Obtemos o Graphics2D a partir do strategy. O Graphics2D � o
			// objeto que utilizo para desenhar na tela.
			graphics2D = (Graphics2D) strategy.getDrawGraphics();

			fps.updateFPS();

			//graphics2D.setColor(new Color(0, 0, 0, 5));
			graphics2D.setColor(new Color(0, 0, 0));
			//graphics2D.fillRect(0, 0, Utils.getInstance().getWidth(), Utils.getInstance().getHeight());

			gameLoop();

			// try{Thread.sleep(1);}catch (Exception e){}

			// Atualiza o Graphics2D com o que foi definido no GameLoop.
			graphics2D.dispose();
			// Apresenta o Graphics2D atualizado na janela do jogo.
			strategy.show();

			fps.synchronize();
		}

		//aposTermino();
		//if (sairAoTermino) {
		//	System.exit(0);
		//}
		System.exit(0);
		
	}


	
	public void desenharGif(Image image, int x, int y) {
		graphics2D.drawImage(image, x, y, container);
	}

	public void desenharImagem(Image image, int x, int y) {
		graphics2D.drawImage(image, x, y, null);
	}

	public void desenharString(String mensagem, int x, int y) {
		graphics2D.drawString(mensagem, x, y);
	}

	public void desenharString(String mensagem, int x, int y, Color color) {
		graphics2D.setColor(color);
		graphics2D.drawString(mensagem, x, y);
	}

	public void desenharString(String mensagem, int x, int y, Color color, int fontSize) {
		graphics2D.setColor(color);
		graphics2D.setFont(new Font("Arial", Font.BOLD, fontSize));
		graphics2D.drawString(mensagem, x, y);
		
	}

	public void desenharString(String mensagem, int x, int y, Color color, int fontSize, String fontName) {
		graphics2D.setColor(color);
		graphics2D.setFont(new Font(fontName, Font.BOLD, fontSize));
		graphics2D.drawString(mensagem, x, y);
	}

	public void desenharString(String mensagem, int x, int y, Color color, int fontSize, String fontName,
			int fontStyle) {
		graphics2D.setColor(color);
		graphics2D.setFont(new Font(fontName, fontStyle, fontSize));
		graphics2D.drawString(mensagem, x, y);
	}

	public Image carregarImagem(String path) {
		return Utils.getInstance().loadImage(path);
	}

	public void finalizarJogo() {
		gameRunning = false;
	}

	//public abstract void aposTermino();

	public abstract void gameLoop();

//	public void sairAoTerminar() {
//		sairAoTermino = true;
//	}

	
	public void alterarFramesPorSegundos(int fps) {
		this.fps = new Fps(fps);
	}

	public int getDeltaTime(){
		return fps.getDeltaTime();
	}
	
	public void adicionarAudio(String nome, String path) {
		Mp3 mp3 = new Mp3();
		mp3.carregar(path);
		if (musicas.get(nome) != null) {
			musicas.remove(nome);
		}
		musicas.put(nome, mp3);
	}

	public void tocarAudio(String nome) {
		String audioName = musicas.get(nome).getAudioName();
		musicas.remove(nome);
		Mp3 mp3 = new Mp3();
		mp3.carregar(audioName);
		musicas.put(nome, mp3);
		musicas.get(nome).iniciar();
	}

	public void pararAudio(String nome) {
		musicas.get(nome).finalizar();
	}

	public boolean audioIsCompleted(String nome) {
		if (musicas.get(nome) != null) {
			return musicas.get(nome).isCompleted();
		} else {
			return true;
		}
	}

	public void removerAudio(String nome) {
		musicas.remove(nome);
	}

	public void desenharRetangulo(int x, int y, int width, int height, Color color) {
		graphics2D.setColor(color);
		graphics2D.fillRect(x, y, width, height);
	}

	public void desenharCirculo(int x, int y, int width, int height, Color color) {
		graphics2D.setColor(color);
		graphics2D.fillOval(x, y, width, height);
	}

	public void addNewFont(String name, String path, float size, int style) {
		CustomFont cf = new CustomFont(path, size, style);
		customFonts.put(name, cf);
	}

	public void setFont(String name) {
		graphics2D.setFont(customFonts.get(name).getCustomFont());
	}

	public Graphics2D getGraphics2D() {
		return graphics2D;
	}
	
	public int getWidth() {
		return Utils.getInstance().getWidth();
	}
	
	public int getHeight() {
		return Utils.getInstance().getHeight();
	}

}
