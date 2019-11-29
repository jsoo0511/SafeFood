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
				<form id="fm" method="post" class="login100-form validate-form" autocomplete="off">
					<span class="login100-form-title p-b-59"> User Info </span>

					<div class="wrap-input100 validate-input"
						data-validate="Name is required">
						<span class="label-input100">아이디</span> <input class="input100"
							type="text" name="id" placeholder="ID" value="${member.id}"
							readonly="readonly"> <span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input"
						data-validate="Valid email is required: ex@abc.xyz">
						<span class="label-input100">이메일</span> <input class="input100"
							type="text" name="email" placeholder="Email"
							value="${member.email }"> <span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input"
						data-validate="Username is required">
						<span class="label-input100">이름</span> <input class="input100"
							type="text" name="name" placeholder="Username"
							value="${member.name }"> <span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input"
						data-validate="Username is required">
						<span class="label-input100">전화번호</span> <input class="input100"
							type="text" name="phone" placeholder="Phone"
							value="${member.phone }"> <span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input"
						data-validate="Password is required">
						<span class="label-input100">비밀번호</span> <input class="input100"
							type="text" name="pw" placeholder="Password"
							value="${member.pw }"> <span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input">
						<span class="label-input100">알레르기</span> <span><br></span>
						<div class="label-input100">
							<div style="height:10px"></div>

							<table class="table">
								<tr>
									<td><input id="대두" name="allergy" value="0"
										type="checkbox"><label for="0">대두</label></td>
									<td><input id="땅콩" name="allergy" value="1"
										type="checkbox"><label for="1">땅콩</label></td>
									<td><input id="우유" name="allergy" value="2"
										type="checkbox"><label for="2">우유</label></td>
									<td><input id="게" name="allergy" value="3" type="checkbox"><label
										for="3">게</label></td>
								</tr>
								<tr>
									<td><input id="새우" name="allergy" value="4"
										type="checkbox"><label for="4">새우</label></td>
									<td><input id="참치" name="allergy" value="5"
										type="checkbox"><label for="5">참치</label></td>
									<td><input id="연어" name="allergy" value="6"
										type="checkbox"><label for="6">연어</label></td>
									<td><input id="쑥" name="allergy" value="7" type="checkbox"><label
										for="7">쑥</label></td>
								</tr>
								<tr>
									<td><input id="소고기" name="allergy" value="8"
										type="checkbox"><label for="8">소고기</label></td>
									<td><input id="닭고기" name="allergy" value="9"
										type="checkbox"><label for="9">닭고기</label></td>
									<td><input id="돼지고기" name="allergy" value="10"
										type="checkbox"><label for="10">돼지고기</label></td>
									<td><input id="복숭아" name="allergy" value="11"
										type="checkbox"><label for="11">복숭아</label></td>
								</tr>
								<tr>
									<td><input id="민들레" name="allergy" value="12"
										type="checkbox"><label for="12">민들레</label></td>
									<td><input id="계란흰자" name="allergy" value="13"
										type="checkbox"><label for="13">계란흰자</label></td>
									<td></td>
									<td></td>
								</tr>
							</table>
							<!-- "대두", "땅콩", "우유", "게", "새우", "참치", "연어", "쑥", "소고기", "닭고기", "돼지고기", "복숭아", "민들레", "계란흰자" -->


						</div>
					</div>

					<div class="container-login100-form-btn">
						<button type="button" onclick="updatemember()"
							class="btn btn-success mybutton" id="update">정보수정</button>
						<button type="button" onclick="deletemember()"
							class="btn btn-success	 mybutton" id="delete">회원탈퇴</button>

						<!-- 						<a href="#" -->
						<!-- 							class="dis-block txt3 hov1 p-r-30 p-t-10 p-b-10 p-l-30"> Sign -->
						<!-- 							in <i class="fa fa-long-arrow-right m-l-5"></i> -->
						<!-- 						</a> -->
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script>
	function updatemember() {
		console.log("update");
		let $form = $("#fm");
		$form.attr("action", "modify");
		$form.submit();
	}

	function deletemember() {
		let $form = $("#fm");
		$form.attr("action", "withdraw");
		$form.submit();
	}

	function setAllergy() {
		let temp = '${member.allergy}';
		console.log(temp);
		let split_temp = temp.split('/');
		console.log(split_temp);

		for ( var i in split_temp) {
			console.log(split_temp[i]);
			$("#" + split_temp[i]).attr("checked", true);
		}
	}

	setAllergy();
</script>
</html>