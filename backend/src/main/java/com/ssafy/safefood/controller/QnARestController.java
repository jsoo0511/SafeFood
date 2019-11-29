package com.ssafy.safefood.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.safefood.dto.Board;
import com.ssafy.safefood.dto.PageBean;
import com.ssafy.safefood.dto.Reply;
import com.ssafy.safefood.service.BoardService;
import com.ssafy.safefood.service.QnABoardService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin({"*"})
public class QnARestController {
	@Autowired
	QnABoardService bs;

	@GetMapping("/QnABoard")
	@ApiOperation("전체 QnABoard리스트 Paging 구현됨")
	public ResponseEntity<Map<String,Object>> showListQnABoard(@RequestParam int curPage) {
		int listCnt=bs.count();
		try {
			PageBean bean = new PageBean(listCnt,curPage);
			List<Board> list=bs.searchQnAPaging(bean);
			
			return response(list,listCnt/10+1,HttpStatus.OK,true);
		} catch (RuntimeException e) {
			return response("Get QnABoard Error","null",HttpStatus.CONFLICT,true);
		}
		
	}

	//QnA해당 게시글 상세정보
	@GetMapping("/QnABoard/{no}") /**로그인 되지 않은 사용자의 게시글 상세정보 열람을 막기 위해서 로그인유무 확인을 위해 session값이 필요함*/
	@ApiOperation("해당번호 QnABoard의 상세 정보 dataCount로 현재 게시글이 몇페이지에 존재하는지의 정보, 댓글리스트가 넘어옴")
	public ResponseEntity<Map<String,Object>> showDetailQnABoard(@PathVariable String no) {
		//model.addAttribute("detailboard", bs.search(no));
		PageBean bean=new PageBean();
		try {
			int larger=bs.LargerThanMe(Integer.parseInt(no));
			int curPageNo=(larger/bean.getInterval())+1;//현재 위치하는 페이지=(나보다 no번호 큰애들 수/interval)+1	
			List<Reply> list=bs.getThisBoardReply(no); //해당 게시글의 댓글 목록
			
			Map<String,Object> resultMap = new HashMap<String, Object>();
			resultMap.put("data",bs.searchQnABoard(no)); //게시글의 상세정보
			resultMap.put("dataCount", curPageNo); //게시글이 현재 위치하는 페이지
			resultMap.put("dataReply",list); //해당 게시글의 댓글리스트
			resultMap.put("status",true);
			
			//상태와 함께 Map반환
			return new ResponseEntity<>(resultMap,HttpStatus.OK);
			
			
			//return response(bs.searchQnABoard(no),curPageNo,HttpStatus.OK,true);
		} catch (RuntimeException e) {
			return response("Get BoardDetail Error","null",HttpStatus.CONFLICT,true);
		}
	}


	// 글쓰기 DB처리
	@PostMapping("/QnABoard")   /**글쓰기 권한을 주기 위해 session값이 필요함*/
	public ResponseEntity<Map<String,Object>> InsertQnABoard(@RequestBody Board board) {
	//	String loginuser = (String) session.getAttribute("login");
      String loginuser="tempUser";//일단 임시값 넣어놓고 나중에 session값 처리 가능하면 그때 session처리
		Board temp = new Board(loginuser, board.getTitle(), board.getContents());

		try {
			return response(bs.insertQnABoard(board),"null",HttpStatus.OK,true);
			//int result = bs.insertBoard(temp);
		} catch (RuntimeException e) {
			log.trace("Board insert error : {}", e);
			throw e;
		}
	}

	// 총 게시판 갯수
	@GetMapping("/QnAcount")
	@ApiOperation("QnA게시글 총 갯수")
	public ResponseEntity<Map<String,Object>> showSearchQnABoard() {
		return response(bs.count(),"null",HttpStatus.OK,true);
	}

	// 글 수정 동작
	@PutMapping("/QnABoard")
	@ApiOperation("QnA게시판 전체 게시물 갯수")   
	public ResponseEntity<Map<String,Object>> UpdateQnABoard(@RequestBody Board board) {
		try {
			return response(bs.updateQnABoard(board),"null",HttpStatus.OK,true);
		} catch (RuntimeException e) {
			log.trace("Board update error : {}", e);
			throw e;
		}
	}

