<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>안전먹거리</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="./css/css.css">

<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR|Roboto+Slab&display=swap"
	rel="stylesheet">
<style>
body {
	font-family: 'Roboto Slab', 'Noto Sans KR';
}
</style>
</head>
<body>

	<jsp:include page="../header.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row">
			<jsp:include page="../side.jsp"></jsp:include>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<h1 class="sub-header">공지사항</h1>
				<div class="table-responsive">
					<table class="table table-responsed">
						<tbody>
							<tr>
								<td>번호</td>
								<td>${detailboard.no}</td>
							</tr>
							<tr>
								<td>작성일자</td>
								<td>${detailboard.regdate}</td>
							</tr>
							<tr>
								<td>작성자</td>
								<td>${detailboard.id}</td>
							</tr>
							<tr>
								<td>제목</td>
								<td>${detailboard.title}</td>
							</tr>
							<tr>
								<td>내용</td>
								<td>${detailboard.contents}</td>
							</tr>

						</tbody>
					</table>
				</div>

				<div>
<!-- 					<input type="button" class="btn btn-default" value="글쓰기" id="write" /> -->
<!-- 					<input type="button" class="btn btn-default" value="돌아가기" id="back" /> -->
<%-- 					<c:if test="${login ==detailboard.id }"> --%>
<!-- 						<input type="button" class="btn btn-default" value="수정하기" -->
<!-- 							id="update" /> -->
<!-- 						<input type="button" class="btn btn-default" value="삭제하기" -->
<!-- 							id="delete" />	 -->
<%-- 					</c:if> --%>
					<span><a href="/insertBoard">글쓰기</a></span> <span><a
						href="/listBoard">돌아가기</a></span>
					<c:if test="${login ==detailboard.id }">
						<span><a href="/updateBoard?no=${detailboard.no }">수정하기</a></span>
						<span><a href="/deleteBoard?no=${detailboard.no }">삭제하기</a></span>
					</c:if>
				</div>
				<jsp:include page="../footer.jsp"></jsp:include>

			</div>
		</div>
	</div>

</body>

<script>
	let detail = (no) => {
		console.log(no);
	}

</script>
</html>