package org.jystudio.dbutil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.set.MapBackedSet;

public class TestDBUtil {
	
	public static void main(String[] args) {
		DBUtil dbUtil = new DBUtil();
		if (!dbUtil.getConnection()) {
			System.out.println("getConnection error!");
			return;
		}
		
		/*//Test add user. Pass.
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
		}*/
		
		//Test query db
		String sql = "select * from question";
		List<Map<String, Object>> maps = null;
		try {
			 maps = dbUtil.findMoreList(sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (maps != null) {
			System.out.println("maps size: " + maps.size());
			for (Map<String, Object> map : maps) {
				for (String key : map.keySet()) {
					System.out.println("key is: " + key + " value is " + map.get(key).toString());
				}
			}
			
			System.out.println("---");
			
		} else {
			System.out.println("no data return!");
		}
		
		 
		
		dbUtil.release();
	}
}
