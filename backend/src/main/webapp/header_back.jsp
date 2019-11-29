<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand whitetitle" href="main">SAFEFOOD</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<form class="navbar-form navbar-right" method="post"
				action="foodlist">
<!-- 				<input type="text" class="form-control" placeholder="Search" -->
<!-- 					name="word"> -->

				<input id= "search" type="text" class="form-control" placeholder="Search" name = "word">
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a id="cat" href="#"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-expanded="false">검색조건<span class="caret"></span></a>
					<ul id="drops" class="dropdown-menu" role="menu"
						aria-labelledby="dropdownMenu">
						<li class="choose"><a href="#">전체</a></li>
						<li class="choose"><a href="#">상품명</a></li>
						<li class="choose"><a href="#">제조사</a></li>
						<li class="choose"><a href="#">재료</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>

<script>
	$(".choose>a").on("click", ()=>{
		let temp = $(this).text();
		console.log(temp);
		alert(temp);
	})

</script>
