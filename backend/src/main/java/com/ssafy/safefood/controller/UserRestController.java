//package com.ssafy.safefood.controller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ssafy.safefood.dto.User;
//import com.ssafy.safefood.service.JwtService;
//import com.ssafy.safefood.service.UserService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@RestController
//@RequestMapping("/api")
//@Slf4j
//public class UserRestController {
//
//	@Autowired
//	private JwtService jwtService;
//	
//	@Autowired
//	private UserService userService;
//	
//	@PostMapping("/user/signin")
//	public ResponseEntity<Map<String, Object>> signin(@RequestBody User user){
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = null;
//		try {
//			User loginUser = userService.signin(user.getEmail(), user.getPassword());
//			
//			String token = jwtService.create(loginUser);
//			
//			resultMap.put("auth_token",  token);
//			resultMap.put("status",  true);
//			resultMap.put("data", "loginUser");
//			status = HttpStatus.ACCEPTED;
//		} catch (RuntimeException e) {
//			// TODO: handle exception
//			log.error("로그인 실패", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		return new ResponseEntity<Map<String,Object>>(resultMap, status);
//	}
//	
//	@GetMapping("/info")
//	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req){
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = null;
//		try {
//			String info = userService.getServerInfo();
//			
//			resultMap.putAll(jwtService.get(req.getParameter("auth_token")));
//			resultMap.put("status", true);
//			status = HttpStatus.ACCEPTED;
//		} catch (Exception e) {
//			log.error("정보조회 실패", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
//}
