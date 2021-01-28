package BoardUseEnum;

public class Comment {
	private int cid;
	private int uid;
	private int aid;
	private String nickname;
	private String regDate;
	private String body;
	private int commentReply;
	
	
	public int getCommentReply() {
		return commentReply;
	}
	public void setCommentReply(int commentReply) {
		this.commentReply = commentReply;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setDate(String regDate) {
		this.regDate = regDate;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
