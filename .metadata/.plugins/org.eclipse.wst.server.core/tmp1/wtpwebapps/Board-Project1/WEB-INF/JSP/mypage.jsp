<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp" />

<table border="1" class="table">
	<caption>
		<h2>마이 페이지</h2>
	</caption>
	<tr>
		<th width="100px">아이디</th>
		<td width="150px" align="center">${m.ID}</td>
	</tr>
	<tr>
		<th>닉네임</th>
		<td align="center">${m.nickname}</td>
	</tr>
	<tr>
		<th>등급</th>
		<td align="center">${m.mng ? "관리자" : "일반회원"}</td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="button" value="수정"
			onclick="location.href='/Board-Project1/member.do?action=PWcheck&page=update'">
			<input type="button" value="회원탈퇴"
			onclick="location.href='/Board-Project1/member.do?action=PWcheck&page=resign'">
		</td>
	</tr>
</table>

<jsp:include page="/layout/footer.jsp" />