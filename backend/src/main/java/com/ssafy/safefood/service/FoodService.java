package com.ssafy.safefood.service;

import java.util.List;

import com.ssafy.safefood.dto.Eat;
import com.ssafy.safefood.dto.Food;
import com.ssafy.safefood.dto.FoodPageBean;

public interface FoodService {
	
	// 음식 추가
	public void insertData(Food food);

	// 조건에 맞는 음식 검색 (페이징)
	public List<Food> searchAll(FoodPageBean bean);

	// 코드와 일치하는 음식 검색
	public Food search(int code);

	// 가장 많이 검색한
	List<Food> searchBest(FoodPageBean bean);

	// ?
	public List<Food> searchBestIndex();

	// 사용자별 음식의 섭취 정보 추가 
	public int insertEat(Eat eat);

	// 해당 음식 상세 정보 조회 수 업데이트
	public void updateClick(int code);

	// 검색 결과로 나온 횟수 업데이트
	public void updateSfreq(int code);
	
	//검색 순위
	public List<Food> frequency(String choice);
}
