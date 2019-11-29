package com.ssafy.safefood.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.Handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.safefood.service.JwtService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor{
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		String token = request.getParameter("auth_token");
		if(token != null && token.length()>0) {
			// 유효 토큰일 경우
			jwtService.checkValid(token);
			log.trace("토큰 사용 가능 : {}", token);
			return true;
		}else {
			throw new RuntimeException("인증 토큰이 없습니다.");
		}
	}
}
