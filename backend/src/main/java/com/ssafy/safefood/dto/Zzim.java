package com.ssafy.safefood.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.safefood.dto.Food;

import lombok.Data;

@Data
public class Zzim {
	int no;
	String id;
	int code;
	String regdate;
	Food food;
	
	public Zzim(int no, String id, int code, Food food) {
		this.no = no;
		this.id = id;
		this.code = code;
		this.food = food;
	}
	public Zzim(int no, String id, int code) {
		super();
		this.no = no;
		this.id = id;
		this.code = code;
	}
	public Zzim(String id, int code) {
		super();
		this.id = id;
		this.code = code;
	}
	public Zzim() {
		super();
	}
	public Zzim(int no, String id, int code, String regdate, Food food) {
		super();
		this.no = no;
		this.id = id;
		this.code = code;
		this.regdate = regdate;
		this.food = food;
	}
	
	

}
