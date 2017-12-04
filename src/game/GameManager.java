package game;

public class GameManager {
	
	private static GameManager instance;
	private int gameScore;
	
	
	
	public static  GameManager getInstance() {
		
		if(instance == null){
			instance = new GameManager();
		}
		return instance;
		
	}
	
	
	public void addScore(int p){
		gameScore+=p;
	}
	
	
	public int getScore(){
		return gameScore;
	}
	
	public void setScore(int score){
		//para evitar o problema de reiniciar o jogo com o score da partida passada
		gameScore = score;
	}

	
	
}
