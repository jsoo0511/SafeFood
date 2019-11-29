package com.ssafy.safefood.repo;

import java.util.List;
import java.util.Map;

import com.ssafy.safefood.dto.Eat;
import com.ssafy.safefood.dto.Member;
import com.ssafy.safefood.dto.YMD;
import com.ssafy.safefood.dto.Zzim;

public interface MemberDao {

	// 멤버 가입
	int insertMember(Member member);

	// 멤버 로그인 (멤버 정보 return)
	Member LoginMember(String id, String pw);

	// 멤버 search (로그인과 비슷해서 정리해야 함)
	Member searchMember(String id);

	// 멤버 정보 update
	int updateMember(Member member);

	// 멤버 정보 delete
	public int deleteMember(String id);

	// 멤버 리스트 찾아오기
	List<Member> searchAll();

	// 멤버가 먹은 음식 추가
	Member eatMember(String id);
	
	// 멤버가 찜한 음식들의 영양소 총합
	Zzim zzimNutrients(String id);
	
	//연,월,일별 사용자 섭취정보
	List<Eat> EatDetailYMD(YMD y);
	
	//연,월,일별 사용자 섭취정보
	List<Eat> EatDetailYMD(Map<String, String> map);
	
	//섭취 정보 삭제(배열로 값 받음)
	int deleteMyEat(int[] delEatList);
}