	// 글 삭제 동작
	@DeleteMapping("/QnABoard/{no}")    /**글 삭제 권한을 위해서 session값이 필요함*/
	public ResponseEntity<Map<String,Object>> DeleteQnABoard(@PathVariable String no) {
		try {
			return response(bs.deleteQnABoard(no),"null",HttpStatus.OK,true);
		} catch (RuntimeException e) {
			log.trace("Board delete error : {}", e);
			throw e;
		}
	}
	
	//QnA답글 가져오기
	@GetMapping("/QnAReply")
	@ApiOperation("해당 게시글의 댓글리스트 가져옴")
	public ResponseEntity<Map<String,Object>> GetReplyThisQnABoard(@RequestParam String boardno){
		try {
			List<Reply> list=bs.getThisBoardReply(boardno);
			if(list==null)
				return response("해당 게시글의 댓글이 없습니다.","null",HttpStatus.OK,true);
			else
				return response(list,list.size(),HttpStatus.OK,true); //댓글리스트와 댓글 수 출력 
		} catch (RuntimeException e) {
			log.trace("Get QnAReply Error: {}",e);
			return response("Get QnAReply Error","null",HttpStatus.CONFLICT,true);
		}
	}
	
	//QnA답글 입력
	@PostMapping("/QnAReply") /**답글 입력 권한을 위해 로그인 유무를 판단하기 위한 session값이 필요함*/
	@ApiOperation("답글 입력")
	public ResponseEntity<Map<String,Object>> InsertReplyQnABoard(@RequestBody Reply r){
		try {
			int result=bs.insertReply(r);
			if(result==1)
				return response("답글 insert 성공","null",HttpStatus.OK,true);
			else
				return response("답글 insert 실패","null",HttpStatus.CONFLICT,true);
		} catch (RuntimeException e) {
			log.trace("Insert QnAReply Error: {}",e);
			return response("Insert QnAReply Error","null",HttpStatus.CONFLICT,true);
		}
	}
	
	//QnA답글 수정
	@PutMapping("/QnAReply")
	@ApiOperation("답글 수정")
	public ResponseEntity<Map<String,Object>> UpdateReplyQnABoard(@RequestBody Reply r){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //댓글 업데이트 시 날짜도 업데이트한 날짜로 변경해줘야 됨
		Calendar cal = Calendar.getInstance();
		r.setRegdate(dateFormat.format(cal.getTime()));
		try {
			int result=bs.updateReply(r);
			if(result==1)
				return response("답글 update 성공","null",HttpStatus.OK,true);
			else
				return response("답글 update 실패","null",HttpStatus.CONFLICT,true);
		} catch (RuntimeException e) {
			log.trace("Update QnAReply Error: {}",e);
			return response("Update QnAReply Error","null",HttpStatus.CONFLICT,true);
		}
	}
	
	//QnA답글 삭제
	@DeleteMapping("/QnAReply/{idx}")
	@ApiOperation("답글 삭제")
	public ResponseEntity<Map<String,Object>> DeleteReplyQnABoard(@PathVariable int idx){
		Reply r = new Reply(idx);
		try {
			int result=bs.deleteReply(idx);
			if(result==1)
				return response("답글 delete 성공","null",HttpStatus.OK,true);
			else
				return response("답글 delete 실패","null",HttpStatus.CONFLICT,true);
		} catch (RuntimeException e) {
			log.trace("Delete QnAReply Error: {}",e);
			return response("Delete QnAReply Error","null",HttpStatus.CONFLICT,true);
		}
	}
	
	private ResponseEntity<Map<String, Object>> response(Object data, Object dataCount,HttpStatus httpStatus, boolean status){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("data",data);
		resultMap.put("dataCount", dataCount);
		resultMap.put("status",status);
		
		
		//상태와 함께 Map반환
		return new ResponseEntity<>(resultMap,httpStatus);
	}
}
