package game;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ScoreIO implements Serializable{

	private static final long serialVersionUID = 8750314060189230438L;
	List<Score> scores = new LinkedList<>();
	
	public void addScore(Score score){
		
		for (int i = 0; i < scores.size(); i++) {
			if(scores.get(i).getScore() < score.getScore()){
				scores.add(i, score);
			}
		}
	}
	
	public List<Score> getScores(){
		
		return scores;
		
	}
	
	public void printList(){
		System.out.println(scores.toString());
	}
	
}
