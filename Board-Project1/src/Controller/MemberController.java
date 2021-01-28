package Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BoardUseEnum.Member;
import BoardUseEnum.MemberDao;

public class MemberController {

	MemberDao md = new MemberDao();

	public String doAction(HttpServletRequest request, HttpServletResponse response) {

		String dest = "/WEB-INF/JSP/list.jsp";
		String action = "";

		if (request.getParameter("action") != null) {
			action = request.getParameter("action");
		}

		if (action.equals("login")) {
			dest = "/WEB-INF/JSP/login.jsp";

		} else if (action.equals("doLogin")) {
			dest = doLogin(request, response);

		} else if (action.equals("doLogout")) {
			dest = doLogout(request, response);

		} else if (action.equals("signup")) {
			dest = "/WEB-INF/JSP/signup.jsp";

		} else if (action.equals("doSignup")) {
			dest = doSignup(request, response);

		} else if (action.equals("find")) {
			dest = "/WEB-INF/JSP/findIDPW.jsp";

		} else if (action.equals("mypage")) {
			dest = "/WEB-INF/JSP/mypage.jsp";

		} else if (action.equals("PWcheck")) {
			dest = PWcheck(request, response);

		} else if (action.equals("mypageUpdate")) {
			dest = mypageUpdate(request, response);

		} else if (action.equals("doMypageUpdate")) {
			dest = doMypageUpdate(request, response);

		} else if (action.equals("memberM")) {	// management Member
			dest = memberM(request, response);

		} else if (action.equals("memberMpage")) {
			dest = memberMpage(request, response);
			
		} else if (action.equals("doMemberM")) {
			dest = doMemberM(request, response);
			
		} else if (action.equals("resign")) {
			dest = resign(request, response);
			
		} 
		
		return dest;
	}

	private String doSignup(HttpServletRequest request, HttpServletResponse response) {
		String ID = request.getParameter("ID");
		request.setAttribute("ID", ID);
		String PW = request.getParameter("PW");
		String PW2 = request.getParameter("PW2");
		String nickname = request.getParameter("nickname");
		request.setAttribute("nickname", nickname);

		String dest = "/WEB-INF/JSP/signup.jsp";

		int cnt = 0;
		if (!PW.equals(PW2)) {
			request.setAttribute("PWc", "비밀번호가 일치하지 않습니다.");
			cnt = 1;
		}

		int check = md.signupCheck(ID, nickname);
		if (check == 1) {
			request.setAttribute("IDc", "중복된 아이디 입니다.");
		} else if (check == 2) {
			request.setAttribute("NNc", "중복된 닉네임 입니다.");
		} else if (check == 3) {
			request.setAttribute("IDc", "중복된 아이디 입니다.");
			request.setAttribute("NNc", "중복된 닉네임 입니다.");
		} else if (cnt == 0) {
			md.signup(ID, PW, nickname);
			request.setAttribute("state", true);
			dest = "/WEB-INF/JSP/login.jsp";
		}

		return dest;
	}

	private String doLogin(HttpServletRequest request, HttpServletResponse response) {
		String ID = request.getParameter("ID");
		request.setAttribute("ID", ID);
		String PW = request.getParameter("PW");

		String dest = "";
		Member m = md.getMember(ID, PW);

		if (m == null) {
			request.setAttribute("failMsg", "로그인에 실패하였습니다.");

			dest = "/WEB-INF/JSP/login.jsp";
		} else {
			HttpSession session = request.getSession();
			m.setState(true);
			session.setAttribute("m", m);

			dest = "/WEB-INF/JSP/main.jsp";
		}

		return dest;
	}

	private String doLogout(HttpServletRequest request, HttpServletResponse response) {
		Member m = new Member();
		HttpSession session = request.getSession();
		session.setAttribute("m", m);

		return "/WEB-INF/JSP/main.jsp";
	}
	
	private String PWcheck(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("page", request.getParameter("page"));
		
		return "/WEB-INF/JSP/PWcheck.jsp";
	}

	private String mypageUpdate(HttpServletRequest request, HttpServletResponse response) {
		String PW1 = request.getParameter("PW");
		String PW2 = request.getParameter("PWcheck");
		String getPage = request.getParameter("page");

		String dest = "";
		if (PW1.equals(PW2)) {
			if(getPage.equals("update")) {
				dest = "/WEB-INF/JSP/mypageUpdate.jsp";				
			} else {
				dest = "/WEB-INF/JSP/resignCheck.jsp";
			}
		} else {
			request.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
			request.setAttribute("page", request.getParameter("page"));
			
			dest = "/WEB-INF/JSP/PWcheck.jsp";
		}

		return dest;
	}
	
	private String doMypageUpdate(HttpServletRequest request, HttpServletResponse response) {
		String ID = request.getParameter("ID");
		String nickname = request.getParameter("nickname");
		String PW1 = request.getParameter("PW1");
		String PW2 = request.getParameter("PW2");

		String dest = "/WEB-INF/JSP/mypageUpdate.jsp";
		
		int cnt = 0;
		if (!PW1.equals(PW2)) {
			request.setAttribute("PWc", "비밀번호가 일치하지 않습니다.");
			cnt = 1;
		}

		int check = md.signupCheck("", nickname);
		if (check == 2) {
			request.setAttribute("NNc", "중복된 닉네임 입니다.");
		} else if (cnt == 0) {
			md.updateMember(ID, PW1, nickname);
			
			Member m = md.getMember(ID, PW1);
			m.setState(true);
			
			HttpSession session = request.getSession();
			session.setAttribute("m", m);
			
			dest = "/WEB-INF/JSP/mypage.jsp";
		}

		return dest;
	}
	
	private String memberM(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Member> am = md.getMembers();
		request.setAttribute("am", am);
		
		return "/WEB-INF/JSP/memberM.jsp";
	}
	
	private String memberMpage(HttpServletRequest request, HttpServletResponse response) {
		int uid = Integer.parseInt(request.getParameter("uid"));
		Member m = md.getMember(uid);
		
		request.setAttribute("member", m);
		
		return "/WEB-INF/JSP/memberMpage.jsp";
	}
	
	private String doMemberM(HttpServletRequest request, HttpServletResponse response) {
		int uid = Integer.parseInt(request.getParameter("uid"));
		Member m = md.getMember(uid);
		m.setID(request.getParameter("ID"));
		m.setNickname(request.getParameter("nickname"));
		m.setState(Boolean.parseBoolean(request.getParameter("ban")));
		m.setMng(Boolean.parseBoolean(request.getParameter("mng")));
		
		md.updateMember(m);
		
		return memberMpage(request, response);
	}
	
	private String resign(HttpServletRequest request, HttpServletResponse response) {
		int uid = Integer.parseInt(request.getParameter("uid"));
		md.resignMember(uid);
		
		HttpSession session = request.getSession();
		session.setAttribute("m", new Member());
		
		return "/WEB-INF/JSP/login.jsp";
	}
}
