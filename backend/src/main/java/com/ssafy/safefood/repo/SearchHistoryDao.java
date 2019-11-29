package com.ssafy.safefood.repo;

import java.util.List;

import com.ssafy.safefood.dto.SearchHistory;

public interface SearchHistoryDao {
	
	SearchHistory select(String word);
	int insert(SearchHistory sh);
	int update(SearchHistory sh);
	List<SearchHistory> searchAll(String id);
	int delete(String word);
	SearchHistory search(SearchHistory sh);

}
