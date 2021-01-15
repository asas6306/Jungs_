<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
</head>
<body>
<h1>게시물 목록(${member.nickname})</h1>
<a href="/web-exam1/Controller?action=login">로그인페이지</a>
<a href="/web-exam1/Controller?action=signup">회원가입</a>
<table border="1">
	<tr>
		<th>번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>작성날짜</th>
	</tr>
	
	<c:forEach var="article" items="${aa}">
		<tr>
			<td>${article.id}</td>
			<td>${article.nickname}</td>
			<td><a href="/web-exam1/Controller?action=detail&articleid=${article.id}">${article.title}</a></td>
			<td>${article.date}</td>
		</tr>
	</c:forEach>
	<c:choose>
		<c:when test="${member.state}">
			<tr>
				<td colspan="4" align="center"><a href="/web-exam1/Controller?action=add">글쓰기</a></td>
			</tr>
		</c:when>
	</c:choose>
	
</table>
</body>
</html>