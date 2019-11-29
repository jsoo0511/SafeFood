package com.ssafy.safefood.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.safefood.dto.Food;
import com.ssafy.safefood.dto.FoodPageBean;
import com.ssafy.safefood.service.FoodService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
public class MainRestController implements ErrorController{

	@Autowired
	FoodService fs;

	// 메인 페이지
	@GetMapping("/main")
	@ApiOperation("main페이지, rest로 foodlist도 data로 바로 전달해줘야됨")
	public ResponseEntity<Map<String, Object>> showIndex() {
		List<Food> list = fs.searchAll(new FoodPageBean());
		return response(list,HttpStatus.OK,true);//foodlist전달
	}

	// 검색창 자동완성
	@GetMapping("/mainSearch")
	@ApiOperation("검색창 자동완성 stype으로 name, maker, material, all중 하나 선택")
	public ResponseEntity<Map<String, Object>> foodbest(@RequestParam String stype, @RequestParam String name) {
		if (stype == null) {
			stype = "all";
		}

		List<Food> f = fs.searchBest(new FoodPageBean(stype, name, null, null));
		return response(f,HttpStatus.OK,true);
	}

	@ExceptionHandler(RuntimeException.class)
	// @ExceptionHandler 에서는 Model을 사용할 수 없다....!
	public ModelAndView handleError() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", "서버 오류 발생");
		mav.setViewName("Error");
		return mav;
	}	
	
	@Override
	public String getErrorPath() {
		return "/error";	//에러 발생시 호출될 페이지
	}
	
	@RequestMapping("/error")
	@ApiOperation("error핸들러")
	public ResponseEntity<Map<String, Object>> handlerError(HttpServletRequest req) {
		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if(status!=null) {
			Integer code = Integer.parseInt(status.toString());
			if(code == 404) {
				return response("찾는페이지가 없습니다.",HttpStatus.NOT_FOUND,false);
			}else {
				return response("잠시 후 다시 시도해주세요.",HttpStatus.CONFLICT,false);
			}
		}
		return response("Error",HttpStatus.CONFLICT,false);
	}
	
	private ResponseEntity<Map<String, Object>> response(Object data, HttpStatus httpStatus, boolean status) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("data", data);
		resultMap.put("status", status);

		// 상태와 함께 Map반환
		return new ResponseEntity<>(resultMap, httpStatus);
	}
}
