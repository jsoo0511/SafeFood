package com.ssafy.safefood.service;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.safefood.repo.FoodDao;
import com.ssafy.safefood.dto.Eat;
import com.ssafy.safefood.dto.Food;
import com.ssafy.safefood.dto.FoodPageBean;

@Service
public class FoodServiceImpl implements FoodService {

	private String[] allergys = { "대두", "땅콩", "우유", "게", "새우", "참치", "연어", "쑥", "소고기", "닭고기", "돼지고기", "복숭아", "민들레",	 "계란흰자" };

	@Autowired
	FoodDao dao;

	// 모든 음식 정보
	@Override
	public List<Food> searchAll(FoodPageBean bean) {
		return dao.searchAll(bean);
	}

	@Override
	public Food search(int code) {
		// code에 맞는 식품 정보를 검색하고, 검색된 식품의 원재료에 알레르기 성분이 있는지 확인하여 Food 정보에 입력한다.
		Food food = dao.search(code);
		StringBuilder sb = new StringBuilder("");
		for (String allergy : allergys) {
			if (allergy != null && food.getMaterial().contains(allergy)) {
				sb.append(allergy + " ");
			}
		}
		food.setAllergy(sb.toString());
		return food;
	}

	// 리스트에서 클릭시마다 클릭 빈도수(frequency) 업데이트
	@Override
	@Transactional
	public void updateClick(int code) {
		dao.updateClick(code);
	}
	
	// 검색 빈도 수 업데이트
	@Override
	@Transactional
	public void updateSfreq(int code) {
		dao.updateSfreq(code);
	}

	// 검색 빈도 수 select
	@Override
	public List<Food> searchBest(FoodPageBean bean) {
		return dao.searchBest(bean);
	}

	// 최대 검색 빈도 수 select
	@Override
	public List<Food> searchBestIndex() {
		return dao.searchBestIndex();
	}

	// food 데이터 DB에 insert
	@Override
	@Transactional
	public void insertData(Food food) {
		dao.insertFood(food);
	}

	// 먹은 음식 추가시 해당 user의 eat에 추가 (칼로리 계산용)
	@Override
	public int insertEat(Eat eat) {
		return dao.insertEat(eat);
	}

	@Override
	public List<Food> frequency(String choice) {
		return dao.frequency(choice);
	}
}
