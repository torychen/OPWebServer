package org.jystudio.dbutil;

public class TestDBUtil {

	public static void main(String[] args) {
		DBUtil dbUtil = new DBUtil();
		if (!dbUtil.getConnection()) {
			System.out.println("getConnection error!");
			return;
		}
		
		dbUtil.release();
	}
}
