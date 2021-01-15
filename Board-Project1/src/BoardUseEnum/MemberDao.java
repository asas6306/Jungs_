package BoardUseEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MemberDao {
	static Member m = new Member();
	Scanner sc = new Scanner(System.in);
	DBUtil db = new DBUtil();

	public void signup(String inputID, String inputPW, String inputNickname) {
		String sql = "insert into member set ID = ?, PW = ?, nickname = ?";
		db.updateSQL(sql, inputID, inputPW, inputNickname);
	}

	public boolean memberCheck(String inputID, String inputPW) {
		String sql = "select * from member where ID = ? and PW = ?";
		return db.existCheck(sql, inputID, inputPW);
	}
	
	public Member getMember(String inputID, String inputPW) {
		String sql = "select * from member where ID = ? and PW = ?";
		Member m = db.getRow(sql, new MemberMapper(), inputID, inputPW);
		return m;
	}
	
	public Member getMember(int userid) {
		String sql = "select * from member where userid = ?";
		Member m = db.getRow(sql, new MemberMapper(), userid);
		return m;
	}
	
	public int signupCheck(String inputID, String inputNickname) {
		String sql1 = "select * from member where ID = ?";
		String sql2 = "select * from member where nickname = ?";
		
		int checkNum = 0;
		
		if(db.existCheck(sql1, inputID)) {
			checkNum += 1;
		}
		if(db.existCheck(sql2, inputNickname)) {
			checkNum += 2;
		}
		
		return checkNum;
	}

	public void updateMember(String ID, String PW, String nickname) {
		String sql = "update member set PW = ?, nickname = ? where ID = ?";
		
		db.updateSQL(sql, PW, nickname, ID);
	}
	
	public void updateMember(Member m) {
		String sql = "update member set ID = ?, nickname = ?, ban = ?, mng = ? where ID = ?";
		
		db.updateSQL(sql, m.getID(), m.getNickname(), m.isBan(), m.isMng(), m.getID());
	}
	
	public ArrayList<Member> getMembers() {
		String sql = "select * from member";
		
		return db.getRows(sql, new MemberMapper());
	}
}
