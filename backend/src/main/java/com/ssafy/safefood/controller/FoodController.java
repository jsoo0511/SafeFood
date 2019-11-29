//package com.ssafy.safefood.controller;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
////import com.google.gson.Gson;
//import com.ssafy.safefood.dto.Eat;
//import com.ssafy.safefood.dto.Food;
//import com.ssafy.safefood.dto.FoodPageBean;
//import com.ssafy.safefood.dto.Member;
//import com.ssafy.safefood.service.FoodService;
//import com.ssafy.safefood.service.MemberService;
//
//@Controller
//public class FoodController {
//	
//	@Autowired
//	MemberService ms;
//	
//	@Autowired
//	FoodService fs;	
//	
//	// 식품 리스트 페이지
//	@GetMapping("/foodlist")
//	public String showFoodlist(HttpSession session) {
//		List<Food> list = fs.searchAll(new FoodPageBean());
//		session.setAttribute("foodlist", list);
//		session.setAttribute("pagenow", "foodlist"); //pagenow는 옆의 side bar에서 어떤 항목이 선택됬는지를 표시해줌
//		return "food/foodlist";
//	}
//	
//	// 검색창에 검색한 식품 목록
//	@PostMapping("/foodlist")
//	public  String foodList(@RequestParam String stype, @RequestParam String word, HttpSession session) {
////		 동적쿼리로 바꾸자
//		if(stype==null) {
//			stype="all";
//		}
//		if(word==null) {
//			word="";
//		}
//		List<Food> f = fs.searchAll(new FoodPageBean(stype, word, null, 0));
//
//		for(Food foo: f) {
//			fs.updateSfreq(foo.getCode());
//		}
//		
//		session.setAttribute("foodlist", f);
//		String retword="";
//		
//		switch(stype) {
//		case "all":
//			retword="전체";
//			break;
//		case "name":
//			retword="상품명";
//			break;
//		case "maker":
//			retword="제조사";
//			break;
//		case "material":
//			retword="재료";
//			break;
//		default:
//		}
//		
//		session.setAttribute("type", retword);
//		session.setAttribute("word", word);
//
//		return "food/foodlist";
//	}
//	
//	// 정렬된 음식 페이지
//	@PostMapping("/food/sorted")
//	public  String sortedFoodList() {
//		return "food/sortedlist";
//	}
//
//	@ResponseBody
//	@PostMapping("/food/similar")
//	public List<Food> foodSimilar(@RequestParam int fname) throws Exception {
//
//		// 현재 음식 찾기
//		Food food = fs.search(fname);
//		List<Food> list = null;
//		list = fs.searchAll(new FoodPageBean());			
//		System.out.println("frname: "+fname);
//		//유클리디언 거리
//		double[] u = new double[20];
//		double[] idx = new double[20];
//		int cnt =0;
//		for (Food f : list) {
//			double dis = Math.floor(Math.pow(food.getCalory()-f.getCalory(), 2)+Math.pow(food.getCarbo()-f.getCarbo(), 2)+
//					Math.pow(food.getProtein()-f.getProtein(), 2)+Math.pow(food.getFat()-f.getFat(), 2)+Math.pow(food.getSugar()-f.getSugar(), 2)+
//					Math.pow(food.getNatrium()-f.getNatrium(), 2));
//			idx[cnt] = u[cnt] = dis;
//			cnt++;
//		}
//		
//		Arrays.sort(idx);
//		
//		List<Food>f = new ArrayList<>();
//		for (int i = 1; i < 6; i++) {
//			for (int j = 0; j < u.length; j++) {
//				if(idx[i]==u[j]) {
//					f.add(fs.search(j+1));
//				}
//			}
//		}
//		
//		System.out.println("similar list : " + f);
//
//		return f;
//	}
//	
//	// 식품 상세정보 페이지
//	@GetMapping("/fooddetail")
//	public String showFooddetail(@RequestParam int code, Model model) {
//		// 조회수 업데이트
//		fs.updateClick(code);
//		
//		Food f = fs.search(code);
//		model.addAttribute("foodview", f);
//
//		return "food/fooddetail";
//	}
//
//	// 음식 섭취 상세 정보
//	@GetMapping("/myeatlist")
//	public String memberEatList(HttpSession session) {
//		String loginuser = (String) session.getAttribute("login");
//
//		System.out.println("login " + loginuser);
//		session.setAttribute("pagenow", "myeatlist");
//
//		if (loginuser != null) {
//			Member result = ms.eatMember(loginuser);
//
//			if (result != null) {
//				List<Eat> myNutri = result.getEatlist();
//				Food sum = new Food(-1, "", 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, "", "", "", "", 0, 0);
//
//				for (Eat e : myNutri) {
//					Food temp = fs.search(e.getCode());
//					sum.setSupportpereat(sum.getSupportpereat() + temp.getSupportpereat());
//					sum.setCalory(sum.getCalory() + temp.getCalory());
//					sum.setCarbo(sum.getCarbo() + temp.getCarbo());
//					sum.setProtein(sum.getProtein() + temp.getProtein());
//					sum.setFat(sum.getFat() + temp.getFat());
//					sum.setSugar(sum.getSugar() + temp.getSugar());
//					sum.setNatrium(sum.getNatrium() + temp.getNatrium());
//					sum.setChole(sum.getChole() + temp.getChole());
//					sum.setFattyacid(sum.getFattyacid() + temp.getFattyacid());
//					sum.setTransfat(sum.getTransfat() + temp.getTransfat());
//				}
//
//				session.setAttribute("nutri", myNutri);
//				session.setAttribute("summary", sum);
//			}
//			return "member/mynutri";
//		}
//		return "member/mynutri";
//		
//	}
//	
//}
