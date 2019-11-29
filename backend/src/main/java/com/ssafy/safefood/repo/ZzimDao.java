package com.ssafy.safefood.repo;

import java.util.List;

import com.ssafy.safefood.dto.Zzim;

public interface ZzimDao {
	int insert(Zzim z);
	List<Zzim> select(String id);
	int delete (Zzim z);
	Zzim check(Zzim z);
	Zzim IsZzim(Zzim z);

}
