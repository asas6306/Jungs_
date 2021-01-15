<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp" %>

<table border="1" class="table">
	<caption><h3>${a.articleid}번 게시물</h3></caption>
		<tr>
			<th>제목</th>
			<td colspan="3">${a.title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${a.nickname}</td>
			<th>작성날짜</th>
			<td>${a.date}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${a.hit}</td>
			<th>좋아요</th>
			<td>${a.like}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3">${a.body}</td>
		</tr>
		<c:choose>
			<c:when test="${a.userid == m.userid}">
				<tr>
					<td colspan="4" align="center">
						<input type="button" value="수정" onclick="location.href='/Board-Project1/article.do?action=update&aid=${a.articleid}'">
						<input type="button" value="삭제" onclick="location.href='/Board-Project1/article.do?action=delete&aid=${a.articleid}'">
					</td>
				</tr>
			</c:when>
		</c:choose>
		<!-- @@ 댓글기능 @@ -->
		<tr align="center">
			<td colspan="4" align="center">
			<table class="table">				
			<tr>
				<th colspan="3">댓글</th>
			</tr>
			<c:forEach var="c" items="${ca}">
			<tr>
				<td style="font-weight: bold;" align="center" width="100px">${c.nickname} </td>
				<c:choose>
					<c:when test="${check=='update'&&cid==c.commentid}">
					<td>
						<form action="/Board-Project1/article.do">
							<input type="hidden" name="action" value="doUpdateComment">
							<input type="text" name="body" value="${c.body}" required>
							<input type="hidden" name="cid" value="${c.commentid}">
							<input type="hidden" name="aid" value="${a.articleid}">
							<input type="submit" value="수정">
						</form>
					</td> 
					</c:when>
					<c:otherwise>
						<td style="width: 250px">${c.body} 
						<c:choose>
							<c:when test="${m.userid==c.userid}">
								<a style="font-size: 70%" href="/Board-Project1/article.do?action=updateComment&cid=${c.commentid}&aid=${a.articleid}&uid=${m.userid}">수정</a>
							</c:when>
						</c:choose>
						</td>
					</c:otherwise>
				</c:choose>
				
				<td style="font-size: 70%">${c.date} <a href="/Board-Project1/article.do?action=commentReply&cid=${c.commentid}&aid=${a.articleid}&uid=${m.userid}">답글</a></td>
				<c:choose>
					<c:when test="${m.userid==c.userid}">
						<td>
							<input type="button" value="X" onclick="location.href='/Board-Project1/article.do?action=deleteComment&cid=${c.commentid}&aid=${a.articleid}&uid=${m.userid}'">
						</td>
					</c:when>
				</c:choose>
			</tr>
				<!-- @@ 대댓글 @@ -->
			<c:forEach var="cr" items="${cra}">
				<c:choose>
					<c:when test="${cr.commentReply==c.commentid}">
						<tr style="font-size: 80%">
							<td align="right" style="font-weight: bold;">└>${cr.nickname}</td>
							<c:choose>
								<c:when test="${check=='update'&&cid==cr.commentid}">
								<td>
									<form action="/Board-Project1/article.do">
										<input type="hidden" name="action" value="doUpdateComment">
										<input type="text" name="body" value="${cr.body}" required>
										<input type="hidden" name="cid" value="${cr.commentid}">
										<input type="hidden" name="aid" value="${a.articleid}">
										<input type="submit" value="수정">
									</form>
								</td> 
								</c:when>
								<c:otherwise>
									<td>${cr.body}&nbsp
										<c:choose>
											<c:when test="${m.userid==cr.userid}">
												<a style="font-size: 85%" href="/Board-Project1/article.do?action=updateComment&cid=${cr.commentid}&aid=${a.articleid}&uid=${m.userid}">수정</a>
											</c:when>
										</c:choose>
									</td>
								</c:otherwise>
							</c:choose>
								<td style="font-size: 85%">${cr.date}</td>
							<c:choose>
								<c:when test="${m.userid==cr.userid}">
									<td>
										<input type="button" value="X" onclick="location.href='/Board-Project1/article.do?action=deleteComment&cid=${cr.commentid}&aid=${a.articleid}&uid=${m.userid}'">
									</td>
								</c:when>
							</c:choose>
						</tr>
					</c:when>
				</c:choose>
			</c:forEach>
				<c:choose>
					<c:when test="${check=='reply'&&cid==c.commentid}">
						<tr>
							<td align="center">답글</td>
							<td>
							<form action="/Board-Project1/article.do">
								<input type="hidden" name="action" value="doCommentReply">
								<input type="text" name="body" required>
								<input type="hidden" name="cid" value="${c.commentid}">
								<input type="hidden" name="aid" value="${a.articleid}">
								<input type="hidden" name="uid" value="${m.userid}">
								<input type="submit" value="입력">
							</form>
							</td>
						</tr>
					</c:when>
				</c:choose>
				</c:forEach>
				<tr>
				<form action="/Board-Project1/article.do">
					<td colspan="3" align="center">
						<input type="hidden" name="action" value="addComment">
						<input type="text" name="body" placeholder="댓글을 입력하세요." required>
						<input type="hidden" name="uid" value="${m.userid}">
						<input type="hidden" name="aid" value="${a.articleid}">
					</td>
					<td align="center">
						<input type="submit" value="입력">
					</td>
				</form>
			</tr>
		</table>
		</td>
	</tr>
	<!-- @@ 댓글기능 @@ -->
	<tr>
		<td colspan="4" align="center">
			<c:choose>
				<c:when test="${m.state}">
					<c:choose>
						<c:when test="${like}">
							<input type="button" value="좋아요 취소" onclick="location.href='/Board-Project1/article.do?action=like&uid=${m.userid}&aid=${a.articleid}&like=false'">
						</c:when>
						<c:otherwise>
							<input type="button" value="좋아요" onclick="location.href='/Board-Project1/article.do?action=like&uid=${m.userid}&aid=${a.articleid}&like=true'">
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>
			<input type="button" value="목록으로" onclick="location.href='/Board-Project1/article.do?action=list&pointer=${pointer}'">
		</td>
		<!-- history.back() 뒤로가기 -->
	</tr>
</table>

<%@ include file="/layout/footer.jsp" %>