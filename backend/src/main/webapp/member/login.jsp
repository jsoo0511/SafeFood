<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN - SAFEFOOD</title>
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

<link rel="stylesheet" type="text/css" href="../css/util.css">
<link rel="stylesheet" type="text/css" href="../css/main.css">
<!-- <link rel="stylesheet" href="../css/css.css"> -->
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR|Roboto+Slab&display=swap"
	rel="stylesheet">
<style>
body {
	font-family: 'Roboto Slab', 'Noto Sans KR';
	background-image: url("../img/back.jpg");
}
</style>

</head>
<body style="background-color: #999999;">

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-50 p-r-50 p-t-72 p-b-50">
				<form class="login100-form validate-form" action="login" method="post" autocomplete="off">
					<span class="login100-form-title p-b-59"> Login </span>

					<div class="wrap-input100 validate-input"
						data-validate="Name is required">
						<span class="label-input100">아이디</span> <input class="input100"
							type="text" name="id" placeholder="ID"> <span
							class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input"
						data-validate="Password is required">
						<span class="label-input100">비밀번호</span> <input class="input100"
							type="text" name="pw" placeholder="Password"> <span
							class="focus-input100"></span>
					</div>

					<div class="container-login100-form-btn">
						<button type="submit" class="btn btn-success btn-max">Login</button>
						<button type="button" class="btn btn-success btn-max"
							onClick="location.href='./signup'">Sign Up</button>

						<!-- 						<a href="#" -->
						<!-- 							class="dis-block txt3 hov1 p-r-30 p-t-10 p-b-10 p-l-30"> Sign -->
						<!-- 							in <i class="fa fa-long-arrow-right m-l-5"></i> -->
						<!-- 						</a> <a href="#" -->
						<!-- 							class="dis-block txt3 hov1 p-r-30 p-t-10 p-b-10 p-l-30"> -->
						<!-- 							Login <i class="fa fa-long-arrow-right m-l-5"></i> -->
						<!-- 						</a> -->
					</div>
					
					<div>${message }</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>