package login.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Dbjdbc.jdbcutils;

import login.service.Loginservice;

public class LoginDao implements Loginservice {
	private jdbcutils jdbcutils;

	public LoginDao() {
		// TODO Auto-generated constructor stub
		jdbcutils = new jdbcutils();
	}

	public boolean Login(List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			jdbcutils.getConnection();// 连接数据库
			String sql = "select * from userinfo2 where username=? and password=?";
			Map<String, Object> map = jdbcutils.findSimpleresult(sql, params);
			flag = !map.isEmpty() ? true : false;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcutils.release();// 最后一定要释放jdbc的对象
		}

		return flag;
	}
}
