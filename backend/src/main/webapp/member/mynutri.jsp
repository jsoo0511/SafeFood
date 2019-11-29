<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>안전먹거리</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>

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


				<c:if test="${empty login}">
					<div id="detailimg" class="row placeholders">
						<div id="innerdivback"></div>
						<div id="innerdiv">
							<div id="verticalbottom">
								<h1 class="page-header">
									What We <span class="">Provide</span>
								</h1>
								<h3>로그인 후 이용해주세요.</h3>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${!empty login}">
					<h1 class="sub-header">나의 영양 정보</h1>
					<c:if test="${empty nutri}">
						섭취한 음식이 없습니다.					
					</c:if>
					<c:if test="${!empty nutri}">
						<div class="table-responsible">
							<div class="row">
								<div class="col-md-7">
									<canvas id="myDoughnutChart"></canvas>
								</div>
								<div class="col-md-5">
									<table id="nutri_info" class="table table-hover">
										<tbody>

										</tbody>
									</table>
									<!-- 표들어갈곳 -->
								</div>
							</div>


							<br>
							<h1>
								<span class="high_light">섭취 내역</span>
							</h1>
							<br>
							<!-- 							<div class="container"> -->
							<form action="deletemyeat" method="post">
								<div class="row">
									<div class="col-md-12">
										<table id="foodlist2"
											class="table table-hover table-responsible">
											<tbody>
												<tr>
													<td><span class="high_light"><b>번호</b></span></td>
													<td><span class="high_light"><b>상품코드</b></span></td>
													<td><span class="high_light"><b>상품명</b></span></td>
													<td><span class="high_light"><b>수량</b></span></td>
													<td width="40px"></td>
												</tr>

												<!-- 	private int idx; -->
												<!-- 	private String id; -->
												<!-- 	private int code; -->
												<!-- 	private int amount;							 -->
												<c:forEach items="${nutri}" var="nutri">
													<tr>
														<td>${nutri.idx}</td>
														<td>${nutri.code}</td>
														<td>${nutri.foodname}</td>
														<td>${nutri.amount}</td>
														<td><input name="del" type="checkbox" value="${nutri.idx}"/></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<input class="btn btn-light" type="submit" value="삭제하기" />
										<!-- 									</div> -->
									</div>
								</div>
							</form>
						</div>

					</c:if>
				</c:if>
				<jsp:include page="../footer.jsp"></jsp:include>

			</div>
		</div>
	</div>

