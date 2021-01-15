<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 등록</title>
</head>
<body>
<h1>새 게시물 작성</h1><hr>
	<a href="/web-exam1/Controller?action=doAdd&title=aaaa&body=bbbb"></a>
	<form action="/web-exam1/Controller">
		제목 : <input type="text" name="title" placeholder="제목을 입력하세요." required><br>
		내용 : <input type="text" name="body" placeholder="내용을 입력하세요." required><br>
		<input type="hidden" name="action" value="doAdd">
		<input type="submit">
	</form>
</body>
</html>