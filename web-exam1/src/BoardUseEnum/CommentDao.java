package BoardUseEnum;

import java.util.ArrayList;
import java.util.Scanner;

public class CommentDao {
	Scanner sc = new Scanner(System.in);
	DBUtil db = new DBUtil();

	public void commentFlatform(int index) {
		while (true) {
			String sql = "select * from comment join member on comment.userid = member.userid  where articleid = ? order by date";
			ArrayList<Comment> ac = db.getRows(sql, new CommentMapper(), index);
			
			for(int i = 0; i< ac.size(); i++) {
				Comment c = ac.get(i);
				
				System.out.println("[" + c.getCommnetid() + "] " + c.getUserid() + " " + c.getDate());
				System.out.println(" → " + c.getBody());
			}
			
			System.out.println("1.댓글달기 2.댓글수정 3.댓글삭제 4.뒤로");
			System.out.print(" > ");
			int input = Integer.parseInt(sc.nextLine());

			if (input == 1) {
				add(index);
			} else if (input == 2) {
				update();
			} else if (input == 3) {
				delete();
			} else if (input == 4) {
				System.out.println("Go Back");
				break;
			} else {
				System.out.println("Can't");
			}
		}
	}
	
	public ArrayList<Comment> getComments(int articleid){
		String sql = "SELECT * FROM COMMENT JOIN MEMBER ON comment.userid = member.userid WHERE articleid = ?";
		ArrayList<Comment> ac = db.getRows(sql, new CommentMapper(), articleid);
		
		return ac;
	}
	
	public void add(int index) {
		if(MemberDaoV2.m.isState()) {
			String sql = "insert into comment set userid = ?, articleid = ?, body = ?";
			System.out.print("내용 > ");
			String body = sc.nextLine();
			
			db.updateSQL(sql, MemberDaoV2.m.getUserid(), index, body);
			System.out.println("Good");			
		}else {
			System.out.println("Need signin");
		}
	}
	
	public void update() {
		String sql = "select * from comment join member on comment.userid = member.userid";
		System.out.print(" > ");
		int input = Integer.parseInt(sc.nextLine());
		Comment c = db.getRow(sql, new CommentMapper(), input);
		
		if(MemberDaoV2.m.getUserid() == c.getUserid()) {
			System.out.print("내용 > ");
			String body = sc.nextLine();
			
			sql = "update comment set body = ? where id = ?";
			db.updateSQL(sql, body, input);
			System.out.println("Good");
		}else {
			System.out.println("Can't");
		}
	}
	
	public void delete() {
		String sql = "select * from comment";
		System.out.print(" > ");
		int input = Integer.parseInt(sc.nextLine());
		Comment c = db.getRow(sql, new CommentMapper(), input);
		
		if(MemberDaoV2.m.getUserid() == c.getUserid()) {
			System.out.print("Really?(y/n) > ");
			if(sc.nextLine().equals("y")) {
				sql = "delete from comment where id = ?";
				db.updateSQL(sql, input);
				System.out.println("Good");
			}else {
				System.out.println("Don't");
			}
		}else {
			System.out.println("Can't");
		}
	}
}
