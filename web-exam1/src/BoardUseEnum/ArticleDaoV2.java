package BoardUseEnum;

import java.util.ArrayList;
import java.util.Scanner;

public class ArticleDaoV2 {
	Scanner sc = new Scanner(System.in);
	DBUtil db = new DBUtil();

	public void listAll() { // 전체출력
		String sql = "select * from article left join member on article.userid = member.userid";
		ArrayList<Article> aa = db.getRows(sql, new ArticleMapper());
		for (int i = 0; i < aa.size(); i++) {
			Article a = aa.get(i);
			System.out.println(a.getId() + "]" + a.getNickname() + "\t" + a.getTitle());
			System.out.println("----------------------------");
		}
	}

	public void listAll(ArrayList<Article> aa) { // 전체출력
		for (int i = 0; i < aa.size(); i++) {
			Article a = aa.get(i);
			System.out.println(a.getId() + "] " + a.getNickname() + "\t" + a.getTitle());
			System.out.println("----------------------------");
		}
	}
	
	public ArrayList<Article> getaa() {
		String sql = "select * from article left join member on article.userid = member.userid";
		ArrayList<Article> aa = db.getRows(sql, new ArticleMapper());
		return aa;
	}

	public void choiceArticle() {
		System.out.print("index > ");
		int index = Integer.parseInt(sc.nextLine());

		String sql = "select * from article where id = ?";
		if (db.existCheck(sql, index)) {
			String vsql = "update article set hit = hit+1 where id = ?";
			db.updateSQL(vsql, index);
			choiceArticleAction(index);
		} else {
			System.out.println("Not exist");
		}
	}

	public void choiceArticleAction(int index) {
		while (true) {
			listOne(index);
			System.out.println("1.comment 2.like 3.update 4.delete 5.back");
			System.out.print(" > ");
			int input = Integer.parseInt(sc.nextLine());

			if (input == 1) {
				CommentDao cd = new CommentDao();
				cd.commentFlatform(index);
			} else if (input == 2) {
				String csql = "select * from `like` where articleid = ? and userid = ?";
				boolean oc = db.existCheck(csql, index, MemberDaoV2.m.getUserid());
				if (oc) {
					String dsql = "delete from `like` where articleid = ? and userid = ?";
					db.updateSQL(dsql, index, MemberDaoV2.m.getUserid());
					String lsql = "update article set `like` = `like` - 1 where id = ?";
					db.updateSQL(lsql, index);
					System.out.println("Cancelled like");
				} else {
					String isql = "insert into `like` (articleid, userid) values (?, ?)";
					db.updateSQL(isql, index, MemberDaoV2.m.getUserid());
					String lsql = "update article set `like` = `like` + 1 where id = ?";
					db.updateSQL(lsql, index);
					System.out.println("Like this");
				}
				System.out.println("======= 좋아요 목록 =======");
				String sql = "select * from `like` join member on `like`.userid = member.userid where `like`.articleid = ?";
				ArrayList<Like> al = db.getRows(sql, new LikeMapper(), index);
				for (int i = 0; i < al.size(); i++) {
					System.out.print(al.get(i).getNickname() + "  ");
					if (i > 0 && i % 10 == 0)
						System.out.println();
				}
				System.out.println();
			} else if (input == 3) {
				update(index);
			} else if (input == 4) {
				delete(index);
			} else if (input == 5) {
				System.out.println("Go Back");
			} else if (input == -1) {
				System.out.println("Number Please^-^");
			} else {
				System.out.println("Not exist");
			}
		}
	}

	public void listOne(int index) { // 상세보기, 단일출력
		String sql = "select * from article left join member on article.userid = member.userid where article.id = ?";
		Article a = db.getRow(sql, new ArticleMapper(), index);
		System.out.println("======= " + a.getId() + "번 게시물 =======");

		System.out.println("작성자:" + a.getNickname() + "\t" + a.getDate());
		System.out.println("제목:" + a.getTitle() + "\t조회수:" + a.getHit() + "\t좋아요:" + a.getLike());
		System.out.println("내용:" + a.getBody());
		System.out.println("------------------------------------");
	}

	public void add() { // 게시물 추가
		if (MemberDaoV2.m.isState()) {
			String sql = "insert into article set title = ?, `body` = ?, userid = ?";
			System.out.print("title > ");
			String title = sc.nextLine();
			System.out.print("body > ");
			String body = sc.nextLine();

			db.updateSQL(sql, title, body, MemberDaoV2.m.getUserid());
			System.out.println("Good");
		} else {
			System.out.println("Can't");
		}

	}
	public void add(String title, String body, int userid) { // 게시물 추가
		String sql = "insert into article set title = ?, `body` = ?, userid = ?";
		
		db.updateSQL(sql, title, body, userid);
	}

