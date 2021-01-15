<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
</head>
<body>
<h1>${article.id}번 게시물 수정</h1>
<a href="/web-exam1/Con?action=doUpdate&title=aaaa&body=bbbb&articleid=1"></a>
<form action="/web-exam1/Con">
	제목 : <input type="text" name="title" value="${article.title}"><br>
	내용 : <input type="text" name="body" value="${article.body}"><br>
	<input type="hidden" name="articleid" value="${article.id}">
	<input type="hidden" name="action" value="doUpdate">
	<input type="submit">
</form>
</body>
</html>