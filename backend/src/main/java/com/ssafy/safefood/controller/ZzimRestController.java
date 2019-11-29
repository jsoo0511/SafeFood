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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.safefood.dto.Zzim;
import com.ssafy.safefood.service.ZzimService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
public class ZzimRestController {

	@Autowired
	ZzimService service;

	@PostMapping("/Zzim")
	@ApiOperation("찜목록추가") /** 찜목록 추가 권한을 위해 사용자 확인을 위한 session값이 필요함 */
	public ResponseEntity<Map<String, Object>> ZzimInsert(@RequestBody Zzim z) {
		System.out.println("zzz" + z);
		// zzim목록에 이미 들어있는 경우 추가해줄 필요없다.
//		Zzim z=new Zzim(id,code);
		Zzim check = service.check(z);
		if (check != null)
			return response("이미 찜목록에 있습니다.", HttpStatus.CONFLICT, false);

		try {
			int result = service.insert(z);
			if (result == 1) {
				return response("1", HttpStatus.OK, true);
			} else {
				return response("Insert Error", HttpStatus.CONFLICT, false);
			}
		} catch (Exception e) {
			return response("Insert RumtimeError", HttpStatus.CONFLICT, false);
		}
	}

	@GetMapping("/Zzim/{id}")
	@ApiOperation("찜목록 전체확인") /** 찜목록 전체 확인 권한을 위해 사용자 확인을 위한 session값이 필요함 */
	public ResponseEntity<Map<String, Object>> ZzimSelect(@PathVariable String id) {
		try {
			if (id.equals("0")) {

			}
			List<Zzim> list = service.select(id);
			return response(list, HttpStatus.OK, true);
		} catch (RuntimeException e) {
			log.trace("select zzim Runtime Error: {}", e);
			return response("select Rumtime Error zzim", HttpStatus.CONFLICT, false);
		}
	}

	@DeleteMapping("/Zzim")
	@ApiOperation("찜목록 삭제") /** 찜목록 삭제 권한을 위해 사용자 확인을 위한 session값이 필요함 */
	public ResponseEntity<Map<String, Object>> ZzimDelete(@RequestParam String id, @RequestParam int code) {
		Zzim z = new Zzim(id, code);
		try {
			int result = service.delete(z);
			if (result == 1) {
				return response("1", HttpStatus.OK, true);
			} else {
				return response("delete Error", HttpStatus.CONFLICT, false);
			}
		} catch (RuntimeException e) {
			return response("delete RumtimeError", HttpStatus.CONFLICT, false);
		}
	}

	@GetMapping("/Zzim") /** 찜목록 유무 판별 권한을 위해 사용자 확인을 위한 session값이 필요함 */
	@ApiOperation("상품코드랑 아이디 받으면 그 상품이 찜되있는지 안되있는지 판별, 상품이 있으면 1 아니면 0 리턴")
	public ResponseEntity<Map<String, Object>> IsZzim(@RequestParam String id, @RequestParam int code) {
		try {

			Zzim temp = service.IsZzim(new Zzim(id, code));
			if (temp == null)
				return response(0, HttpStatus.OK, true);
			else
				return response(1, HttpStatus.OK, true);

		} catch (RuntimeException e) {
			return response("InZzim Error", HttpStatus.CONFLICT, false);
		}
	}

	private ResponseEntity<Map<String, Object>> response(Object data, HttpStatus httpStatus, boolean status) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("data", data);
		resultMap.put("status", status);

		// 상태와 함께 Map반환
		return new ResponseEntity<>(resultMap, httpStatus);
	}

}
