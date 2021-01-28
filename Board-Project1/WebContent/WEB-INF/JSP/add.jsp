<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp"%>


<form action="/Board-Project1/article.do">
	<table border="1" class="table">
		<input type="hidden" name="action" value="doAdd" method="post">
		<caption>
			<h3>게시물 작성</h3>
		</caption>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" required></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="22" name="body" required></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="hidden" name="uid"
				value="${m.uid}"> <input type="submit" value="작성하기">
			</td>
		</tr>
	</table>
</form>

<%@ include file="/layout/footer.jsp"%>