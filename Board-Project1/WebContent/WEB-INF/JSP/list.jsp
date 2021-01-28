<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/layout/header.jsp"%>

<table border="1" class="table">
	<caption>
		<h3>게시판</h3>
	</caption>
	<tr>
		<th>번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>작성일</th>
		<th><c:choose>
				<c:when test="${order2==1}">
					<a href="${listStr}0&order=like">좋아요↑</a>
				</c:when>
				<c:otherwise>
					<a href="${listStr}0&order=liked">좋아요↓</a>
				</c:otherwise>
			</c:choose></th>
		<th><c:choose>
				<c:when test="${order2==2}">
					<a href="${listStr}0&order=hit">조회수↑</a>
				</c:when>
				<c:otherwise>
					<a href="${listStr}0&order=hitd">조회수↓</a>
				</c:otherwise>
			</c:choose></th>
	</tr>
	<c:forEach var="a" items="${aa}">
		<tr>
			<td align="center">${a.aid}</td>
			<td width="100px" align="center">${a.nickname}</td>
			<td width="150px"><a
				href="/Board-Project1/article.do?action=detail&aid=${a.aid}&uid=${m.uid}&hit=true&pointer=${pointer}">${a.title}</a>
			</td>
			<td>${a.regDate}</td>
			<td align="center">${a.like}</td>
			<td align="center">${a.hit}</td>
			<c:choose>
				<c:when test="${a.uid==m.uid || mng}">
					<td><input type="button" value="X"
						onclick="location.href='/Board-Project1/article.do?action=delete&aid=${a.aid}'">
					</td>
				</c:when>
			</c:choose>

		</tr>
	</c:forEach>
	<tr>
		<c:choose>
			<c:when test="${m.state}">
				<td colspan="6" align="right"><input type="button" value="글쓰기"
					onclick="location.href='/Board-Project1/article.do?action=add'">
				</td>
			</c:when>
		</c:choose>
	</tr>
	<tr>
		<td colspan="6" align="center"><c:choose>
				<c:when test="${!leftend}">
					<a href="${listStr}${pointer-5}&move=left${order1}">이전</a>&nbsp
				</c:when>
			</c:choose> <c:forEach var="p" begin="${begin}" end="${end}">
				<c:choose>
					<c:when test="${pointer==p}">
						<b>${p}</b>&nbsp
				</c:when>
					<c:otherwise>
						<a href="${listStr}${p}${order1}">${p}</a>&nbsp
				</c:otherwise>
				</c:choose>
			</c:forEach> <c:choose>
				<c:when test="${!rightend}">
					<a href="${listStr}${pointer+5}&move=right${order1}">다음</a>
				</c:when>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="6" align="center">
			<form action="/Board-Project1/article.do">
				<input type="hidden" name="action" value="list"> <select
					name="searchDate">
					<option value="1">전체</option>
					<option value="2">1일</option>
					<option value="3">1주</option>
					<option value="4">1달</option>
					<option value="5">1달</option>
				</select> <select name="searchIndex">
					<option value="1">제목+내용</option>
					<option value="2">제목</option>
					<option value="3">내용</option>
					<option value="4">작성자</option>
				</select> <input type="text" name="searchBody"> <input type="submit"
					value="검색">
			</form>
		</td>
	</tr>
</table>

<%@ include file="/layout/footer.jsp"%>