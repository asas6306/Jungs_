package Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController {
	public String doAction(HttpServletRequest request, HttpServletResponse response) {

		String dest = "/WEB-INF/JSP/main.jsp";
		String action = "";

		if (request.getParameter("action") != null) {
			action = request.getParameter("action");
		}

		if (action.equals("introduce")) {
			dest = "/WEB-INF/JSP/introduce.jsp";

		}

		return dest;
	}

}
