package board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BoardUseEnum.*;

@WebServlet("/Con")
public class Controller2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ArticleDaoV2 ad = new ArticleDaoV2();
		
		String action = request.getParameter("action");
		String dest = "/WEB-INF/JSP1/list.jsp";
		
		if(action.equals("list")) {
			request.setAttribute("aa", ad.getaa());
			
			dest = "/WEB-INF/JSP1/list.jsp";
		}else if(action.equals("detail")) {
			int articleid = Integer.parseInt(request.getParameter("articleid"));
			request.setAttribute("article", ad.getArticleById(articleid));
			
			dest = "/WEB-INF/JSP1/detail.jsp";
		}else if(action.equals("insert")) {
			dest = "/WEB-INF/JSP1/insert.jsp";
		}else if(action.equals("doInsert")) {
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			
			ad.add(title, body, 1);
			dest = "/WEB-INF/JSP1/list.jsp";
		}else if(action.equals("update")) {
			int articleid = Integer.parseInt(request.getParameter("articleid"));
			
			request.setAttribute("article", ad.getArticleById(articleid));
			dest = "/WEB-INF/JSP1/update.jsp";
		}else if(action.equals("doUpdate")) {
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			int articleid = Integer.parseInt(request.getParameter("articleid"));
			
			ad.update(title, body, articleid);
			dest = "/Con?action=detail&articleid=" + articleid;
		}else if(action.equals("delete")) {
			ad.delete(Integer.parseInt(request.getParameter("articleid")));
			
			dest="/WEB-INF/JSP1/list.jsp";
		}
		
		request.setAttribute("aa", ad.getaa());
		RequestDispatcher rd = request.getRequestDispatcher(dest);
		rd.forward(request, response);
	}
}
