package com.ssafy.safefood.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Nutrition {
	private int number;
	private String name;
	private int amount;
	private Date date;
	
}
