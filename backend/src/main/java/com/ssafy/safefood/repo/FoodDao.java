package com.ssafy.safefood.repo;

import java.util.List;

import com.ssafy.safefood.dto.Eat;
import com.ssafy.safefood.dto.Food;
import com.ssafy.safefood.dto.FoodPageBean;

public interface FoodDao {
	// 식품 영양 정보 및 식품 정보를 xml 파일에서 로딩하는 기능 
	// public void loadData();
	
	// 검색 조건(key) 검색 단어(word)에 해당하는 식품 정보(Food)의 개수를 반환. 
	public int foodCount(FoodPageBean bean);
	
	// 검색 조건(key) 검색 단어(word)에 해당하는 식품 정보(Food)를  검색해서 반환.  
	public List<Food> searchAll(FoodPageBean bean);
	
	// 해당 식품 정보를 반환하고, 검색된 식품의 sFreq + 1
	public Food search(int code);
	
	// 가장 많이 검색한 Food
	List<Food> searchBest(FoodPageBean bean);
	public List<Food> searchBestIndex();
	
	// food를 DB에 추가
	public int insertFood(Food food);
	
	// food를 DB에서 Update
	public int updateClick(int code);

	// 먹은 음식에 추가 (전체 칼로리용)
	int insertEat(Eat eat);

	// 검색 된 음식 추가
	int updateSfreq(int code);
	
	//검색 순위
	public List<Food> frequency(String choice);
	
}
