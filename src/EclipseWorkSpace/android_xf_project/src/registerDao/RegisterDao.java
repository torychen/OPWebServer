package registerDao;

import java.util.List;

import register.service.registerService;
import Dbjdbc.jdbcutils;

//数据访问层
public class RegisterDao implements registerService {

	jdbcutils utils = null;

	public RegisterDao() {
		// TODO Auto-generated constructor stub
		utils = new jdbcutils();
	}

	/*
	 * (non-Javadoc) 完成对用户的注册
	 * 
	 * @see register.service.registerService#registerUser(java.util.List)
	 */
	public boolean registerUser(List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag = false;
		utils.getConnection();// 连接到jdbc数据库的对象
		String sql = "insert into userinfo2 (username,password,realname) values(?,?,?)";
		try {

			flag = utils.UpdateByPreparedStatement(sql, params);// 在数据库中加载数据

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (utils != null) {
				utils.release();
			}
		}

		return flag;
	}

}
