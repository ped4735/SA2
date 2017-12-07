package game;

import java.io.Serializable;

public class Score implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3296658815298936148L;
	private String nome;
	private int score;
	
	public Score(String nome, int score){
		this.nome = nome;
		this.score = score;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	@Override
	public String toString() {
		return "Score [nome=" + nome + ", score=" + score + "]";
	}
	
	
	
	
}
