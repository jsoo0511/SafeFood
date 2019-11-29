package com.ssafy.safefood.repo;

import java.util.List;

import com.ssafy.safefood.dto.Board;
import com.ssafy.safefood.dto.PageBean;
import com.ssafy.safefood.dto.Reply;

public interface QnABoardDao {
	public int getQnABoardNo();

	public int insertQnABoard(Board board);

	public Board searchQnABoard(String no);

	public List<Board> searchQnAAll(PageBean bean);

	public int count();

	public int updateQnABoard(Board board);

	public int deleteQnABoard(String no);
	
	public List<Board> searchQnAPaging(PageBean bean);
	
	public int LargerThanMe(int no);
	
	public int insertReply(Reply r);
	
	public List<Reply> getThisBoardReply(String boardno);
	
	public int updateReply(Reply r);
	
	public int deleteReply(int idx);
}
