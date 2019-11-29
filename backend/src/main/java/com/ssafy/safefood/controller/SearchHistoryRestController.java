package com.ssafy.safefood.controller;

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

import com.ssafy.safefood.dto.SearchHistory;
import com.ssafy.safefood.service.SearchHistoryService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin({"*"})
public class SearchHistoryRestController {
	
	@Autowired
	SearchHistoryService service;
	
	@GetMapping("/SearchHistory")
	@ApiOperation("검색어 히스토리 전체목록 가져오기")
	public ResponseEntity<Map<String,Object>> historyList(@RequestParam String id){
		try {
			
			List<SearchHistory> list=service.searchAll(id);
			return response(list,HttpStatus.OK,true);
		} catch (RuntimeException e) {
			log.trace("history get Error: {}",e);
			return response("history get Error",HttpStatus.CONFLICT,false);
		}
	}
	
	@PostMapping("/SearchHistory")
	@ApiOperation("검색어 히스토리에 검색어 삽입")
	public ResponseEntity<Map<String,Object>> insertHistory(@RequestBody SearchHistory sh){
		if(service.search(sh)==null) {
			System.out.println("insert");
			try {
				int result=service.insert(sh);
				return response("insert success",HttpStatus.OK,true);
				
			} catch (RuntimeException e) {
				return response("insert error",HttpStatus.CONFLICT,false);
			}
		} else { //이미 검색어가 있을 경우 update로 이동
			return updateHistory(sh);
		}
	}
	
	@PutMapping("/SearchHistory")
	@ApiOperation("검색어 히스토리 검색어 카운트 1증가")
	public ResponseEntity<Map<String,Object>> updateHistory(@RequestBody SearchHistory sh){
		if(service.search(sh)==null) { //검색어가 없는 경우 insert실행
			return insertHistory(sh);
		} else {
			try {
				int result=service.update(sh);
				return response("update success",HttpStatus.OK,true);
				
			} catch (RuntimeException e) {
				return response("update error",HttpStatus.CONFLICT,false);
			}
		}
	}
	
	@DeleteMapping("/SearchHistory/{word}")
	public ResponseEntity<Map<String,Object>> deleteHistory(@PathVariable String word){
		try {		
			if(service.select(word)==null) {
				return response("삭제하려는 검색어가 존재하지 않습니다.",HttpStatus.CONFLICT,false);
			} else {
				int result=service.delete(word);
				if(result==1) {
					return response("검색어 삭제 성공",HttpStatus.OK,true);
				} else {
					throw new RuntimeException();
				}
			}
		} catch (Exception e) {
			log.trace("Delete Error: {}",e);
			return response("삭제 오류 발생",HttpStatus.CONFLICT,false);
		}
	}
	
	
	

	private ResponseEntity<Map<String, Object>> response(Object data, HttpStatus httpStatus, boolean status){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("data",data);
		resultMap.put("status",status);
		
		
		//상태와 함께 Map반환
		return new ResponseEntity<>(resultMap,httpStatus);
	}
}
