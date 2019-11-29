package com.ssafy.safefood.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.safefood.dto.Eat;
import com.ssafy.safefood.dto.Food;
import com.ssafy.safefood.dto.FoodPageBean;
import com.ssafy.safefood.dto.Member;
import com.ssafy.safefood.service.FoodService;
import com.ssafy.safefood.service.JwtService;
import com.ssafy.safefood.service.MemberService;
import com.ssafy.safefood.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class FoodRestController {
	
	@Autowired
	MemberService ms;
	
	@Autowired
	FoodService fs;	
	
	@Autowired
	private UserService us;	
	
	@Autowired
	private JwtService js;	
	
	// 검색창에 검색한 식품 목록
		@GetMapping("/food")
		@ApiOperation("음식 검색  stype에서 all, name, maker, material로 검색타입 소분류 선택")
		public  ResponseEntity<Map<String, Object>> foodList(@RequestParam String stype, @RequestParam String word) {
			// 동적쿼리로 바꾸자
			if(stype==null) {
				stype="all";
			}
			if(word==null) {
				word="";
			}
			List<Food> f = fs.searchAll(new FoodPageBean(stype, word, null, 0));

			for(Food foo: f) {
				fs.updateSfreq(foo.getCode());
			}
			
			//session.setAttribute("foodlist", f);
			String retword="";
			
			switch(stype) {
			case "all":
				retword="전체";
				break;
			case "name":
				retword="상품명";
				break;
			case "maker":
				retword="제조사";
				break;
			case "material":
				retword="재료";
				break;
			default:
			}
			
	return response(f,"null",HttpStatus.OK,true);
		}
	
//	// 정렬된 음식 페이지
//	@PostMapping("/food/sorted")
//	public  String sortedFoodList() {
//		return "food/sortedlist";
//	}

	//@ResponseBody
	//@PostMapping("/food/similar")
	//public List<Food> foodSimilar(@RequestParam int fname) throws Exception {
	/**유클리디안 거리 구하기 위한 함수 fooddetail함수에서 호출해서 이용*/
	public List<Food> foodSimilar(int fname) throws Exception {
		// 현재 음식 찾기
		Food food = fs.search(fname);
		List<Food> list = null;
		list = fs.searchAll(new FoodPageBean());			
		
		//유클리디언 거리
		double[] u = new double[20];
		double[] idx = new double[20];
		int cnt =0;
		for (Food f : list) {
			double dis = Math.floor(Math.pow(food.getCalory()-f.getCalory(), 2)+Math.pow(food.getCarbo()-f.getCarbo(), 2)+
					Math.pow(food.getProtein()-f.getProtein(), 2)+Math.pow(food.getFat()-f.getFat(), 2)+Math.pow(food.getSugar()-f.getSugar(), 2)+
					Math.pow(food.getNatrium()-f.getNatrium(), 2));
			idx[cnt] = u[cnt] = dis;
			cnt++;
		}
		
		Arrays.sort(idx);
		
		List<Food>f = new ArrayList<>();
		for (int i = 1; i < 6; i++) {
			for (int j = 0; j < u.length; j++) {
				if(idx[i]==u[j]) {
					f.add(fs.search(j+1));
				}
			}
		}
		
		System.out.println("similar list : " + f);

		return f;
	}
	
	// 식품 상세정보 페이지
	@GetMapping("/food/{code}")
	@ApiOperation("식품상세정보, 해당food정보와 유클리디안거리 정보 전송") /**로그인 되지 않은 사람은 식품 상제정보 내용을 열람 못하게 해야하므로 로그인 여부를 따지기위해 session 로그인 값이 필요함*/
	public ResponseEntity<Map<String, Object>> showFooddetail(@PathVariable int code) throws Exception {
		// 조회수 업데이트
		fs.updateClick(code);
		try {
			
			Food f = fs.search(code);//food정보
			List<Food> uclidian=foodSimilar(code);
			return response(f,uclidian,HttpStatus.OK,true);
		} catch (RuntimeException e) {
			log.trace("식품상세정보 에러: {}",e);
			return response("식품상세정보 에러","error",HttpStatus.CONFLICT,false);
		}
	}
	
	//음식 클릭한 순위
	@GetMapping("/foodFrequency")
	@ApiOperation("음식 순위 상위 10개 추출 - c입력시 클릭 순위, s입력시 검색 순위")
	public ResponseEntity<Map<String, Object>> foodFrequency(@RequestParam String choice){
		
		try {
			List<Food> list=fs.frequency(choice);
			return response(list,"순위 상위 10개 추출 성공",HttpStatus.OK,true);
		} catch (RuntimeException e) {
			log.trace("순위추출 에러: {}",e);
			return response("순위추출 에러","error",HttpStatus.CONFLICT,false);
		}
		
	}


	
	private ResponseEntity<Map<String, Object>> response(Object data, Object data2,HttpStatus httpStatus, boolean status) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("data", data);
		resultMap.put("dataIngredient", data2);
		resultMap.put("status", status);

		// 상태와 함께 Map반환
		return new ResponseEntity<>(resultMap, httpStatus);
	}
	


}
