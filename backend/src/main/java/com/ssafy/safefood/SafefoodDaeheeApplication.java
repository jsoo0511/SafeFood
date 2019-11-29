package com.ssafy.safefood;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.safefood.controller.JwtInterceptor;

@SpringBootApplication
public class SafefoodDaeheeApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(SafefoodDaeheeApplication.class, args);
	}

	@Autowired
	private JwtInterceptor jwtInterceptor;
	
	@Override    //addPathpatterns -> 토큰 불가능, exclude... -> filter느낌, 어떤 애가 토근을 받을지 결정하는 거
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/MYEAT").excludePathPatterns(Arrays.asList("/**"));
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "OPTIONS", "PUT");
	}
}
