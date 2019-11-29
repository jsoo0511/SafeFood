package com.ssafy.safefood.repo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.safefood.dto.Eat;
import com.ssafy.safefood.dto.Member;
import com.ssafy.safefood.dto.YMD;
import com.ssafy.safefood.dto.Zzim;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	SqlSession session;

	private final String namespace = "com.ssafy.mapper.MemberMapper.";

	@Override
	public int insertMember(Member member) {
		String stmt = namespace + "insertMember";
		return session.update(stmt, member);
	}

	@Override
	public Member LoginMember(String id, String pw) {
		String stmt = namespace + "loginMember";
		Member temp = new Member(id, pw, null, null, null, null);
		return session.selectOne(stmt, temp);	//수정
	}

	@Override
	public Member searchMember(String id) {
		String stmt = namespace + "searchMember";
		return session.selectOne(stmt, id);
	}

	@Override
	public int updateMember(Member member) {
		String stmt = namespace + "updateMember";
		return session.update(stmt, member);
	}

	@Override
	public int deleteMember(String id) {
		String stmt = namespace + "deleteMember";
		return session.delete(stmt, id);
	}

	@Override
	public List<Member> searchAll() {
		String stmt = namespace + "searchAll";
		return session.selectList(stmt);
	}
	
	public Member eatMember(String id){
		String stmt = namespace + "eatMember";
		return session.selectOne(stmt, id);
	}
	
	public int deleteMyEat(int[] delEatList) {
		String stmt = namespace + "deleteMyEat";
		List<Integer> dellist=new ArrayList<Integer>(delEatList.length);
		for(int i:delEatList) {
			dellist.add(i);
		}
		Map<String, Object> delmap = new HashMap<String, Object>();
		delmap.put("dellist", dellist);
		return session.delete(stmt, delmap);
	}

	@Override
	public Zzim zzimNutrients(String id) {
		String stmt=namespace + "zzimNutrients";
		return session.selectOne(stmt,id);
	}

	@Override
	public List<Eat> EatDetailYMD(YMD y) {
		String stmt=namespace + "EatDetailYMD";
		return session.selectList(stmt,y);
	}

	@Override
	public List<Eat> EatDetailYMD(Map<String, String> map) {
		String stmt=namespace + "EatDetailYMD";
		System.out.println(map.get("id"));
//		System.out.println(map.get("));
		return session.selectList(stmt,map);
	}
}
