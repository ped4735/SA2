package game;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ScoreIO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1173559520101450186L;

	List<Score> scores = new LinkedList<>();

	private final int MAX_POS_RANK = 5;
	
	public void addScore(Score score) {

		if (scores.size() == 0) {
			scores.add(score);
		} else {

			for (int i = 0; i < scores.size(); i++) {
				if (score.getScore() > scores.get(i).getScore()) {
					scores.add(i, score);
					break;
				}else{
					scores.add(i+1, score);
					break;
				}
			}
		}
		
		if(scores.size() > MAX_POS_RANK){
			scores.remove(scores.size()-1);			
		}
		
	}

	public List<Score> getScores() {

		return scores;

	}

	public void printList() {

		Iterator<Score> itr = scores.iterator();
		while (itr.hasNext()) {
			Score tempScore = itr.next();
			System.out.println("{" + tempScore.getNome() + " "
					+ tempScore.getScore() + "}");
		}

	}

}
