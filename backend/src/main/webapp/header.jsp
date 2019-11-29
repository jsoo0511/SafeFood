<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div id="stext">1</div>
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
						<li class="choose" id="all"><a class="ca" href="#" id="all">전체</a></li>
						<li class="choose" id="name"><a class="ca" href="#" id="name">상품명</a></li>
						<li class="choose" value="maker"><a class="ca" href="#"
							id="maker">제조사</a></li>
						<li class="choose"><a class="ca" href="#" id="material">재료</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>



<script>
	let search="전체";
	
	$(".choose > a").on("click", function(){
		let temp = $(this).text();
		$("#cat").html(temp + '<span class="caret"></span>');
		search = temp;
		console.log(temp);
		
		switch(temp){
		case "상품명":
			$("#stype").val("name");
			break;
		case "제조사":
			$("#stype").val("maker");
			break;
		case "재료":
			$("#stype").val("material");
			break;
		default:
			$("#stype").val("all");			
		}
	})

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
			data:{"stype": search, "name":currentVal},
			success:(data)=>{
				$("#stext").html(data);
				$("#stext").show();
				if( currentVal==""){
					$("#stext").hide();
				}else{
					$("#stext").show();					
				}
			},
			error:()=>{
				console.log("실패");
			}
		});
	})
	
	$(document).on("click", ".best", function(){
		console.log($(this).text());
		$("#word").val($(this).text());
		$("#stext").hide();
	})
</script>
