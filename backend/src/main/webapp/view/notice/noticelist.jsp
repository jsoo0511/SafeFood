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
	href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Roboto+Slab&display=swap"
	rel="stylesheet">
<style>
body {
	font-family: 'Roboto Slab', 'Nanum Gothic';
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
					<table class="table table-striped">
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>테스트</td>
								<td>SSAFY</td>
								<td>20</td>
							</tr>

<%-- 							<c:forEach items="${foodlist}" var="l"> --%>
<!-- 								<tr> -->
<%-- 									<td>${l.code}</td> --%>
<%-- 									<td>${l.name}</td> --%>
<%-- 									<td>${l.maker}</td> --%>
<%-- 									<td>${l.frequency}</td> --%>
<!-- 								</tr> -->
<%-- 							</c:forEach> --%>

						</tbody>
					</table>
				</div>

				<jsp:include page="../footer.jsp"></jsp:include>

			</div>
		</div>
	</div>

</body>
</html>