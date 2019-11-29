package com.ssafy.safefood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.safefood.dto.Zzim;
import com.ssafy.safefood.repo.ZzimDao;

@Service
public class ZzimServiceImpl implements ZzimService{
	
	@Autowired
	ZzimDao dao;

	@Override
	public int insert(Zzim z) {
		return dao.insert(z);
	}

	@Override
	public List<Zzim> select(String id) {
		return dao.select(id);
	}

	@Override
	public int delete(Zzim z) {
		return dao.delete(z);
	}

	@Override
	public Zzim check(Zzim z) {
		return dao.check(z);
	}

	@Override
	public Zzim IsZzim(Zzim z) {
		return dao.IsZzim(z);
	}

}
