package com.ssafy.safefood.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.safefood.dto.SearchHistory;

@Repository
public class SearchHistoryDaoImpl implements SearchHistoryDao{
	
	private final String namespace = "com.ssafy.mapper.SearchHistoryMapper.";

	@Autowired
	SqlSession session;
	
	@Override
	public SearchHistory select(String word) {
		return session.selectOne(namespace+"select",word);
	}

	@Override
	public int insert(SearchHistory sh) {
		return session.insert(namespace+"insert",sh);
	}

	@Override
	public int update(SearchHistory sh) {
		return session.update(namespace+"update",sh);
	}

	@Override
	public List<SearchHistory> searchAll(String id) {
		if(id.equals("")) {
			return session.selectList(namespace+"selectAll",null);
		}else {
			return session.selectList(namespace+"selectAll",id);						
		}
	}

	@Override
	public int delete(String word) {
		return session.delete(namespace+"delete", word);
	}


	@Override
	public SearchHistory search(SearchHistory sh) {
		return session.selectOne(namespace+"select",sh);
	}
	
	

}
