package org.jystudio.dbutil;

import java.util.ArrayList;
import java.util.List;

public class TestDBUtil {
	
	public static void main(String[] args) {
		DBUtil dbUtil = new DBUtil();
		if (!dbUtil.getConnection()) {
			System.out.println("getConnection error!");
			return;
		}
		
		//Test add user. Pass.
		String sql = "insert into user(name, password) values(?, ?)";
		List<Object> params = new ArrayList<>();
		params.add("test1");
		params.add("123");
		
		try {
			dbUtil.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error to update user.");
		}
		
		params.clear();
		
		//Test add question
		sql = "insert into question(body) values(?)";
		params.add("单链表插入节点");
		try {
			dbUtil.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error to update question.");
		}
		 
		
		dbUtil.release();
	}
}
