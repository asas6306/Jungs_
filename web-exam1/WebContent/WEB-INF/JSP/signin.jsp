<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
<h1>로그인</h1>
	<a href="/web-exam1/Controller?action=doLogin&inputID=aaaa&inputPW=bbbb"></a>
	<form action="/web-exam1/Controller">
		아이디<br><input type="text" name="inputID" required><br>
		비밀번호<br><input type="password" name="inputPW" required><br>
		<input type="hidden" name="action" value="doLogin" required><br>
		<input type="submit" value="로그인">
	</form>
	<a href="/web-exam1/Controller?action=signup">회원가입</a>
</body>
</html>