</body>
<script>
	function graphs() {
		let SERVING_WT = "0";
		let NUTR_CONT1 = "${summary.calory}";
		let NUTR_CONT2 = "${summary.carbo}";
		let NUTR_CONT3 = "${summary.protein}";
		let NUTR_CONT4 = "${summary.fat}";
		let NUTR_CONT5 = "${summary.sugar}";
		let NUTR_CONT6 = "${summary.natrium}";
		let NUTR_CONT7 = "${summary.chole}";
		let NUTR_CONT8 = "${summary.fattyacid}";
		let NUTR_CONT9 = "${summary.transfat}";
		let BGN_YEAR = "안쓰는 데이터";
		let ANIMAL_PLANT = "${summary.maker}";
		let ctx = $('#myDoughnutChart');
		let names = "${login}";
		let myDoughnutChart = new Chart(ctx,
				{
					type : 'bar',
					data : {
						labels : [ '칼로리', '탄수화물', '단백질', '지방', '당류', '나트륨',
								'콜레스테롤', '포화지방산', '트랜스지방' ],
						datasets : [ {
							label : names + '님의 총 섭취정보',
							data : [ NUTR_CONT1, NUTR_CONT2, NUTR_CONT3,
									NUTR_CONT4, NUTR_CONT5, NUTR_CONT6,
									NUTR_CONT7, NUTR_CONT8, NUTR_CONT9 ],
							backgroundColor : [ 'rgba(255, 99, 132, 1)',
									'rgba(54, 162, 235, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(75, 192, 192, 1)',
									'rgba(153, 102, 255, 1)',
									'rgba(255, 159, 64, 1)',
									'rgba(186, 253, 255, 1)',
									'rgba(255, 224, 224, 1)',
									'rgba(209, 0, 129, 1)', ],
							borderColor : [ 'rgba(255, 99, 132, 1)',
									'rgba(54, 162, 235, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(75, 192, 192, 1)',
									'rgba(153, 102, 255, 1)',
									'rgba(255, 159, 64, 1)',
									'rgba(186, 253, 255, 1)',
									'rgba(255, 224, 224, 1)',
									'rgba(209, 0, 129, 1)', ],
							borderWidth : 1
						} ]
					},

				// These labels appear in the legend and in the tooltips when hovering different arcs    
				});

		let trs = document.createElement("tr");
		let trs2 = document.createElement("tr");
		let trs3 = document.createElement("tr");
		let trs4 = document.createElement("tr");
		let trs5 = document.createElement("tr");
		let trs6 = document.createElement("tr");
		let trs7 = document.createElement("tr");
		let trs8 = document.createElement("tr");
		let trs9 = document.createElement("tr");
		let trs10 = document.createElement("tr");

		var tds20 = document.createElement("td");
		tds20.innerHTML = "<span class='high_light'><b>총용량</b></span>";

		//?
		tds20.width = "50%";
		var tds21 = document.createElement("td");
		tds21.innerHTML = SERVING_WT;

		var tds = document.createElement("td");
		tds.innerHTML = "<span class='high_light'><b>칼로리</b></span>";
		var tds1 = document.createElement("td");
		tds1.innerHTML = NUTR_CONT1;

		var tds2 = document.createElement("td");
		tds2.innerHTML = "<span class='high_light'><b>탄수화물</b></span>";
		var tds3 = document.createElement("td");
		tds3.innerHTML = NUTR_CONT2;

		var tds4 = document.createElement("td");
		tds4.innerHTML = "<span class='high_light'><b>단백질</b></span>";
		var tds5 = document.createElement("td");
		tds5.innerHTML = NUTR_CONT3;

		var tds6 = document.createElement("td");
		tds6.innerHTML = "<span class='high_light'><b>지방</b></span>";
		var tds7 = document.createElement("td");
		tds7.innerHTML = NUTR_CONT4;

		var tds8 = document.createElement("td");
		tds8.innerHTML = "<span class='high_light'><b>당류</b></span>";
		var tds9 = document.createElement("td");
		tds9.innerHTML = NUTR_CONT5;

		var tds10 = document.createElement("td");
		tds10.innerHTML = "<span class='high_light'><b>나트륨</b></span>";
		var tds11 = document.createElement("td");
		tds11.innerHTML = NUTR_CONT6;

		var tds12 = document.createElement("td");
		tds12.innerHTML = "<span class='high_light'><b>콜레스테롤</b></span>";
		var tds13 = document.createElement("td");
		tds13.innerHTML = NUTR_CONT7;

		var tds14 = document.createElement("td");
		tds14.innerHTML = "<span class='high_light'><b>포화지방산</b></span>";
		var tds15 = document.createElement("td");
		tds15.innerHTML = NUTR_CONT8;

		var tds16 = document.createElement("td");
		tds16.innerHTML = "<span class='high_light'><b>트랜스지방</b></span>";
		var tds17 = document.createElement("td");
		tds17.innerHTML = NUTR_CONT9;

		trs.appendChild(tds);
		trs.appendChild(tds1);
		trs2.appendChild(tds2);
		trs2.appendChild(tds3);
		trs3.appendChild(tds4);
		trs3.appendChild(tds5);
		trs4.appendChild(tds6);
		trs4.appendChild(tds7);
		trs5.appendChild(tds8);
		trs5.appendChild(tds9);
		trs6.appendChild(tds10);
		trs6.appendChild(tds11);
		trs7.appendChild(tds12);
		trs7.appendChild(tds13);
		trs8.appendChild(tds14);
		trs8.appendChild(tds15);
		trs9.appendChild(tds16);
		trs9.appendChild(tds17);
		trs10.appendChild(tds20);
		trs10.appendChild(tds21);

		$('#nutri_info').append(trs10);
		$('#nutri_info').append(trs);
		$('#nutri_info').append(trs2);
		$('#nutri_info').append(trs3);
		$('#nutri_info').append(trs4);
		$('#nutri_info').append(trs5);
		$('#nutri_info').append(trs6);
		$('#nutri_info').append(trs7);
		$('#nutri_info').append(trs8);
		$('#nutri_info').append(trs9);

		return;
	}

	graphs();
</script>
</html>