package com.ssafy.safefood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.safefood.dto.Board;
import com.ssafy.safefood.dto.PageBean;
import com.ssafy.safefood.dto.Reply;
import com.ssafy.safefood.repo.QnABoardDao;

@Service
public class QnABoardServiceImpl implements QnABoardService{

	@Autowired
	QnABoardDao dao;

	@Override
	public int insertQnABoard(Board board) {
		return dao.insertQnABoard(board);
	}

	@Override
	public int updateQnABoard(Board board) {
		return dao.updateQnABoard(board);
	}

	@Override
	public int deleteQnABoard(String no) {
		return dao.deleteQnABoard(no);
	}

	@Override
	public Board searchQnABoard(String no) {
		return dao.searchQnABoard(no);
	}

	@Override
	public List<Board> searchQnAall(PageBean bean) {
		return dao.searchQnAAll(bean);
	}

	@Override
	public int count() {
		return dao.count();
	}
	
	@Override
	public List<Board> searchQnAPaging(PageBean bean) {
		return dao.searchQnAPaging(bean);
	}

	@Override
	public int LargerThanMe(int no) {
		return dao.LargerThanMe(no);
	}

	@Override
	public int insertReply(Reply r) {
		return dao.insertReply(r);
	}

	@Override
	public List<Reply> getThisBoardReply(String boardno) {
		return dao.getThisBoardReply(boardno);
	}

	@Override
	public int updateReply(Reply r) {
		return dao.updateReply(r);
	}

	@Override
	public int deleteReply(int idx) {
		return dao.deleteReply(idx);
	}
	
	
}
