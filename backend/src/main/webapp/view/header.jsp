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
				<div class="form-group">
					<div class="icon-addon addon-md">
						<input id="stype" name="stype" type="hidden" value="all" /> <input
							id="word" type="text" class="form-control" autocomplete="off" placeholder="Search"
							name="word"> <label for="search"
							class="glyphicon glyphicon-search" title="search"></label>
					</div>
				</div>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a id="cat" href="#"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-expanded="false">검색조건<span class="caret"></span></a>
					<ul id="drops" class="dropdown-menu" role="menu"
						aria-labelledby="dropdownMenu">
						<li class="c1" id="all"><a class="ca" href="#" id="all">전체</a></li>
						<li class="c2" id="name"><a class="ca" href="#" id="name">상품명</a></li>
						<li class="c3" value="maker"><a class="ca" href="#"
							id="maker">제조사</a></li>
						<li class="c4"><a class="ca" href="#" id="material">재료</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>

<div id="stext">1</div>


<script>
	let search="전체";

	$(".c1").on("click", ()=>{
		$("#cat").html('전체<span class="caret"></span>');
		search = "전체";
		$("#stype").val("all");
		console.log("${food}");
	});
	$(".c2").on("click", ()=>{
		$("#cat").html('상품명<span class="caret"></span>');
		search = "상품명";
		$("#stype").val("name");
	});
	$(".c3").on("click", ()=>{
		$("#cat").html('제조사<span class="caret"></span>');
		search = "제조사";
		$("#stype").val("maker");
	});
	$(".c4").on("click", ()=>{
		$("#cat").html('재료<span class="caret"></span>');
		search = "재료";
		$("#stype").val("material");
	});

	let oldVal="";
	$("#word").on('propertychange change keyup paste input', function(){
		var currentVal=$(this).val();
		if(currentVal==oldVal){
			return;
		}
		oldVal=currentVal;
		console.log(currentVal);
		$.ajax({
			url:"foodbestsearch",
			data:"name="+currentVal,
			success:(data)=>{
				$("#stext").html(data);
				$("#stext").show();
				if( currentVal==""){
					$("#stext").hide();
				}
			},
			error:()=>{
				console.log("실패");
			}
		});
	})
</script>
