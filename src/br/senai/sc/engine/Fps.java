package br.senai.sc.engine;

public class Fps {

	private int targetFps = 30;
	private long optimalTime = 1000 / targetFps;

	private long lastFrameTime;
	
	private int deltaTime;
	public static float DELTATIME;
	
	private int fps;
	private long lastFPSMs;

	
	
	public Fps() {
		//getDelta();
		findDeltaTime();
		lastFPSMs = getTime();
	}
	
	public Fps(int fps) {
		targetFps = fps;
		optimalTime = 1000 / targetFps;
		//getDelta();
		findDeltaTime();
		lastFPSMs = getTime();
	}

	public int getDeltaTime() {
		return deltaTime;
	}
	
	public int getTargetFps() {
		return targetFps;
	}

	public void setTargetFps(int targetFps) {
		this.targetFps = targetFps;
	}

	public long getOptimalTime() {
		return optimalTime;
	}

	public void setOptimalTime(long optimalTime) {
		this.optimalTime = optimalTime;
	}

	public long getLastFrameTime() {
		return lastFrameTime;
	}

	public void setLastFrameTime(long lastFrameTime) {
		this.lastFrameTime = lastFrameTime;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	public long getLastFPSMs() {
		return lastFPSMs;
	}

	public void setLastFPSMs(long lastFPSMs) {
		this.lastFPSMs = lastFPSMs;
	}

	/*
	 * Tempo do sistema em milisegundos!
	 */
	private long getTime() {
		// 1000000 nanotimes = 1 milisegundo
		return System.nanoTime() / 1000000;
	}

	/*
	 * Diferença do tempo desde o último frame
	 */	
	public void findDeltaTime() {
		long time = getTime();
		deltaTime = (int) (time - lastFrameTime);
		lastFrameTime = time;
		DELTATIME = (float)deltaTime/1000;
	}
	

	public void updateFPS() {
		if (lastFPSMs > 1000) {
			//System.out.println("FPS: " + fps);
			fps = 0;
			lastFPSMs = 0;
		}
		
		findDeltaTime();
		lastFPSMs += deltaTime;
		//lastFPSMs += getDelta();
		fps++;
	}

	public void synchronize() {
		long ms = lastFrameTime - getTime() + optimalTime;
		try {
			if (ms > 0) {
				Thread.sleep(ms);
			}
		} catch (InterruptedException ex) {
			System.err.println(ex.getMessage());
			System.exit(-1);
		}
	}

}
