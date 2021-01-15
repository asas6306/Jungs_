<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>회원가입</h1>
	<a href="/web-exam1/Controller?action=doSignin&inputID=aaaa&inputPW=bbbb&inputNickname=cccc"></a>
	<form action="/web-exam1/Controller">
		아이디<br>
		<input type="text" name="inputID" placeholder="아이디를 입력하세요." value="${cashID}" required>${cID}<br>
		비밀번호<br>
		<input type="password" placeholder="비밀번호를 입력하세요." name="inputPW" required><br>
		닉네임<br>
		<input type="text" name="inputNickname" placeholder="닉네임을 입력하세요." value="${cashNN}" required>${cNN}<br>
		<input type="hidden" name="action" value="doSignup">
		<input type="submit" name="회원가입">	
	</form>
	
	<a href="/web-exam1/Controller?action=list">뒤로가기</a>
</body>
</html>