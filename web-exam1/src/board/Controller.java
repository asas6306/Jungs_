package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BoardUseEnum.Article;
import BoardUseEnum.ArticleDaoV2;
import BoardUseEnum.Comment;
import BoardUseEnum.CommentDao;
import BoardUseEnum.Member;
import BoardUseEnum.MemberDaoV2;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		ArticleDaoV2 ad = new ArticleDaoV2();
		MemberDaoV2 md = new MemberDaoV2();
		
		String dest = "/WEB-INF/JSP/list.jsp";
		
		if(action.equals("login")){
			dest = "/WEB-INF/JSP/signin.jsp";
		}else if(action.equals("doLogin")){
			String inputID = request.getParameter("inputID");
			request.setAttribute("inputID", inputID);
			String inputPW = request.getParameter("inputPW");
			request.setAttribute("inputPW", inputPW);
			
			if(md.memberCheck(inputID, inputPW)) {
				HttpSession session = request.getSession();
				Member member = md.getMember(inputID, inputPW);
				member.setState(true);
				session.setAttribute("member", member);
				
				dest = "/WEB-INF/JSP/list.jsp";
			}else {
				String failMsg = "로그인에 실패하였습니다.";
				request.setAttribute("failMsg", failMsg);
				
				dest = "/WEB-INF/JSP/fails.jsp";
			}
		}else if(action.equals("signup")) {
			dest = "/WEB-INF/JSP/signup.jsp";
		}else if(action.equals("doSignup")) {
			String inputID = request.getParameter("inputID");
			request.setAttribute("cashID", inputID);
			String inputPW = request.getParameter("inputPW");
			String inputNickname = request.getParameter("inputNickname");
			request.setAttribute("cashNN", inputNickname);
			dest = "/WEB-INF/JSP/signup.jsp";
			int check = md.signupCheck(inputID, inputNickname);
			
			String cID = "";
			String cNN = "";
			
			if(check==1) {
				cID = "중복된 아이디 입니다.";
			}else if(check==2) {
				cNN = "중복된 닉네임 입니다.";
			}else if(check==3) {
				cID = "중복된 아이디 입니다.";
				cNN = "중복된 닉네임 입니다.";
			}else {
				md.signup(inputID, inputPW, inputNickname);
				dest = "/WEB-INF/JSP/signin.jsp";
			}
			
			
			request.setAttribute("cID", cID);
			request.setAttribute("cNN", cNN);
			
		}else if(action.equals("list")) {
			request.setAttribute("aa", ad.getaa());
			
			dest = "/WEB-INF/JSP/list.jsp";
		}else if(action.equals("detail")) {
			HttpSession session = request.getSession();
			Member member = (Member)session.getAttribute("member");
			
			if(member.isState()) {
				int articleid = Integer.parseInt(request.getParameter("articleid"));
				
				request.setAttribute("article", ad.getArticleById(articleid));
				
				CommentDao c = new CommentDao();
				ArrayList<Comment> ac = c.getComments(articleid);
				
				request.setAttribute("ac", ac);
				
				
				dest = "/WEB-INF/JSP/detail.jsp";				
			}else {
				dest = "/WEB-INF/JSP/signin.jsp";
			}
			
		}else if(action.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("articleid"));
			ad.delete(id);
			
			dest = "/WEB-INF/JSP/list.jsp";
		}else if(action.equals("add")) {
			dest = "/WEB-INF/JSP/add.jsp";
		}else if(action.equals("doAdd")) {
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			int userid = 1;
			
			ad.add(title, body, userid);
			
			dest = "/WEB-INF/JSP/list.jsp";
		}else if(action.equals("update")) {
			int articleid = Integer.parseInt(request.getParameter("articleid"));
			Article a = ad.getArticleById(articleid);
			request.setAttribute("article", a);
			
			dest = "/WEB-INF/JSP/update.jsp";
		}else if(action.equals("doUpdate")) {
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			int articleid = Integer.parseInt(request.getParameter("articleid"));
			
			ad.update(title, body, articleid);
		}
		
		request.setAttribute("aa", ad.getaa());
//		request.setAttribute("member", m);
		RequestDispatcher rd = request.getRequestDispatcher(dest);
		rd.forward(request, response);
	}

}
