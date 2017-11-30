package game;

public class GameMannager {
	
	private static GameMannager instance;
	private int gameScore;
	
	
	
	public static  GameMannager getInstance() {
		
		if(instance == null){
			instance = new GameMannager();
		}
		return instance;
		
	}
	
	
	public void addScore(int p){
		gameScore+=p;
	}
	
	public int getScore(){
		return gameScore;
	}

	
	
}
