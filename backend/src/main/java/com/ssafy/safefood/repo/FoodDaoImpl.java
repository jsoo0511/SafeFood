package com.ssafy.safefood.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.safefood.dto.Eat;
import com.ssafy.safefood.dto.Food;
import com.ssafy.safefood.dto.FoodPageBean;

@Repository
public class FoodDaoImpl implements FoodDao {
	private List<Food> foods;
	
	private final String namespace = "com.ssafy.mapper.FoodMapper.";
	
	@Autowired
	SqlSession session;

	@Override
	public List<Food> searchBest(FoodPageBean bean) {
		String stmt = namespace + "searchBest";
		return session.selectList(stmt, bean);
	}

	public List<Food> searchBestIndex() {
		return null;
	}

	public List<Food> getAllList() {
		return foods;
	}
	
	@Override
	public int foodCount(FoodPageBean bean) {
		String stmt = namespace + "foodCount";
		return session.selectOne(stmt, bean);
	}

	@Override
	public List<Food> searchAll(FoodPageBean bean) {
		String stmt = namespace + "searchAllBean";
		System.out.println("결과");
		System.out.println(bean.getKey() + " " + bean.getWord());
		List<Food> res = session.selectList(stmt, bean);
		System.out.println(res);
		return res;
	}


	@Override
	public Food search(int code) {
		String stmt = namespace + "search";
		return session.selectOne(stmt, code);
	}

	@Override
	public int insertFood(Food food) {
		String stmt = namespace + "insertFood";
		return session.selectOne(stmt, food);
	}

	@Override
	public int updateClick(int code) {
		String stmt = namespace + "updateClick";
		return session.update(stmt, code);
	}
	
	@Override
	public int updateSfreq(int code) {
		String stmt = namespace + "updateSfreq";
		return session.update(stmt, code);
	}

	@Override
    public int insertEat(Eat eat) {
        String stmt = namespace + "insertEat";
        System.out.println("add eat");
        return session.insert(stmt, eat);
    }

	@Override
	public List<Food> frequency(String choice) {
		String stmt=namespace+"frequency";
		return session.selectList(stmt,choice);
	}
}
