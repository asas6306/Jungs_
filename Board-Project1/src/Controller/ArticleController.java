package Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BoardUseEnum.Article;
import BoardUseEnum.ArticleDao;
import BoardUseEnum.CommentDao;

public class ArticleController {

	ArticleDao ad = new ArticleDao();
	CommentDao cd = new CommentDao();

	public String doAction(HttpServletRequest request, HttpServletResponse response) {

		String dest = "/WEB-INF/JSP/list.jsp";
		String action = "";

		if (request.getParameter("action") != null) {
			action = request.getParameter("action");
		}

		if (action.equals("list")) {
			dest = list(request, response);

		} else if (action.equals("add")) { // 게시물 추가
			dest = "/WEB-INF/JSP/add.jsp";

		} else if (action.equals("doAdd")) { // 게시물 추가 동작
			dest = doAdd(request, response);

		} else if (action.equals("delete")) { // 게시물 삭제
			dest = delete(request, response);

		} else if (action.equals("update")) { // 게시물 수정
			dest = update(request, response);

		} else if (action.equals("doUpdate")) { // 게시물 수정 동작
			dest = doUpdate(request, response);

		} else if (action.equals("detail")) { // 자세히보기
			dest = detail(request, response);

		} else if (action.equals("addComment")) { // 댓글달기
			dest = addComment(request, response);

		} else if (action.equals("updateComment")) { // 댓글수정
			dest = updateComment(request, response);

		} else if (action.equals("doUpdateComment")) { // 댓글수정
			dest = doUpdateComment(request, response);

		} else if (action.equals("deleteComment")) { // 댓글삭제
			dest = deleteComment(request, response);

		} else if (action.equals("commentReply")) { // 대댓글
			dest = commentReply(request, response);

		} else if (action.equals("doCommentReply")) { // 대댓글
			dest = doCommentReply(request, response);

		} else if (action.equals("like")) { // 대댓글
			dest = like(request, response);

		}

		return dest;
	}

	private String list(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Article> aa = null;
		int orderIndex = 0;

		if (request.getParameter("order") != null) {
			String order = request.getParameter("order");
			String order1 = "";
			if (order.equals("like")) {
				orderIndex = 1;
				order1 = "&order=like";
			} else if (order.equals("liked")) {
				orderIndex = 2;
				order1 = "&order=liked";
				request.setAttribute("order2", "1");
			} else if (order.equals("hit")) {
				orderIndex = 3;
				order1 = "&order=hit";
			} else if (order.equals("hitd")) {
				orderIndex = 4;
				order1 = "&order=hitd";
				request.setAttribute("order2", "2");
			}
			request.setAttribute("order1", order1);
		}

		if (request.getParameter("searchBody") != null) { // 검색기능
			int sDate = Integer.parseInt(request.getParameter("searchDate"));
			int index = Integer.parseInt(request.getParameter("searchIndex"));
			String body = request.getParameter("searchBody");
			aa = ad.getSearchaa(sDate, index, body, orderIndex);
			request.setAttribute("listStr", "/Board-Project1/article.do?action=list&" + "searchBody=" + body
					+ "&searchIndex=" + index + "&searchDate=" + sDate + "&pointer=");
		} else {
			aa = ad.getaa(orderIndex);
			request.setAttribute("listStr", "/Board-Project1/article.do?action=list&pointer=");
		}

		request.setAttribute("aa", page(request, response, aa));

		return "/WEB-INF/JSP/list.jsp";
	}

