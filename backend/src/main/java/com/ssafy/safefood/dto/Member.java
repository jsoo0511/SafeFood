package com.ssafy.safefood.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Member {
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String email;
	private String allergy;
	
	private List<Eat> eatlist;
	
	public List<Eat> getEatlist() {
		return eatlist;
	}

	public Member(String id, String pw, String name, String phone, String email, String allergy) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.allergy = allergy;
	}
}
