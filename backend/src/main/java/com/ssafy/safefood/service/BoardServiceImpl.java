package com.ssafy.safefood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.safefood.repo.BoardDao;
import com.ssafy.safefood.dto.Board;
import com.ssafy.safefood.dto.PageBean;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao dao;

	public void setDao(BoardDao dao) {
		this.dao = dao;
	}

	@Override
	@Transactional
	public int insertBoard(Board board) {
		return dao.insertBoard(board);
	}

	@Override
	@Transactional
	public int updateBoard(Board board) {
		return dao.updateBoard(board);
	}

	@Override
	@Transactional
	public int deleteBoard(String no) {
		return dao.deleteBoard(no);
	}

	@Override
	public Board search(String no) {
		return dao.searchBoard(no);
	}

	@Override
	public List<Board> searchAll(PageBean bean) {
		return dao.searchAll(bean);
	}

	@Override
	public int count() {
		return dao.count();
	}

	@Override
	public List<Board> searchPaging(PageBean bean) {
		return dao.searchPaging(bean);
	}
	
	@Override
	public int LargerThanMe(int no) {
		return dao.LargerThanMe(no);
	}
}
