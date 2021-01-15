package BoardUseEnum;

public class Article {
	private int articleid;
	private String title;
	private String body;
	private int userid;
	private String nickname;
	private String date;
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
	public int getArticleid() {
		return articleid;
	}
	public void setArticleid(int articleid) {
		this.articleid = articleid;
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
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}

}
