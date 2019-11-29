package com.ssafy.safefood.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PerformanceInterceptor implements HandlerInterceptor{
	
	private static Logger logger = LoggerFactory.getLogger(PerformanceInterceptor.class);
	
	@Override // Controller 동작 전 처리
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		request.setAttribute("start", start);

		// false 리턴 시 서버 호출 되지 않음
		return true;
	}

	@Override // Controller 동작 후 처리
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// modelAndView: Controller에서 설정한 값들이 저장됨 -> 필요 시 수정 가능
		long middle = System.currentTimeMillis();
		request.setAttribute("middle", middle);
		
	}

	@Override // View까지 완전히 출력한 후
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		long end = System.currentTimeMillis();
		logger.trace("호출 : " + request.getServletPath());
		long start = (Long) request.getAttribute("start");
		long middle = (Long) request.getAttribute("middle");
		logger.trace("controller 동작 시간 : {}", middle-start);
		logger.trace("뷰 반환 시간 : {}", end-middle);  
		logger.trace("전체 동작 시간 : {}", end-start);
	}

}
