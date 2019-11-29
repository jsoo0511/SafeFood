package com.ssafy.safefood.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ssafy.safefood.dto.Board;
import com.ssafy.safefood.dto.PageBean;

public class BoardDaoImplBackUp{
	public Map<String, Object> searchAll(Connection con, PageBean bean) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String key = bean.getKey();
			String word = bean.getWord();
			int interval = bean.getInterval();
			int pageNo = bean.getPageNo();
			StringBuilder sql = new StringBuilder(100);
			StringBuilder where = new StringBuilder();
			if (key != null && !key.equals("all") && word != null && !word.trim().equals("")) {
				if (key.equals("id")) {
					where.append(" where id = ? ");
				} else if (key.equals("title")) {
					where.append(" where title like  ? ");
				} else if (key.equals("contents")) {
					where.append(" where contents like  ? ");
				}
			}

			sql.append(" select (select count(*) from board " + where + " ) as total, no, id, title, date_format(regdate, '%Y-%m-%d') as regdate from board   ");
			sql.append(where);
			sql.append(" order by no desc limit ?,? ");
			stmt = con.prepareStatement(sql.toString());
			int idx = 1;
			if (key != null && !key.equals("all") && word != null && !word.trim().equals("")) {
				if (key.equals("id")) {
					stmt.setString(idx++, word);
					stmt.setString(idx++, word);
				} else {
					stmt.setString(idx++, "%" + word + "%");
					stmt.setString(idx++, "%" + word + "%");
				}
			}
			stmt.setInt(idx++, (pageNo - 1) * interval);
			stmt.setInt(idx++, interval);
			rs = stmt.executeQuery();
			Map<String, Object> map = new HashMap<>();
			ArrayList<Board> boards = new ArrayList<>(interval);
			map.put("list", boards);
			boolean isFirst = true;
			while (rs.next()) {
				if (isFirst) {
					map.put("totalNumber", rs.getInt("total"));
					isFirst = false;
				}
				boards.add(new Board(rs.getInt("no"), rs.getString("id"), rs.getString("title"), rs.getString("regdate")));
			}
			System.out.println("dao: " + map);
			return map;
		} finally {
		}
	}

	public int count(Connection con, PageBean bean) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String key = bean.getKey();
			String word = bean.getWord();
			StringBuilder sql = new StringBuilder(100);
			sql.append(" select count(*) as cnt from board   ");
			if (key != null && word != null && !word.trim().equals("")) {
				if (key.equals("id")) {
					sql.append(" where id = ? ");
				} else if (key.equals("title")) {
					sql.append(" where title like  ? ");
				} else if (key.equals("contents")) {
					sql.append(" where contents like  ? ");
				}
			}
			stmt = con.prepareStatement(sql.toString());
			int idx = 1;
			if (key != null && word != null && !word.trim().equals("")) {
				if (key.equals("id")) {
					stmt.setString(idx++, word);
				} else {
					stmt.setString(idx++, "%" + word + "%");
				}
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("cnt");
			}
		} finally {
		}
		return 0;
	}

}
