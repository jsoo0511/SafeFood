package com.ssafy.safefood.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.safefood.dto.Board;
import com.ssafy.safefood.dto.PageBean;
import com.ssafy.safefood.dto.Reply;


public interface QnABoardService {
	
	// 게시글 추가
		public int insertQnABoard(Board board);

		// 게시글 업데이트
		public int updateQnABoard(Board board);

		// 게시글 삭제
		public int deleteQnABoard(String no);

		// 해당 글
		public Board searchQnABoard(String no);

		// 조건에 맞는 글 모두 검색
		public List<Board> searchQnAall(PageBean bean);
		
		//게시글 개수검색
		public int count();
		
		//페이징 테스트
		public List<Board> searchQnAPaging(PageBean bean);
		
		//나보다 no큰애들 수 반환
		public int LargerThanMe(int no);
		
		//QnA답글 입력
		public int insertReply(Reply r);
		
		//해당 QnA게시글의 댓글리스트 호출
		public List<Reply> getThisBoardReply(String boardno);
		
		//QnA답글 업데이트
		public int updateReply(Reply r);
		
		//QnA답글 삭제
		public int deleteReply(int idx);

}
