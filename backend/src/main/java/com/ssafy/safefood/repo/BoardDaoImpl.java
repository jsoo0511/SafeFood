package com.ssafy.safefood.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.safefood.dto.Board;
import com.ssafy.safefood.dto.PageBean;

@Repository
public class BoardDaoImpl implements BoardDao{

	private final String namespace = "com.ssafy.mapper.BoardMapper.";
	
	@Autowired
	SqlSession session;

	
	@Override
	public int getBoardNo() {
		int result = session.selectOne(namespace + "getBoardNo");
		return result;
	}

	@Override
	public int insertBoard(Board board) {
		int result = session.insert(namespace + "insertBoard", board);
		return result;
	}

	@Override
	public Board searchBoard(String no) {
		Board result = session.selectOne(namespace + "searchBoard", no);
		return result;
	}

	@Override
	public List<Board> searchAll(PageBean bean) {
		List<Board> result = session.selectList(namespace+"searchAll");
		System.out.println("dao: " + result);
		return result;
	}

	@Override
	public int count() {
		int result = session.selectOne(namespace + "count");
		return result;
	}

	@Override
	public int updateBoard(Board board) {
		int result = session.update(namespace + "updateBoard", board);
		return result;
	}

	@Override
	public int deleteBoard(String no) {
		int result = session.delete(namespace + "deleteBoard", no);
		return result;
	}

	@Override
	public List<Board> searchPaging(PageBean bean) {
		
		return session.selectList(namespace+"searchPaging",bean);
	}

	@Override
	public int LargerThanMe(int no) {
		return session.selectOne(namespace+"larger",no);
	}


}
