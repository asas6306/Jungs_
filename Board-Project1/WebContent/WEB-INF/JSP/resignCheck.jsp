<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp" />

<table class="table">
	<caption>
		<h3>회원탈퇴</h3>
	</caption>
	<tr>
		<th>정말로 탈퇴 하시겠습니까?</th>
	</tr>
	<tr>
		<td width="300px" align="center"><input type="button" value="예"
			onclick="location.href='/Board-Project1/member.do?action=resign&uid=${m.uid}'">
			<input type="button" value="아니요"
			onclick="location.href='/Board-Project1/member.do?action=mypage'">
		</td>
	</tr>

</table>

<jsp:include page="/layout/footer.jsp" />