	public void update(int index) { // 게시물수정
		String sql = "update article set title = ?, `body` = ? where id = ?";
		System.out.print("title > ");
		String title = sc.nextLine();
		System.out.print("body > ");
		String body = sc.nextLine();

		db.updateSQL(sql, title, body, index);
		System.out.println("Good");

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

	public void sort() {
		System.out.println("1.id 2.제목 3.내용 4.작성자");
		int index = Integer.parseInt(sc.nextLine());
		String sql = sortSql(index);

		ArrayList<Article> aa = db.getRows(sql, new ArticleMapper());
		listAll(aa);
		choiceArticle();
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

	public void page() {
		String sql = "select * from article left join member on article.userid = member.userid";
		ArrayList<Article> aa = db.getRows(sql, new ArticleMapper());

		int d = 3; // displaysCount
		int s = aa.size() % d == 0 ? (int) (aa.size() / d) - 1 : (int) (aa.size() / d); // scroll
		int p = 0; // pointer
		while (true) {
			// 상단 게시물 출력
			for (int i = p * d; i < (p + 1) * d; i++) {
				if (i < aa.size()) {
					Article a = aa.get(i);
					System.out.println(a.getId() + "] " + a.getNickname() + "\t" + a.getTitle());
					System.out.println("----------------------------");
				}
			}
			// 하단 페이지 출력
			for (int i = ((p / 5) * 5); i < ((p / 5 + 1) * 5); i++) {
				if (i <= s) {
					if (i == p) {
						System.out.print(" [" + (i + 1) + "] ");
					} else {
						System.out.print("  " + (i + 1) + "  ");
					}
				}
			}
			System.out.println();

			System.out.println("1.이전페이지 2.이전 3.다음 4.다음페이지 5.페이지수정 6.뒤로가기");
			System.out.print(" > ");
			int input = Integer.parseInt(sc.nextLine());
			if (input == 1) {
				if (p - 5 < 0) {
					p = 0;
					System.out.println("■■■■■ First Page ■■■■■");
				} else {
					p -= 5;
				}
			} else if (input == 2) {
				if (p == 0) {
					System.out.println("■■■■■ First Page ■■■■■");
				} else {
					p--;
				}
			} else if (input == 3) {
				if (p == s) {
					System.out.println("■■■■■ Last Page ■■■■■");
				} else {
					p++;
				}
			} else if (input == 4) {
				if (p + 5 > s) {
					p = s;
					System.out.println("■■■■■ Last Page ■■■■■");
				} else {
					p += 5;
				}
			} else if (input == 5) {
				System.out.print(" > ");
				d = Integer.parseInt(sc.nextLine());
				s = s = (int) (aa.size() % d) == 0 ? (int) (aa.size() / d) - 1 : (int) (aa.size() / d);
				p = 0;
			} else if (input == 6) {
				System.out.println("Go Back");
				break;
			} else {
				System.out.println("Can't");
			}
		}
	}

	public void makeCashArticle() { // 임시
		System.out.print("생성 할 게시물 개수 > ");
		int input = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < input; i++) {
			int ranHit = (int) (Math.random() * 100);
			String sql = "insert into article set title = ?, body = ?, userid = ?";
			String str = String.valueOf(ranHit);
			db.updateSQL(sql, str, str, MemberDaoV2.m.getUserid());
		}
	}

	public void search() {
		System.out.println("1.제목+내용 2.제목 3.내용 4.작성자 5.뒤로가기");
		System.out.print(" > ");
		int index = Integer.parseInt(sc.nextLine());

		if (index != 5) {
			ArrayList<Article> aa = null;
			System.out.print("검색어 > ");
			String e = sc.nextLine();
			String ie = "%" + e + "%";
			if (index == 5) {
			} else if (2 <= index && index <= 4) {
				aa = db.getRows(getSearchSql(index), new ArticleMapper(), ie);
			} else {
				aa = db.getRows(getSearchSql(index), new ArticleMapper(), ie, ie);
			}

			if (aa != null) {
				listAll(aa);
				choiceArticle();
			}
		}
	}

	public String getSearchSql(int index) {
		String str = "select * from article left join member on article.userid = member.userid";

		if (index == 2) {
			str = str + " where title like ?";
		} else if (index == 3) {
			str = str + " where body like ?";
		} else if (index == 4) {
			str = str + " where nickname like ?";
		} else {
			str = str + " where title like ? or body like ?";
		}

		return str;
	}
	
	public Article getArticleById(int id) {
		ArrayList<Article> aa = getaa();
		Article a = null;
		for(Article casha:aa) {
			if(casha.getId()==id) {
				a = casha;
			}
		}
		return a;
	}

}
