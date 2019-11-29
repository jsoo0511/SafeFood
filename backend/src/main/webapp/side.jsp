<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<c:if test="${empty login}">
			<li>
				<div class="profile">
					<div class="profilephoto"></div>
					<div class="profiletext">
						<a href="member/login" style="text-decoration:none;color:#047300"><span><br>환영합니다<br><b>로그인</b></span>
						</a>
					</div>

				</div>
			</li>
		</c:if>
		<c:if test="${!empty login}">
			<li>
				<div class="profile">
					<div class="profilephoto"></div>
					<div class="profiletext"><b><span class="">
						${login}<br> <a href="member/modify" style="text-decoration:none;color:#047300">정보수정</a><br><a
							href="member/logout" style="text-decoration:none;color:#047300">로그아웃 </a></span></b>
					</div>
				</div>
			</li>

		</c:if>
	</ul>

	<c:choose>
		<c:when test="${pagenow == 'main'}">
			<ul class="nav nav-sidebar">
				<li class="active"><a href="main">메인 <span class="sr-only">(current)</span></a></li>
				<li><a href="foodlist">영양정보</a></li>
				<li><a href="myeatlist">내 영양정보</a></li>
			</ul>
			<ul class="nav nav-sidebar">
				<li><a href="listBoard">공지사항</a></li>
			</ul>
		</c:when>
		<c:when test="${pagenow == 'foodlist'}">
			<ul class="nav nav-sidebar">
				<li><a href="main">메인</a></li>
				<li class="active"><a href="foodlist">영양정보 <span
						class="sr-only">(current)</span></a></li>
				<li><a href="myeatlist">내 영양정보</a></li>
			</ul>
			<ul class="nav nav-sidebar">
				<li><a href="listBoard">공지사항</a></li>
			</ul>
		</c:when>
		<c:when test="${pagenow == 'myeatlist'}">
			<ul class="nav nav-sidebar">
				<li><a href="main">메인</a></li>
				<li><a href="foodlist">영양정보</a></li>
				<li class="active"><a href="myeatlist">내 영양정보 <span
						class="sr-only">(current)</span></a></li>
			</ul>
			<ul class="nav nav-sidebar">
				<li><a href="listBoard">공지사항</a></li>
			</ul>
		</c:when>
		<c:when test="${pagenow == 'notice'}">
			<ul class="nav nav-sidebar">
				<li><a href="main">메인</a></li>
				<li><a href="foodlist">영양정보</a></li>
				<li><a href="myeatlist">내 영양정보</a></li>
			</ul>
			<ul class="nav nav-sidebar">
				<li class="active"><a href="listBoard">공지사항 <span
						class="sr-only">(current)</span></a></li>
			</ul>
		</c:when>
		<c:otherwise>
			"오류"
		</c:otherwise>
	</c:choose>


	${nowpage}
</div>