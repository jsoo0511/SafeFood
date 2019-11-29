package com.ssafy.safefood.service;

import java.util.List;
import java.util.Map;

import com.ssafy.safefood.dto.Eat;
import com.ssafy.safefood.dto.Member;
import com.ssafy.safefood.dto.YMD;
import com.ssafy.safefood.dto.Zzim;

public interface MemberService {

	// 로그인 정보 가져오기
	Member login(String id, String pw);

	// 회원 가입
	int signup(Member member);

	// 회원 정보 수정
	int update(Member member);

	// 회원 탈퇴
	int delete(String id);

	// 회원 정보 가져오기 
	Member searchMember(String id);

	// 멤버 리스트
	List<Member> searchAll();

	// 섭취 정보 추가
	Member eatMember(String id);
	
	// 멤버가 찜한 음식들의 영양소 총합
	Zzim zzimNutrients(String id);
	
	//연,월,일별 사용자 섭취정보
	List<Eat> EatDetailYMD(YMD y);
	
	//연,월,일별 사용자 섭취정보
	List<Eat> EatDetailYMD(Map<String, String> map);

	int deleteMyEat(int[] delEatList);
	
}
