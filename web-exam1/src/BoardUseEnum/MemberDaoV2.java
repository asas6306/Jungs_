package BoardUseEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MemberDaoV2 {
	static Member m = new Member();
	Scanner sc = new Scanner(System.in);
	DBUtil db = new DBUtil();

	public void memberFlatform() {
		if (m.isState()) { // 회원
			if (m.isMng()) {
				System.out.println("1.info 2.signout 3.back 4.mngpage");
			} else {
				System.out.println("1.info 2.signout 3.back");
			}
			System.out.println("============================");
			System.out.print(" > ");
			int input = Integer.parseInt(sc.nextLine());
			if (input == -1) {
				System.out.println("Number Please");
			} else if (input == 1) {
				memberInfo(m);
				infoAction();
			} else if (input == 2) {
				signout();
			} else if (input == 3) {
				System.out.println("Go Back");
			} else if (input == 4 && m.isMng()) {
				Management mng = new Management();
				mng.mngBoard();
			} else {
				System.out.println("Can't");
			}
		} else { // 비회원
			System.out.println("1.signin 2.signup 3.list 4.find 5.back");
			System.out.println("============================");
			System.out.print(" > ");
			int input = Integer.parseInt(sc.nextLine());
			if (input == -1) {
				System.out.println("Number Please");
			} else if (input == 1) {
				signin();
			} else if (input == 2) {
				signup();
			} else if (input == 3) {
				listAll();
			} else if (input == 4) {
				findMember();
			} else if (input == 5) {
				System.out.println("Go Back");
			} else {
				System.out.println("Can't");
			}
		}
	}

	public void signup() {
		String sql = "insert into member set ID = ?, PW = ?, nickname = ?";
		String ID = null;
		String PW = null;
		String inputNickname = null;
		while (true) {
			System.out.print("ID > ");
			ID = sc.nextLine();
			if (db.existCheck("select * from member where ID = ?", ID)) {
				System.out.println("Already Exist");
			} else {
				break;
			}
		}
		System.out.print("PW > ");
		PW = sc.nextLine();
		while (true) {
			System.out.print("nickname > ");
			inputNickname = sc.nextLine();
			if (db.existCheck("select * from member where nickname = ?", inputNickname)) {
				System.out.println("Already Exist");
			} else {
				break;
			}
		}
		db.updateSQL(sql, ID, PW, inputNickname);
		System.out.println("Good");
	}
	public void signup(String inputID, String inputPW, String inputNickname) {
		String sql = "insert into member set ID = ?, PW = ?, nickname = ?";
		db.updateSQL(sql, inputID, inputPW, inputNickname);
	}

	public void signin() { // 수정
		System.out.print("ID > ");
		String ID = sc.nextLine();
		System.out.print("PW > ");
		String PW = sc.nextLine();

		String sql = "select * from member where ID = ? and PW = ?";
		
		if(db.existCheck(sql, ID, PW)) {
			m = db.getRow(sql, new MemberMapper(), ID, PW);
			m.setState(true);
			System.out.println("Good");
		}else {
			System.out.println("Not exist");
		}
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

	public void listAll() {
		String sql = "select * from member";
		ArrayList<Member> am = db.getRows(sql, new MemberMapper());

		for (int i = 0; i < am.size(); i++) {
			Member cashm = am.get(i);

			System.out.println("[" + cashm.getUserid() + "] : " + cashm.getID() + "(" + cashm.getNickname() + ")  ban:"
					+ cashm.isBan() + " / mng:" + cashm.isMng());
		}
	}

	public void findMember() {
		String sql = "select * from member where ID = ?";

		System.out.print("아이디 > ");
		String input = sc.nextLine();

		Member cashm = db.getRow(sql, new MemberMapper(), input);

		if (cashm == null) {
			System.out.println("Can't");
		} else {
			System.out.println("===== 회원정보 =====");
			System.out.println("userid : " + cashm.getUserid());
			System.out.println("ID : " + cashm.getID());
			System.out.println("PW : " + cashm.getPW());
			System.out.println("nickname : " + cashm.getNickname());
		}
	}

	public void memberInfo(Member m) {
		System.out.println("===== 회원정보 =====");
		System.out.println("userid : " + m.getUserid());
		System.out.println("ID : " + m.getID());
		System.out.println("PW : " + m.getPW());
		System.out.println("nickname : " + m.getNickname());
		System.out.println("ban : " + m.isBan());
		System.out.println("mng : " + m.isMng());
		System.out.println("===========================");
	}

	public void infoAction() {
		System.out.println("1.수정 2.탈퇴 3.뒤로가기");
		System.out.print(" > ");
		int input = Integer.parseInt(sc.nextLine());

		while (true) {
			if (input == 1) {
				infoUpdate(m.getUserid());
				memberInfo(m);
			} else if (input == 2) {
				memberFire(m.getUserid());
			} else if (input == 3) {
				System.out.println("Go Back");
				break;
			} else {
				System.out.println("Can't");
			}
		}
	}

	public void infoUpdate(int userid) {
		System.out.println("1.pw 2.nickname 3.back");
		System.out.print(" > ");
		int input = Integer.parseInt(sc.nextLine());

		String sql = null;

		if (input == 1) {
			sql = "update member set pw = ? where userid = ?";
		} else if (input == 2) {
			sql = "update member set nickname = ? where userid = ?";
		} else {
			System.out.println("Don't");
		}
		if (sql == null) {
		} else {
			System.out.print(" > ");
			String inputs = sc.nextLine();

			db.updateSQL(sql, inputs, userid);

			sql = "select * from member where userid = ?";
			Member cashm = db.getRow(sql, new MemberMapper(), userid);
			m = cashm;
			System.out.println(cashm.getUserid());
			System.out.println(m.getUserid());
			System.out.println("Good");
		}

	}

	public void memberFire(int userid) {
		System.out.print("Really?(y/n) : ");
		String yon = sc.nextLine();

		if (yon.equals("y")) {
			String msql = "delete from member where userid = ?";
			db.updateSQL(msql, userid);
			String asql = "delete from article where id = ?";
			db.updateSQL(msql, userid);
			String csql = "delete from comment where userid = ?";
			db.updateSQL(msql, userid);
			setDefaultMember();
			System.out.println("Good");
		} else {
			System.out.println("Don't");
		}
	}

	public void signout() {
		setDefaultMember();
		System.out.println("Good");
	}

	public void setDefaultMember() {
		Member dm = new Member();
		this.m = dm;
	}
}
