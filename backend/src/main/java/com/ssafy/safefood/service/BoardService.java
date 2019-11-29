package com.ssafy.safefood.service;

import java.util.List;

import com.ssafy.safefood.dto.Board;
import com.ssafy.safefood.dto.PageBean;

public interface BoardService {
	
	// 게시글 추가
	public int insertBoard(Board board);

	// 게시글 업데이트
	public int updateBoard(Board board);

	// 게시글 삭제
	public int deleteBoard(String no);

	// 해당 글
	public Board search(String no);

	// 조건에 맞는 글 모두 검색
	public List<Board> searchAll(PageBean bean);
	
	//게시글 개수검색
	public int count();
	
	//페이징 테스트
	public List<Board> searchPaging(PageBean bean);
	
	//나보다 no큰애들 수 반환
	public int LargerThanMe(int no);
}
