//package com.ssafy.safefood.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.ssafy.safefood.dto.Board;
//import com.ssafy.safefood.dto.PageBean;
//import com.ssafy.safefood.service.BoardService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Controller
//@Slf4j
//public class BoardController {
//
//	@Autowired
//	BoardService bs;
//
//	@GetMapping("/listBoard")
//	public String showListBoard(Model model, HttpSession session) {
//		PageBean bean = new PageBean("all", null, null);
//		model.addAttribute("listboard", bs.searchAll(bean));
//		session.setAttribute("pagenow", "notice");
//		return "board/listBoard";
//	}
//
//	@GetMapping("/detailBoard")
//	public String showDetailBoard(@RequestParam String no, Model model) {
//		model.addAttribute("detailboard", bs.search(no));
//		return "board/detailBoard";
//	}
//
//	// 글쓰기 페이지
//	@GetMapping("/insertBoard")
//	public String showInsertBoard(HttpSession session) {
//		return "board/insertBoard";
//	}
//
//	// 글쓰기 DB처리
//	@PostMapping("/insertBoard")
//	public String InsertBoard(@RequestParam String title, @RequestParam String contents, HttpSession session) {
//		String loginuser = (String) session.getAttribute("login");
//
//		Board temp = new Board(loginuser, title, contents);
//
//		try {
//			int result = bs.insertBoard(temp);
//			return "redirect:listBoard";
//		} catch (RuntimeException e) {
//			log.trace("Board insert error : {}", e);
//			throw e;
//		}
//	}
//
//	// 검색?
//	@GetMapping("/searchBoard")
//	public String showSearchBoard() {
//		return "board/searchBoard";
//	}
//
//	// 글 수정 페이지
//	@GetMapping("/updateBoard")
//	public String showUpdateBoard(@RequestParam String no, Model model) {
//		Board ret = bs.search(no);
//		model.addAttribute("no", no);
//		model.addAttribute("detail", ret);
//		return "board/updateBoard";
//	}
//
//	// 글 수정 동작
//	@PostMapping("/updateBoard")
//	public String UpdateBoard(@RequestParam int no, @RequestParam String title, @RequestParam String contents) {
//		Board board = new Board(no, title, contents);
////		System.out.println("id: " + no);
//		try {
//			bs.updateBoard(board);
//			return "redirect:listBoard";
//		} catch (RuntimeException e) {
//			log.trace("Board update error : {}", e);
//			throw e;
//		}
//	}
//
//	// 글 삭제 동작
//	@GetMapping("/deleteBoard")
//	public String DeleteBoard(@RequestParam String no) {
//		try {
//			bs.deleteBoard(no);
//			return "redirect:listBoard";
//		} catch (RuntimeException e) {
//			log.trace("Board delete error : {}", e);
//			throw e;
//		}
//	}
//
//	//
//	@ResponseBody
//	@GetMapping("/listBoardData") //pagination시 가져올  board데이터들
//	public List<Board> listBoardData(@RequestParam String key, @RequestParam String word,
//			@RequestParam String pageNumber) {
//		PageBean bean = new PageBean(key, word, pageNumber);
//		List<Board> result = bs.searchAll(bean);
//		return result;
//	}
//
//}
