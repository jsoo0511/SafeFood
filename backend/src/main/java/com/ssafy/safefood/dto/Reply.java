package com.ssafy.safefood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
	int idx;
	String id;
	int boardno;
	String reply;
	String regdate;
	
	public Reply(int idx) {
		this.idx = idx;
	}
}
