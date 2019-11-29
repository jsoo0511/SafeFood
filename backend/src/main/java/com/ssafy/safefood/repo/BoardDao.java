package com.ssafy.safefood.repo;

import java.util.List;

import com.ssafy.safefood.dto.Board;
import com.ssafy.safefood.dto.PageBean;

public interface BoardDao {
	public int getBoardNo();

	public int insertBoard(Board board);

	public Board searchBoard(String no);

	public List<Board> searchAll(PageBean bean);

	public int count();

	public int updateBoard(Board board);

	public int deleteBoard(String no);
	
	public List<Board> searchPaging(PageBean bean);
	
	public int LargerThanMe(int no);
}
