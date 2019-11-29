package com.ssafy.safefood.service;

import java.util.List;

import com.ssafy.safefood.dto.SearchHistory;

public interface SearchHistoryService {
	SearchHistory select(String word);
	int insert(SearchHistory sh);
	int update(SearchHistory sh);
	List<SearchHistory> searchAll(String id);
	int delete(String word);
	SearchHistory search(SearchHistory sh);
}
