<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<body>

<h1>게시판</h1>

<table border="1">
	<tr>
		<th>번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>등록날짜</th>
	</tr>
	
	<c:forEach var="article" items="${aa}">
		<tr>
			<td>${article.id}</td>
			<td>${article.nickname}</td>
			<td><a href="/web-exam1/Con?action=detail&articleid=${article.id}">${article.title}</a></td>
			<td>${article.date}</td>
		</tr>
	</c:forEach>
		<tr>
			<td colspan="4" align="center"><a href="/web-exam1/Con?action=insert">게시물 추가</a></td>	
		</tr>
	
</table>
</body>
</html>