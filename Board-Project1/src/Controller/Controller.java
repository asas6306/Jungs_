package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class Controller extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 공통 작업
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();

		// 요청 url 가져오기
		String uri = request.getRequestURI();
		String[] uris = uri.split("/");

		String module = uris[2];
		String dest = "";

		System.out.println(uri);

		if (module.equals("main.do")) {
			MainController mc = new MainController();
			dest = mc.doAction(request, response);
		} else if (module.equals("article.do")) {
			ArticleController ac = new ArticleController();
			dest = ac.doAction(request, response);
		} else if (module.equals("member.do")) {
			MemberController mc = new MemberController();
			dest = mc.doAction(request, response);
		} else if (module.equals("management.do")) {
			dest = "/WEB-INF/JSP/management.jsp";
		} else {
			System.out.println("Nothing");
			return;
		}

		if (dest.startsWith("redirect: ")) {
			// 리다이렉팅
			String[] bits = dest.split(" ");
			String url = bits[1];
			response.sendRedirect(url);

		} else {
			// 포워딩
			RequestDispatcher rd = request.getRequestDispatcher(dest);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}