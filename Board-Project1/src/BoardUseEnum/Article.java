package BoardUseEnum;

public class Article {
	private int aid;
	private String title;
	private String body;
	private int uid;
	private String nickname;
	private String regDate;
	private int like;
	private int hit;
	
	public String getSearchIndex(int index) {
		String str = null;
		
		if(index == 2) {	// 제목
			str = getTitle();
		} else if(index == 3) {	// 내용
			str = getBody();
		} else if(index == 4) {	// 작성자
			str = getNickname();
		} else {	// 제목 + 내용
			str = getTitle() + getBody();
		}
		return str;
	}
	
	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public String getNickname() {
		return this.nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setDate(String regDate) {
		this.regDate = regDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}

}
