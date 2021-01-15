package BoardUseEnum;

import java.util.ArrayList;
import java.util.Scanner;

public class ArticleDao {
	Scanner sc = new Scanner(System.in);
	DBUtil db = new DBUtil();

	public ArrayList<Article> getaa() {
		String sql = "select * from article left join member on article.userid = member.userid";
		ArrayList<Article> aa = db.getRows(sql, new ArticleMapper());
		return aa;
	}

	public ArrayList<Article> getaa(int orderIndex) {
		String sql = "select * from article left join member on article.userid = member.userid";
		if (orderIndex != 0) {
			sql = sql + order(orderIndex);
		}
		ArrayList<Article> aa = db.getRows(sql, new ArticleMapper());
		return aa;
	}

	public void upcntHit(int articleid) {
		String sql = "update article set hit = hit+1 where id = ?";
		db.updateSQL(sql, articleid);
	}

	public void add(String title, String body, int userid) { // 게시물 추가
		String sql = "insert into article set title = ?, `body` = ?, userid = ?";

		db.updateSQL(sql, title, body, userid);
	}

	public void update(String title, String body, int index) { // 게시물수정
		String sql = "update article set title = ?, `body` = ? where id = ?";

		db.updateSQL(sql, title, body, index);

	}

	public void delete(int index) { // 게시물삭제
		String asql = "DELETE FROM article WHERE id = ?";
		db.updateSQL(asql, index);
		String csql = "DELETE FROM comment WHERE articleid = ?";
		db.updateSQL(csql, index);
		String lsql = "DELETE FROM like WHERE articleid = ?";
		db.updateSQL(csql, index);
	}

	public String sortSql(int input) {
		String strin = "";
		if (input == 2) {
			strin = "title";
		} else if (input == 3) {
			strin = "body";
		} else if (input == 4) {
			strin = "member.nickname";
		} else {
			strin = "article.id desc";
		}
		String str = "select * from article left join member on article.userid = member.userid order by " + strin;
		return str;
	}

	public ArrayList<Article> getSearchaa(int sDate, int index, String body, int orderIndex) {
		ArrayList<Article> aa = null;
		String sql = getSearch(sDate, index);

		if (orderIndex != 0) {
			sql = sql + order(orderIndex);
		}
//		좋아요 sql문... null도 1개로 카운트해서 실용성이 없음 ㅠㅠ null을 빼면 좋아요 0개인 개시물 안나옴 ㅠ
//		sql = "SELECT a.*, COUNT(`like`) likecnt FROM (" + sql + ") a LEFT JOIN `like` ON a.id = `like`.articleid WHERE `like`.articleid IS NOT NULL GROUP BY a.id";

		String searchBody = "%" + body + "%";
		if (index == 1) {
			aa = db.getRows(sql, new ArticleMapper(), searchBody, searchBody);
		} else {
			aa = db.getRows(sql, new ArticleMapper(), searchBody);
		}

		return aa;
	}

	public String getSearch(int sDate, int index) {
		String str = "SELECT * FROM article LEFT JOIN MEMBER ON article.userid = member.userid ";
//		SELECT * FROM article LEFT JOIN MEMBER ON article.userid = member.userid WHERE article.date > DATE_ADD(NOW(), INTERVAL -1 WEEK) HAVING title='a';
//		SELECT * FROM (SELECT * FROM article WHERE DATE > DATE_ADD(NOW(), INTERVAL -1 WEEK)) a LEFT JOIN MEMBER ON a.userid =  member.userid WHERE title = 'a';

		str = str + getsDate(sDate) + getsIndex(index);

		return str;
	}

	public String getsDate(int sDate) {
		String str = "";

		if (sDate == 2) {
			str = "WHERE article.date > DATE_ADD(NOW(), INTERVAL -1 day) ";
		} else if (sDate == 3) {
			str = "WHERE article.date > DATE_ADD(NOW(), INTERVAL -1 week) ";
		} else if (sDate == 4) {
			str = "WHERE article.date > DATE_ADD(NOW(), INTERVAL -1 month) ";
		} else if (sDate == 5) {
			str = "WHERE article.date > DATE_ADD(NOW(), INTERVAL -1 year) ";
		} else {
			str = "";
		}

		return str;
	}

	public String getsIndex(int index) {
		String str = "";

		if (index == 2) {
			str = "having title like ?";
		} else if (index == 3) {
			str = "having body like ?";
		} else if (index == 4) {
			str = "having nickname like ?";
		} else {
			str = "having title like ? or body like ?";
		}

		return str;
	}

	public String order(int orderIndex) {
		String str = "";

		if (orderIndex == 1) {
			str = " order by `like`, hit desc";
		} else if (orderIndex == 2) {
			str = " order by `like` desc, hit desc";
		} else if (orderIndex == 3) {
			str = " order by hit, `like` desc";
		} else if (orderIndex == 4) {
			str = " order by hit desc, `like` desc";
		}

		return str;
	}

	public Article getArticleById(int id) {
		ArrayList<Article> aa = getaa();
		Article a = null;
		for (Article casha : aa) {
			if (casha.getArticleid() == id) {
				a = casha;
			}
		}
		return a;
	}

	public void like(int aid, int uid, boolean like) {
		String sql1 = "";
		String sql2 = "";
		if (like) {
			sql1 = "update article set `like` = `like` + 1 where id = ?";
			sql2 = "insert into `like` set articleid = ?, userid = ?";
		} else {
			sql1 = "update article set `like` = `like` - 1 where id = ?";
			sql2 = "delete from `like` where articleid = ? and userid = ?";
		}
		
		db.updateSQL(sql1, aid);
		db.updateSQL(sql2, aid, uid);
	}
	
	public boolean likeCheck(int aid, int uid) {
		String sql = "select * from `like` where articleid = ? and userid = ?";
		
		return db.existCheck(sql, aid, uid);
	}

}
