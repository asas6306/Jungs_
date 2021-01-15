<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp"%>

<table border="1" class="table">
	<caption>
		<h3>소개</h3>
	</caption>
	<tr>
		<th>이름</th>
		<td align="center">박정원</td>
		<th>생년월일</th>
		<td align="center">1995. 07. 20.</td>
		<td rowspan="7"><img alt="사진"
			src="/Board-Project1/img/profile.jpg" height="200" width="160"></td>
		<!-- /Board-Project1/img/profile.png -->
	</tr>
	<tr>
		<th>연락처</th>
		<td align="center">010-3372-3049</td>
		<th>이메일</th>
		<td align="center">asas6302@naver.com</td>
	</tr>
	<tr>
		<th>주소지</th>
		<td>충청남도 계룡시</td>
		<th>학력</th>
		<td>목원대학교 전자공학과</td>
	</tr>
	<tr>
		<th rowspan="3">경력</th>
		<td colspan="3" align="center">제 2사단 포병연대 운용소대장(18개월)</td>
	</tr>
	<tr>
		<td colspan="3" align="center">제 21사단 정보통신대대 운용소대장(6개월)</td>
	</tr>
	<tr>
		<td colspan="3" align="center">SBS아트아카데미(5개월)</td>
	</tr>
	<tr>
		<th>취미</th>
		<td align="center">게임, 드라마</td>
		<th>특기</th>
		<td align="center">참기, 집중하기</td>
	</tr>
</table>

<%@ include file="/layout/footer.jsp"%>