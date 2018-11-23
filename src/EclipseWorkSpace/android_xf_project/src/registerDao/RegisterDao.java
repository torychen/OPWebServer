package registerDao;

import java.util.List;

import register.service.registerService;
import Dbjdbc.jdbcutils;

//���ݷ��ʲ�
public class RegisterDao implements registerService {

	jdbcutils utils = null;

	public RegisterDao() {
		// TODO Auto-generated constructor stub
		utils = new jdbcutils();
	}

	/*
	 * (non-Javadoc) ��ɶ��û���ע��
	 * 
	 * @see register.service.registerService#registerUser(java.util.List)
	 */
	public boolean registerUser(List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag = false;
		utils.getConnection();// ���ӵ�jdbc���ݿ�Ķ���
		String sql = "insert into userinfo2 (username,password,realname) values(?,?,?)";
		try {

			flag = utils.UpdateByPreparedStatement(sql, params);// �����ݿ��м�������

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
