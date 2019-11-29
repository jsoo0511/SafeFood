//package com.ssafy.safefood.controller;
//
//import java.util.Arrays;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ssafy.safefood.dto.Member;
//import com.ssafy.safefood.dto.Eat;
//import com.ssafy.safefood.dto.Food;
//import com.ssafy.safefood.dto.FoodPageBean;
//import com.ssafy.safefood.service.FoodService;
//import com.ssafy.safefood.service.MemberService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Controller
//@Slf4j
//public class MemberController {
//	private String[] allergys = { "대두", "땅콩", "우유", "게", "새우", "참치", "연어", "쑥", "소고기", "닭고기", "돼지고기", "복숭아", "민들레",
//			"계란흰자" };
//
//	@Autowired
//	MemberService ms;
//
//	@Autowired
//	FoodService fs;
//	
//	static HttpSession loginSession; //login담을 세션
//
//	// 로그인페이지
//	@GetMapping("member/login")
//	public String showLogin() {
//		return "member/login";
//	}
//
//	// 로그인 처리
//	@PostMapping("/member/login")
//	public String login(@RequestParam String id, @RequestParam String pw, HttpSession session, Model model) {
//		try {
//			System.out.println(id + pw);
//			// 로그인 정보 확인 후 세션에 등록
//			Member result = ms.login(id, pw);
//
//			if (result != null) {
//				session.setAttribute("login", result.getId());
//				System.out.println("로그인 성공");
//
//				// 음식 목록 가져오기
//				List<Food> list = fs.searchAll(new FoodPageBean());
//				session.setAttribute("foodlist", list);
//
//				return "redirect:../main";
//			} else {
//				model.addAttribute("message", "ID / PW를 다시 확인해주세요");
//				return "member/login";
//			}
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//			model.addAttribute("message", "LOGIN ERROR");
//			System.out.println("로그인 실패");
//			return "member/login";
//		}
//	}
//
//	// 정보수정 페이지
//	@GetMapping("member/modify")
//	public String showModify(Model model, HttpSession session) {
//		String loginuser = (String) session.getAttribute("login");
//		model.addAttribute("member", ms.searchMember(loginuser));
//		return "member/modify";
//	}
//
//	// 회원가입 처리
//	@PostMapping("/member/register")
//	public String memberRegist(HttpServletRequest request) {
//		try {
//			String id = request.getParameter("id");
//			String pw = request.getParameter("pw");
//			String name = request.getParameter("name");
//			String phone = request.getParameter("phone");
//			String email = request.getParameter("email");
//			String[] allergy = request.getParameterValues("allergy");
//			
//
//			String allergySum = "";
//
//			if (allergy != null) {
//				for (String a : allergy) {
//					allergySum = allergySum.concat("/" + allergys[Integer.parseInt(a)]);
//				}
//			}
//
//			if (!allergySum.equals("")) {
//				allergySum = allergySum.substring(1);
//			}
//
//			System.out.println("/SignUp " + id + " / " + pw);
//			Member member = new Member(id, pw, name, phone, email, allergySum);
//
//			ms.signup(member);
//
//			return "redirect:../main";
//		}catch(RuntimeException e) {
//			return "redirect:signup";
//		}
//	}
//
//	// 회원가입 페이지
//	@GetMapping("member/signup")
//	public String showSignup() {
//		return "member/signup";
//	}
//
//	// 로그아웃페이지
//	@GetMapping("member/logout")
//	public String showLogout(HttpSession session) {
//		session.invalidate();
//		// c:remove
//		return "redirect:../main";
//	}
//
//	// 멤버 리스트 페이지
//	@PostMapping("/member/list")
//	public String memberList() {
//		return "member/list";
//	}
//
//	// 탈퇴처리
//	@PostMapping("/member/withdraw")
//	public String memberWithdraw(@RequestParam String id, HttpSession session) {
//		int result = ms.delete(id);
//		session.invalidate();
//		return "redirect:../main";
//	}
//
//	// 정보수정
//	@PostMapping("/member/modify")
//	public String memberModify(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		String pw = request.getParameter("pw");
//		String name = request.getParameter("name");
//		String phone = request.getParameter("phone");
//		String email = request.getParameter("email");
//		String[] allergy = request.getParameterValues("allergy");
//		System.out.println(Arrays.toString(allergy));
//
//		String allergySum = "";
//
//		if (allergy != null) {
//			for (String a : allergy) {
//				allergySum = allergySum.concat("/" + allergys[Integer.parseInt(a)]);
//			}
//		}
//		if (!allergySum.equals("")) {
//			allergySum = allergySum.substring(1);
//		}
//
//		Member member = new Member(id, pw, name, phone, email, allergySum);
//
//		int num = ms.update(member);
//
//		HttpSession session = request.getSession();
//		session.setAttribute("member", member);
//		System.out.println("/UpdateMember " + num + "개 수정");
//		return "redirect:../main";
//	}
//	
//	
//    
//	// 음식 섭취 정보
//	@PostMapping("/member/eatinsert")
//	public String memberEatInsert() {
//		return "member/insert";
//	}
//
//	// 음식 섭취 상세 정보
//	@ResponseBody
//	@PostMapping("/member/eatlist")
//	public String memberEatList(@RequestParam String foodname, @RequestParam String id, @RequestParam int code,
//			@RequestParam int count, HttpSession session) {
//		String loginuser = (String) session.getAttribute("login");
//        System.out.println("eatlist!!!");
//		if (loginuser != null) {
//			Eat temp = new Eat(0, loginuser, code, count, foodname);
//			fs.insertEat(temp);
//		}
//		return "myeatlist";
//	}
//	
//	
//	
//	// 섭취 정보 삭제
//	@PostMapping("/deletemyeat")
//	public String deleteMyEat(HttpServletRequest req){
//		String[] del = req.getParameterValues("del");
//		
//		ms.deleteMyEat(del);
//		
//		return "redirect:eatlist";
//	}
//}
