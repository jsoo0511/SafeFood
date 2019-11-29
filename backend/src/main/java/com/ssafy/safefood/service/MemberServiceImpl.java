package com.ssafy.safefood.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.safefood.repo.MemberDao;

import lombok.extern.slf4j.Slf4j;

import com.ssafy.safefood.dto.Eat;
import com.ssafy.safefood.dto.Member;
import com.ssafy.safefood.dto.YMD;
import com.ssafy.safefood.dto.Zzim;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao mdao;
	
	// 로그인 (search와 거의 비슷함)
	@Override
	public Member login(String id, String pw) {
		log.trace("login : {}, {}", id, pw);
		return mdao.LoginMember(id, pw);
	}
	
	// 회원가입 insert
	@Override
	@Transactional
	public int signup(Member member) {
		return mdao.insertMember(member);
	}
	
	// 회원정보 update
	@Override
	@Transactional
	public int update(Member member) {
		return mdao.updateMember(member);
	}

	// 회원정보 delete
	@Override
	@Transactional
	public int delete(String id) {
		return mdao.deleteMember(id);
	}

	// 멤버정보 search
	@Override
	public Member searchMember(String id) {
		return mdao.searchMember(id);
	}
	
	// 전체멤버정보 Search
	@Override
	public List<Member> searchAll() {
		return mdao.searchAll();
	}

	// 회원별 영양 섭취 정보 insert
	@Override
	@Transactional
	public Member eatMember(String id){
		return mdao.eatMember(id);
	}

	@Override
	@Transactional
	public int deleteMyEat(int[] delEatList) {
		return mdao.deleteMyEat(delEatList);
	}

	@Override
	public Zzim zzimNutrients(String id) {
		return mdao.zzimNutrients(id);
	}	

	@Override
	public List<Eat> EatDetailYMD(Map<String, String> map) {
		return mdao.EatDetailYMD(map);
	}

	@Override
	public List<Eat> EatDetailYMD(YMD y) {
		System.out.println("asdsadsadasd: "+y.getChoice());
		return mdao.EatDetailYMD(y);
	}
}
