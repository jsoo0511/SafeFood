package com.ssafy.safefood.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.safefood.dto.Board;
import com.ssafy.safefood.dto.PageBean;
import com.ssafy.safefood.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
public class BoardRestController {

	@Autowired
	BoardService bs;


	@GetMapping("/Board")
	@ApiOperation("페이징구현된 board리스트 가져오기")
	public ResponseEntity<Map<String, Object>> NoticePagingList(@RequestParam int curPage) {
	    int listCnt=bs.count();//board전체 갯수
	    
	    PageBean bean=new PageBean(listCnt,curPage);
      List<Board> boardList=bs.searchPaging(bean);
      return response(boardList,listCnt/10+1,HttpStatus.OK,true);

	}


	@GetMapping("/Board/{no}")
	@ApiOperation("해당 공지사항 상세정보") /**로그인 되지 않은 사람은 공지사항 열람을 못하게 해야하므로 로그인 여부를 따지기위해 session 로그인 값이 필요함*/
	public ResponseEntity<Map<String, Object>> showDetailNoticeBoard(@PathVariable String no) {
		PageBean bean=new PageBean();
		try {
			int larger=bs.LargerThanMe(Integer.parseInt(no));
			int curPageNo=(larger/bean.getInterval())+1;//현재 위치하는 페이지=(나보다 no번호 큰애들 수/interval)+1
			return response(bs.search(no),curPageNo, HttpStatus.OK, true);
		} catch (RuntimeException e) {
			return response("Get BoardDetail Error","null", HttpStatus.CONFLICT, true);
		}
	}

	// 글쓰기 DB처리
	@PostMapping("/Board") /** 작성자 입력을 위해서 session에서 로그인 값 가져와야됨*/
	public ResponseEntity<Map<String, Object>> InsertNoticeBoard(@RequestBody Board board) {
		String loginuser = "tempUser";// 일단 임시값 넣어놓고 나중에 session값 처리 가능하면 그때 session처리
		Board temp = new Board(loginuser, board.getTitle(), board.getContents());

		try {
			return response(bs.insertBoard(board),"null", HttpStatus.OK, true);
			// int result = bs.insertBoard(temp);
		} catch (RuntimeException e) {
			log.trace("Board insert error : {}", e);
			throw e;
		}
	}

	// 글 수정 동작
	@PutMapping("/Board")  /**수정권한을 부여하기 위해서 session필요함*/
	public ResponseEntity<Map<String, Object>> UpdateNoticeBoard(@RequestBody Board board) {
		try {
			return response(bs.updateBoard(board), "null",HttpStatus.OK, true);
		} catch (RuntimeException e) {
			log.trace("Board update error : {}", e);
			throw e;
		}
	}

	// 글 삭제 동작
	@DeleteMapping("/Board/{no}") /**삭제권한을 부여하기 위해서 session필요함*/
	public ResponseEntity<Map<String, Object>> DeleteNoticeBoard(@PathVariable String no) {
		try {
			return response(bs.deleteBoard(no),"null", HttpStatus.OK, true);
		} catch (RuntimeException e) {
			log.trace("Board delete error : {}", e);
			throw e;
		}
	}


	private ResponseEntity<Map<String, Object>> response(Object data, Object dataCount,HttpStatus httpStatus, boolean status) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("data", data);
		resultMap.put("dataCount", dataCount);
		resultMap.put("status", status);

		// 상태와 함께 Map반환
		return new ResponseEntity<>(resultMap, httpStatus);
	}

}