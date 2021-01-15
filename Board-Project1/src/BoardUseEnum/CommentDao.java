package BoardUseEnum;

import java.util.ArrayList;
import java.util.Scanner;

public class CommentDao {
	Scanner sc = new Scanner(System.in);
	DBUtil db = new DBUtil();

	public ArrayList<Comment> getComments(int articleid) {
		String sql = "SELECT * FROM COMMENT JOIN MEMBER ON comment.userid = member.userid WHERE articleid = ? and commentreply is null";

		return db.getRows(sql, new CommentMapper(), articleid);
	}

	public void add(int index) {
		if (MemberDao.m.isState()) {
			String sql = "insert into comment set userid = ?, articleid = ?, body = ?";
			System.out.print("내용 > ");
			String body = sc.nextLine();

			db.updateSQL(sql, MemberDao.m.getUserid(), index, body);
			System.out.println("Good");
		} else {
			System.out.println("Need signin");
		}
	}

	public void add(String body, int userid, int articleid) {
		String sql = "insert into comment set userid = ?, articleid = ?, body = ?";

		db.updateSQL(sql, userid, articleid, body);
	}

	public void update(String body, int cid) {
		String sql="update comment set body = ? where id = ?";
		db.updateSQL(sql, body, cid);
	}

	public void delete(int cid) {
		String sql = "delete from comment where id = ?";
		db.updateSQL(sql, cid);
	}

	public void addCommentReply(String body, int commentid, int userid, int articleid) {
		String sql = "insert comment set body = ?, commentreply = ?, userid = ?, articleid = ?";
		db.updateSQL(sql, body, commentid, userid, articleid);
	}

	public ArrayList<Comment> getCommentReply(int commentid) {
		String sql = "select * from comment join member on comment.userid = member.userid where articleid = ? and commentreply is not null";
		
		return db.getRows(sql, new CommentMapper(), commentid);
	}
}
