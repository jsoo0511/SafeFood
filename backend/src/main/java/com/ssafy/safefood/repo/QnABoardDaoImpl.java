package com.ssafy.safefood.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.safefood.dto.Board;
import com.ssafy.safefood.dto.PageBean;
import com.ssafy.safefood.dto.Reply;

@Repository
public class QnABoardDaoImpl implements QnABoardDao{
	
	private final String namespace = "com.ssafy.mapper.QnAMapper.";
	
	@Autowired
	SqlSession session;

	@Override
	public int getQnABoardNo() {
		return session.selectOne(namespace+"getQnABoardNo");
	}

	@Override
	public int insertQnABoard(Board board) {
		return session.insert(namespace+"insertQnABoard",board);
	}

	@Override
	public Board searchQnABoard(String no) {
		return session.selectOne(namespace + "searchQnABoard", no);
	}

	@Override
	public List<Board> searchQnAAll(PageBean bean) {
		List<Board> result = session.selectList(namespace+"searchQnAall");
		return result;
	}

	@Override
	public int count() {
		return session.selectOne(namespace+"count");
	}

	@Override
	public int updateQnABoard(Board board) {
		return session.update(namespace + "updateQnABoard", board);
	}

	@Override
	public int deleteQnABoard(String no) {
		return session.delete(namespace + "deleteQnABoard", no);
	}
	
	@Override
	public List<Board> searchQnAPaging(PageBean bean) {
		
		return session.selectList(namespace+"searchQnAPaging",bean);
	}

	@Override
	public int LargerThanMe(int no) {
		return session.selectOne(namespace+"larger",no);
	}

	@Override
	public int insertReply(Reply r) {
		return session.insert(namespace+"insertReply",r);
	}

	@Override
	public List<Reply> getThisBoardReply(String boardno) {
		return session.selectList(namespace+"getThisBoardReply",boardno);
	}

	@Override
	public int updateReply(Reply r) {
		return session.insert(namespace+"updateReply",r);
	}

	@Override
	public int deleteReply(int idx) {
		return session.insert(namespace+"deleteReply",idx);
	}

}
