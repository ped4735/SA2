package br.senai.sc.engine;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import javazoom.jl.player.Player;

public class Mp3 extends Thread {

	private File mp3;
	private Player player;
	private String audioName;

	public void carregar(String caminho) {
		this.audioName = caminho;
		//URL path = this.getClass().getResource(caminho);
		URL path = this.getClass().getClassLoader().getResource(caminho);
		mp3 = new File(path.getFile());
	}

	public String getAudioName() {
		return audioName;
	}

	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}

	public File getMp3() {
		return mp3;
	}

	public void setMp3(File mp3) {
		this.mp3 = mp3;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void run() {
		try {
			FileInputStream fis = new FileInputStream(mp3);
			BufferedInputStream bis = new BufferedInputStream(fis);

			this.player = new Player(bis);

			this.player.play();
			if (this.player.isComplete()) {
				this.player.close();
				this.interrupt();
			}
		} catch (Exception e) {
			System.out.println("Problemas ao tocar a m�sica");
			e.printStackTrace();
		}
	}

	public void iniciar() {
		this.start();
	}

	public void finalizar() {
		this.player.close();
		this.interrupt();
	}

	public boolean isCompleted() {
		if (this.player != null && (this.player.isComplete() || !isAlive())) {
			return true;
		} else {
			return false;
		}
	}

}
