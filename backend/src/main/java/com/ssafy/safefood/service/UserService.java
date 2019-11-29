package com.ssafy.safefood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.safefood.dto.Member;
import com.ssafy.safefood.dto.User;

@Service
public class UserService {

	@Autowired
	MemberService ms;

	public Member signin(String email, String password) {
		Member result = null;
		try {
			// 로그인 정보 확인 후 세션에 등록
			result = ms.login(email, password);
			if (result != null) {
				return result;
			}
		} catch (RuntimeException e) {
			throw new RuntimeException("로그인 에러 (일치 정보 없음)");
		}
		return result;
	}

	public String getServerInfo() {
		return "MADE BY DH";
	}
}
