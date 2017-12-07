package game;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class PlaySound {
	private Clip clip;

	public PlaySound(String soundName) {
		soundName = "src/sounds/" + soundName;
		
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao carregar o audio: " + soundName);
		}
		//System.out.println(clip.getMicrosecondLength());
	}

	public void startLoop() {
		// AudioInputStream audioInputStream =
		// AudioSystem.getAudioInputStream(new
		// File(soundName).getAbsoluteFile());
		// Clip clip = AudioSystem.getClip();
		// clip.open(audioInputStream);
		if (!clip.isRunning()) {
			clip.stop();
			clip.start();
			//System.out.println("pos:" +clip.getMicrosecondPosition());
			if (clip.getMicrosecondLength() <= clip.getMicrosecondPosition()) {
				clip.setMicrosecondPosition(0);

				
			}
		}
	}
	public void start() {
		// AudioInputStream audioInputStream =
		// AudioSystem.getAudioInputStream(new
		// File(soundName).getAbsoluteFile());
		// Clip clip = AudioSystem.getClip();
		// clip.open(audioInputStream);
		if (!clip.isRunning()) {
			clip.stop();
			clip.start();
		}
	}

	public void stop() {
		if (clip.isRunning()) {
			clip.stop();
			clip.setMicrosecondPosition(0);
		}
	}
}