	private ArrayList<Article> page(HttpServletRequest request, HttpServletResponse response, ArrayList<Article> caa) {
		int page = caa.size();
		int pagesize = 10; // 한 페이지에 보여 줄 게시물 수
		int scroll = (page % pagesize == 0) && (page != 0) ? page / pagesize : page / pagesize + 1;
		int scrollsize = 5; // 한 블럭에 보여 줄 스크롤 수
		int block = (scroll % scrollsize == 0) && (scroll != 0) ? scroll / scrollsize : scroll / scrollsize + 1;
		int p = 1; // 포인터
		ArrayList<Article> aa = new ArrayList<Article>();

		if (request.getParameter("pointer") != null) { // 포인터설정
			p = Integer.parseInt(request.getParameter("pointer"));
		}

		if (p < 1) { // 최소값, 최대값 초과 제한
			p = 1;
		} else if (p > scroll) {
			p = scroll;
		}

		int b = (p - 1) / scrollsize; // 블럭
		int begin = 1 + (b * scrollsize); // 스크롤 비긴
		if (begin < 0) {
			begin = 0;
		}

		int end = (b + 1) * scrollsize; // 스크롤 엔드
		if (end > scroll) {
			end = scroll;
		}

		if (request.getParameter("move") != null) { // 이전 다음 이동설정
			String move = request.getParameter("move");
			if (move.equals("left")) {
				p = end;
			} else if (move.equals("right")) {
				p = begin;
			}
		}

		int scrollp = p * pagesize;
		// 아마 오류나시면 게시물 몇 개 추가를...
		for (int i = (p - 1) * pagesize; i < scrollp; i++) { // 게시물 추가
			if (i < page) {
				aa.add(caa.get(i));
			}
		}

		if (b == 0) {
			request.setAttribute("leftend", true);
		}
		if ((b == (block - 1)) || (block == 1)) {
			request.setAttribute("rightend", true);
		}

		HttpSession session = request.getSession();
		session.setAttribute("pointer", p);
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);

		return aa;
	}

	private String doAdd(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		int uid = Integer.parseInt(request.getParameter("uid"));

		ad.add(title, body, uid);

		return list(request, response);
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		ad.delete(aid);

		return list(request, response);
	}

	private String update(HttpServletRequest request, HttpServletResponse response) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		request.setAttribute("aid", aid);

		return "/WEB-INF/JSP/update.jsp";
	}

	private String doUpdate(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		int aid = Integer.parseInt(request.getParameter("aid"));

		ad.update(title, body, aid);

		return detail(request, response);
	}

	private String detail(HttpServletRequest request, HttpServletResponse response) {
		String dest = "";
		if (request.getParameter("uid").equals("")) {
			request.setAttribute("failMsg", "게시판 이용을 위해 로그인이 필요합니다.");
			
			dest = "/WEB-INF/JSP/login.jsp";
		} else {
			int aid = Integer.parseInt(request.getParameter("aid"));
			request.setAttribute("a", ad.getArticleById(aid));
			request.setAttribute("ca", cd.getComments(aid));
			request.setAttribute("cra", cd.getCommentReply(aid));
			int uid = Integer.parseInt(request.getParameter("uid"));
			request.setAttribute("like", ad.likeCheck(aid, uid));

			if (request.getParameter("hit") != null) {
				request.getParameter("hit");
				ad.upcntHit(aid);
			}
			
			Article a = ad.getArticleById(aid);
			request.setAttribute("a", a);

			dest = "/WEB-INF/JSP/detail.jsp";
		}

		return dest;
	}

	private String like(HttpServletRequest request, HttpServletResponse response) {
		boolean like = Boolean.parseBoolean(request.getParameter("like"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		int uid = Integer.parseInt(request.getParameter("uid"));

		ad.like(aid, uid, like);

		return detail(request, response);
	}

	// 댓글 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	private String addComment(HttpServletRequest request, HttpServletResponse response) {
		String body = request.getParameter("body");
		int uid = Integer.parseInt(request.getParameter("uid"));
		int aid = Integer.parseInt(request.getParameter("aid"));

		cd.add(body, uid, aid);

		return detail(request, response);
	}

	private String updateComment(HttpServletRequest request, HttpServletResponse response) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		request.setAttribute("cid", cid);
		request.setAttribute("check", "update");

		return detail(request, response);
	}

	private String doUpdateComment(HttpServletRequest request, HttpServletResponse response) {
		String body = request.getParameter("body");
		int cid = Integer.parseInt(request.getParameter("cid"));

		cd.update(body, cid);

		return detail(request, response);
	}

	private String deleteComment(HttpServletRequest request, HttpServletResponse response) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		cd.delete(cid);

		return detail(request, response);
	}

	private String commentReply(HttpServletRequest request, HttpServletResponse response) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		request.setAttribute("cid", cid);
		request.setAttribute("check", "reply");

		return detail(request, response);
	}

	private String doCommentReply(HttpServletRequest request, HttpServletResponse response) {
		String body = request.getParameter("body");
		int cid = Integer.parseInt(request.getParameter("cid"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		cd.addCommentReply(body, cid, uid, aid);

		return detail(request, response);
	}
	// 댓글 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}
