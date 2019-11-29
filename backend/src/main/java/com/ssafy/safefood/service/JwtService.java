package com.ssafy.safefood.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ssafy.safefood.dto.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtService {
	
	@Value("MYSALT")
	private String salt;
	
	@Value("5")
	private Long expiremin;
	
	public String create(final User user) {
		log.trace("time: {}", expiremin);
		final JwtBuilder builder = Jwts.builder();
		
		builder.setHeaderParam("typ", "JWT");//토큰의 타입으로 고정 값
		
		builder.setSubject("로그인토큰")//토큰 제목 설정
		.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expiremin))
		.claim("User", user).claim("second", "더 담고 싶은 내용");//담고싶은 내용 설정
		
		//secret key를 이용한 암호화
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		
		//마지막 직렬화 처리(자바 시스템 내부에서 사용되는 Object 또는 Data를 외부의 자바시스템에서도 사용할 수 있도록
		//              byte형태로 데이터를 변환하는 기술)
		final String jwt = builder.compact();
		log.debug("토큰 발행 : {}", jwt);
		return jwt;
	}
	
	public void checkValid(final String jwt) {
		log.trace("토큰 점검 : {}", jwt);
		Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
	}
	
	public Map<String, Object> get(final String jwt){
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
		
		log.trace("claims : {}", claims);
		return claims.getBody();
	}
	
	public Object getString(final String jwt){
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
		
		log.trace("claims : {}", claims);
		return claims.getBody().get("User");
	}
}
