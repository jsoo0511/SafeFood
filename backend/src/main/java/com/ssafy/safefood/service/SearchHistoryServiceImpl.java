package com.ssafy.safefood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.safefood.dto.SearchHistory;
import com.ssafy.safefood.repo.SearchHistoryDao;

@Service
public class SearchHistoryServiceImpl implements SearchHistoryService{

	
	@Autowired
	SearchHistoryDao dao;
	
	@Override
	public SearchHistory select(String word) {
		return dao.select(word);
	}

	@Override
	public int insert(SearchHistory sh) {
		return dao.insert(sh);
	}

	@Override
	public int update(SearchHistory sh) {
		return dao.update(sh);
	}

	@Override
	public List<SearchHistory> searchAll(String id) {
		return dao.searchAll(id);
	}

	@Override
	public int delete(String word) {
		return dao.delete(word);
	}

	@Override
	public SearchHistory search(SearchHistory sh) {
		return dao.search(sh);
	}

}
