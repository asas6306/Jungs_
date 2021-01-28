package BoardUseEnum;

import java.util.ArrayList;
import java.util.Scanner;

public class CommentDao {
	Scanner sc = new Scanner(System.in);
	DBUtil db = new DBUtil();

	public ArrayList<Comment> getComments(int articleid) {
		String sql = "SELECT * FROM COMMENT JOIN MEMBER ON comment.uid = member.uid WHERE aid = ? and commentreply is null";

		return db.getRows(sql, new CommentMapper(), articleid);
	}

	public void add(int index) {
		if (MemberDao.m.isState()) {
			String sql = "insert into comment set uid = ?, aid = ?, body = ?";
			System.out.print("내용 > ");
			String body = sc.nextLine();

			db.updateSQL(sql, MemberDao.m.getUid(), index, body);
			System.out.println("Good");
		} else {
			System.out.println("Need signin");
		}
	}

	public void add(String body, int uid, int aid) {
		String sql = "insert into comment set uid = ?, aid = ?, body = ?";

		db.updateSQL(sql, uid, aid, body);
	}

	public void update(String body, int cid) {
		String sql="update comment set body = ? where cid = ?";
		db.updateSQL(sql, body, cid);
	}

	public void delete(int cid) {
		String sql = "delete from comment where cid = ?";
		db.updateSQL(sql, cid);
	}

	public void addCommentReply(String body, int cid, int uid, int aid) {
		String sql = "insert comment set body = ?, commentreply = ?, uid = ?, aid = ?";
		db.updateSQL(sql, body, cid, uid, aid);
	}

	public ArrayList<Comment> getCommentReply(int cid) {
		String sql = "select * from comment join member on comment.uid = member.uid where aid = ? and commentreply is not null";
		
		return db.getRows(sql, new CommentMapper(), cid);
	}
}
