package Model;

public class RankingVO {
	
	String nickname;
	int score;

	public RankingVO(String nickname, int score) {
		super();
		
		this.nickname = nickname;
		this.score = score;
	}



	public String getNickname() {
		return nickname;
	}

	public int getScore() {
		return score;
	}

}
