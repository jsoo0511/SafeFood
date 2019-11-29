package com.ssafy.safefood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Eat {
	private int idx;
	private String id;
	private int code;
	private int amount;
	private String foodname;
	private String regdate;
	Food food;
	
	public Eat(int idx, String id, int code, int amount, String foodname) {
		this.idx = idx;
		this.id = id;
		this.code = code;
		this.amount = amount;
		this.foodname = foodname;
	}
	
	
}
