package demo;

import java.util.ArrayList;
import java.util.List;

import demo.DBUtil;

public class TestDBUtil {

	public static void main(String[] args) {
		DBUtil dbUtil = new DBUtil();
		dbUtil.getConnection();
		
		/*
		 * CREATE TABLE `studentdemo` ( `id` int(11) NOT NULL AUTO_INCREMENT, `stu_name`
		 * varchar(20) DEFAULT NULL, `age` int(11) DEFAULT NULL, `gender` int(11)
		 * DEFAULT NULL, `address` varchar(20) DEFAULT NULL, PRIMARY KEY (`id`) )
		 * ENGINE=InnoDB DEFAULT CHARSET=utf8;
		 */
		String sql = "insert into studentdemo(stu_name, age, gender, address) values(?,?,?,?)";
		int gender;
		for(int i = 1; i < 20; i++) {
			List<Object> params = new ArrayList<>();
			params.add("name-" + i);
			params.add(i);
			gender = i%2 + 1;
			params.add(gender);
			params.add("address-" + i);
			
			try {
				dbUtil.updateByPreparedStatement(sql, params);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("error to update student");
			}
		}
		
		System.out.println("done");
		dbUtil.release();

	}

}
