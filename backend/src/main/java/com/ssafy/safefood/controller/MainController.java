//package com.ssafy.safefood.controller;
//
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.ssafy.safefood.dto.Food;
//import com.ssafy.safefood.dto.FoodPageBean;
//import com.ssafy.safefood.service.FoodService;
//import com.ssafy.safefood.service.MemberService;
//
//@Controller
//public class MainController implements ErrorController{
//
//	@Autowired
//	FoodService fs;
//
//	// 메인 페이지
//	@GetMapping("/main")
//	public String showIndex(HttpSession session) {
//		List<Food> list = fs.searchAll(new FoodPageBean());
//		session.setAttribute("foodlist", list);
//		session.setAttribute("pagenow", "main");
//		return "main";
//	}
//
//	// 검색창 자동완성
//	@GetMapping("/foodbestsearch")
//	public String foodbest(HttpServletRequest request) {
//		String stype = request.getParameter("stype");
//		String name = request.getParameter("name");
//		System.out.println(stype+name);
//		HttpSession session = request.getSession();
//		if (stype == null) {
//			stype = "all";
//		}
//
//		List<Food> f = fs.searchBest(new FoodPageBean(stype, name, null, null));
//		session.setAttribute("bestlist", f);
//		return "food/foodbestsearch";
//	}
//
//	@ExceptionHandler(RuntimeException.class)
//	// @ExceptionHandler 에서는 Model을 사용할 수 없다....!
//	public ModelAndView handleError() {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("message", "서버 오류 발생");
//		mav.setViewName("Error");
//		return mav;
//	}	
//	
//	@Override
//	public String getErrorPath() {
//		return "/error";	//에러 발생시 호출될 페이지
//	}
//	
//	@RequestMapping("/error")
//	public String handlerError(HttpServletRequest req, Model model) {
//		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//		if(status!=null) {
//			Integer code = Integer.parseInt(status.toString());
//			if(code == 404) {
//				model.addAttribute("message", "찾는 페이지가 없습니다.");
//			}else {
//				model.addAttribute("message", "잠시 후 다시 시도해주세요.");			
//			}
//		}
//		
//		return "Error";
//	}
//}
