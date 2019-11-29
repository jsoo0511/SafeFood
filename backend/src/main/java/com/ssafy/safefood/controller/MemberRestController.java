package com.ssafy.safefood.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.safefood.dto.Eat;
import com.ssafy.safefood.dto.Food;
import com.ssafy.safefood.dto.FoodPageBean;
import com.ssafy.safefood.dto.Member;
import com.ssafy.safefood.dto.SearchHistory;
import com.ssafy.safefood.dto.User;
import com.ssafy.safefood.dto.YMD;
import com.ssafy.safefood.dto.Zzim;
import com.ssafy.safefood.service.FoodService;
import com.ssafy.safefood.service.JwtService;
import com.ssafy.safefood.service.MemberService;
import com.ssafy.safefood.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
public class MemberRestController {
	private String[] allergys = { "대두", "땅콩", "우유", "게", "새우", "참치", "연어", "쑥", "소고기", "닭고기", "돼지고기", "복숭아", "민들레",
			"계란흰자" };

	@Autowired
	MemberService ms;

	@Autowired
	FoodService fs;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	@PostMapping("/Member/Login")
	public ResponseEntity<Map<String, Object>> signin(@RequestBody User user) {
//		User user = new User(email, password);
		System.out.println(user);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			Member loginUser = userService.signin(user.getEmail(), user.getPassword());

			String token = jwtService.create(new User(loginUser.getId(), loginUser.getPw()));

			resultMap.put("auth_token", token);
			resultMap.put("status", true);
			resultMap.put("data", loginUser);
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			// TODO: handle exception
			log.error("로그인 실패", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/info")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			String info = userService.getServerInfo();

			resultMap.putAll(jwtService.get(req.getParameter("auth_token")));

			resultMap.put("status", true);
			resultMap.put("info", info);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			log.error("정보조회 실패", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}


	// 회원가입 처리
		@PostMapping("/Member")
		@ApiOperation("회원가입")
		public ResponseEntity<Map<String, Object>> memberRegist(@RequestBody Member member) {
			try {
				return response(ms.signup(member), "null", HttpStatus.OK, true);
			} catch (RuntimeException e) {
				log.trace("error: {}", e);
				return response("회원가입실패", "null", HttpStatus.CONFLICT, false);
			}
		}

	// 로그아웃페이지
	@GetMapping("/Member/Logout")
	@ApiOperation("로그아웃")        /**사용자의 로그아웃을 위해서 사용자 정보를 해제하기 위해서 session이 필요함*/
	public ResponseEntity<Map<String, Object>> showLogout() {
		try {
			//token 삭제로 처리
			return response("로그아웃성공", "null", HttpStatus.OK, true);
		} catch (RuntimeException e) {
			log.trace("error: {}", e);
			return response("로그아웃실패", "null", HttpStatus.CONFLICT, false);
		}
	}

	// 탈퇴처리
	@DeleteMapping("/Member/{id}")
	@ApiOperation("회원탈퇴")        /**회원탈퇴 권한을 위해 session값 필요*/
	public ResponseEntity<Map<String, Object>> memberWithdraw(@PathVariable String id) {
		try {
			int result = ms.delete(id);
			if (result == 0)
				throw new RuntimeException();
			//session.invalidate();
			//token 삭제
			return response("회원탈퇴성공","null", HttpStatus.OK, true);
		} catch (RuntimeException e) {
			log.trace("error: {}", e);
			return response("회원탈퇴실패", "null", HttpStatus.CONFLICT, false);
		}
	}

	// 정보수정
	@PutMapping("/Member")
	@ApiOperation("회원정보수정") /**회원정보수정 권환을 위해 session값 필요*/
	public ResponseEntity<Map<String, Object>> memberModify(@RequestParam String id, @RequestParam String pw,
			@RequestParam String name, @RequestParam String phone, @RequestParam String email,
			@RequestParam("allergy") String[] allergy) {

		try {
			String allergySum = "";

			if (allergy != null) {
				for (String a : allergy) {
					allergySum = allergySum.concat("/" + allergys[Integer.parseInt(a)]);
				}
			}
			if (!allergySum.equals("")) {
				allergySum = allergySum.substring(1);
			}

			Member member = new Member(id, pw, name, phone, email, allergySum);

			int num = ms.update(member);
			if (num == 0)
				throw new RuntimeException();
			return response("수정성공", "null",HttpStatus.OK, true);

		} catch (RuntimeException e) {
			return response("수정실패", "null", HttpStatus.OK, true);
		}
	}

	// 음식 섭취 상세 정보
		@PostMapping("/Member/EatList")
		@ApiOperation("음식 섭취 정보 추가")   /**사용자의 음식 섭취 정보 추가 권한을 위해서 session값이 필요함*/
		public ResponseEntity<Map<String, Object>> memberEatList(@RequestBody Eat temp) {
			System.out.println("temp : " + temp);
			int result = 0;
			try {
//				Eat temp = new Eat(0, id, code, count, foodname);
				result = fs.insertEat(temp);
				return response(result, null, HttpStatus.OK, true);
			} catch (RuntimeException e) {
				return response("로그인정보가 없습니다.", "null", HttpStatus.CONFLICT, false);
			}
		}

	// 음식 섭취 상세 정보  //session loginUser필요
	@GetMapping("/Member/EatList")
	@ApiOperation("해당 유저가 섭취한 음식 섭취 상세 정보")   /**사용자의 음식 섭취 상세정보 접근 권한을 위해서 session값이 필요함*/
	public ResponseEntity<Map<String, Object>> memberEatList(@RequestParam String loginuser) {
		
		System.out.println("login " + loginuser);
		//session.setAttribute("pagenow", "myeatlist");

		if (loginuser != null) {
			Member result = ms.eatMember(loginuser);

			if (result != null) {
				List<Eat> myNutri = result.getEatlist();
				Food sum = new Food(-1, "", 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, "", "", "", "", 0, 0);

				for (Eat e : myNutri) {
					Food temp = fs.search(e.getCode());
					sum.setSupportpereat(sum.getSupportpereat() + temp.getSupportpereat());
					sum.setCalory(sum.getCalory() + temp.getCalory());
					sum.setCarbo(sum.getCarbo() + temp.getCarbo());
					sum.setProtein(sum.getProtein() + temp.getProtein());
					sum.setFat(sum.getFat() + temp.getFat());
					sum.setSugar(sum.getSugar() + temp.getSugar());
					sum.setNatrium(sum.getNatrium() + temp.getNatrium());
					sum.setChole(sum.getChole() + temp.getChole());
					sum.setFattyacid(sum.getFattyacid() + temp.getFattyacid());
					sum.setTransfat(sum.getTransfat() + temp.getTransfat());
				}

			//	session.setAttribute("nutri", myNutri);
			//	session.setAttribute("summary", sum);
				return response(myNutri,sum,HttpStatus.OK,true);
			}
		}
		return response("회원 정보가 존재하지 않습니다.","null", HttpStatus.CONFLICT, true);
		
	}
	
	// 멤버가 찜한 음식들의 영양소 총합
	@GetMapping("/Member/EatList/{id}")
	@ApiOperation("사용자가 찜한 음식들의 영양소 총합") /**사용자의 찜 음식에 관한정보 접근 권한을 위해서 session값이 필요함*/
	public ResponseEntity<Map<String, Object>> memberZzimNutrients(@PathVariable String id){
		try {
			Zzim z=ms.zzimNutrients(id);
			if(z==null) {
				return response("찜한 목록이 없습니다.","null",HttpStatus.CONFLICT,false);
			} else {
				return response(z,"찜한 총 영양소 return성공",HttpStatus.OK,true);
			}
		} catch (RuntimeException e) {
			log.trace("zzimNutrients Error: {}",e);
			return response("zzimNutrients Error 발생","null",HttpStatus.CONFLICT,false);
		}
	}

    // 섭취 정보 삭제(배열로 값 받음)
	@DeleteMapping("/Member/EatList")
	@ApiOperation("섭취정보 배열로 받은 후 삭제")
	public ResponseEntity<Map<String, Object>> deleteMyEat(@RequestParam List<Integer> selected) {
		int del[] = new int[selected.size()];
		
		for(int i = 0; i < del.length; i++) {
			del[i] = selected.get(i);
		}
		
		try {
			int result=ms.deleteMyEat(del);
			return response("EatList Delete Success","null",HttpStatus.OK,true);
		} catch (RuntimeException e) {
			log.trace("EatList Delete Error: {}",e);
			return response("EatList Delete Error 발생","null",HttpStatus.CONFLICT,false);
		}
		
	}
	
	//연,월,일별 사용자 섭취정보
	@GetMapping("/Member/EatListYMD")
	@ApiOperation("사용자의 연,월,일별 섭취정보 연-y, 월-m, 일-d")   /**사용자의 섭취정보 접근 권한을 위해서 사용자 확인을 위한 session값이 필요함*/
	public ResponseEntity<Map<String, Object>> EatDetailYMD(@RequestParam String id, @RequestParam String choice){
		
		try {
		//	HashMap<String, String> map=new HashMap<>();
		//	map.put("id", id);
		//	map.put("choice",choice);
			List<Eat> list = ms.EatDetailYMD(new YMD(id, choice));
			//List<Eat> list=ms.EatDetailYMD(map);
			return response(list,"연월일 섭취정보 return 성공",HttpStatus.OK,true);
		} catch (RuntimeException e) {
			log.trace("EatListYMD Error: {}", e);
			return response("EatListYMD Error","null",HttpStatus.CONFLICT,false);
		}
	}

	private ResponseEntity<Map<String, Object>> response(Object data, Object data2, HttpStatus httpStatus,
			boolean status) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("data", data);
		resultMap.put("dataIngredient", data2);
		resultMap.put("status", status);

		// 상태와 함께 Map반환
		return new ResponseEntity<>(resultMap, httpStatus);
	}

}
