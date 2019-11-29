package com.ssafy.safefood.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.safefood.dto.Zzim;

@Repository
public class ZzimDaoImpl implements ZzimDao{

private final String namespace = "com.ssafy.mapper.ZzimMapper.";
	
	@Autowired
	SqlSession session;

	@Override
	public int insert(Zzim z) {
		return session.insert(namespace+"insert",z);
	}

	@Override
	public List<Zzim> select(String id) {
		return session.selectList(namespace+"select",id);
	}

	@Override
	public int delete(Zzim z) {
		return session.delete(namespace+"delete",z);
	}

	@Override
	public Zzim check(Zzim z) {
		return session.selectOne(namespace+"check",z);
	}

	@Override
	public Zzim IsZzim(Zzim z) {
		return session.selectOne(namespace+"IsZzim",z);
	}
	


}
