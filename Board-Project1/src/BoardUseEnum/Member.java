package BoardUseEnum;

public class Member {
	private int userid = 0;
	private String ID = null;
	private String PW = null;
	private String nickname = "익명";
	private boolean state = false;
	private boolean ban = false;
	private boolean mng = false;
	
	public boolean isBan() {
		return ban;
	}
	public void setBan(boolean ban) {
		this.ban = ban;
	}
	public boolean isMng() {
		return mng;
	}
	public void setMng(boolean mng) {
		this.mng = mng;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String pW) {
		PW = pW;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
}
