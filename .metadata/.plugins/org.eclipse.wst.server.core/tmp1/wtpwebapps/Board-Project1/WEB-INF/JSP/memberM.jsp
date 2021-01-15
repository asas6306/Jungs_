<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/header.jsp" />

<table border="1" class="table">
<caption><h2>회원 관리 페이지</h2></caption>
	<tr>
		<th>회원번호</th>
		<th>아이디</th>
		<th>닉네임</th>
		<th>상태</th>
		<th>권한</th>
	</tr>
	<c:forEach var="member" items="${am}">
		<tr>
			<td>${member.userid}</td>
			<td><a href="/Board-Project1/member.do?action=memberMpage&uid=${member.userid}">${member.ID}</a></td>
			<td>${member.nickname}</td>
			<td>${member.state}</td>
			<td>${member.mng}</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="뒤로가기" onclick="history.back()">
			</td>
		</tr>
	</c:forEach>
</table>

<jsp:include page="/layout/footer.jsp" />