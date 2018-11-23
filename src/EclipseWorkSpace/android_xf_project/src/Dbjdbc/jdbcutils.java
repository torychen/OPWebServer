package Dbjdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Statement;

///////////////////////////////////////////////////////////////////////////////
//�������ڹ���һ����ѯ������ʱ�� �ҿ����ȹ���һ���� ���ڱ�����Ҫ�����ݿ��л�õ�Ԫ�� 
//��дһ������ʵ�� ����Ӧ�Ĺ��� ���ѯ������¼ �����ǵ�����¼
//

////////////////////////////////////////////
public class jdbcutils {
	// ///////////////////////////////////////////////////////////////////////
	// ���������ֵ�������ϵͳ��ص�
	// �������ݿ���û���
	private static final String UESRNAME = "root";// ��������Ǻ��ҵĴ��������ݿ�Ķ�����ص�
													// ���Բ����������
	// �������ݿ������
	private static final String PASSWORD = "DB123456";// ע����������벻��������� ����Ҫ���ұ������������
	// ////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////
	// �������ݿ�ļ�������
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	// �������ݿ�ĵ�ַ
	private static final String URL = "jdbc:mysql://localhost:3306/op.db";
	// ///////////////////////////////////////////////////////////////////////
	// �������ݿ������
	private Connection connection;
	// �������ݿ��ִ�ж���
	private PreparedStatement psStatement;
	// �����ѯ���صĽ������
	private ResultSet resultSet;
	// ʵ��������
	private Statement statement;

	public jdbcutils() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName(DRIVER);
			System.out.println("ע�������ɹ�!!!");
			System.out.println("java.version="
					+ System.getProperty("java.version"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// ////////////////////////////////
	// �������ݿ������
	public Connection getConnection() {

		try {
			// ���һ�����صĶ��� ������������ӵ������ݿ��һ������ ����������������������ݿ�
			connection = DriverManager.getConnection(URL, UESRNAME, PASSWORD);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return connection;

	}

	public boolean deleteByBacth(String[] sql) throws SQLException {
		boolean flag = false;
		statement = (Statement) connection.createStatement();// ����������
		if (sql != null) {
			for (int i = 0; i < sql.length; i++) {
				statement.addBatch(sql[i]);//���ӵ�
			}
		}
		int[] count = statement.executeBatch();// ���ش��������
		if (count != null) {// Ҫ�Ƿ��ص�������Ϊ��
			flag = true;
		}
		return flag;
	}

	public Map<String, Object> findSimpleresult(String sql, List<Object> params)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();

		int index = 1;
		psStatement = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				psStatement.setObject(index++, params.get(i));
			}
		}
		resultSet = psStatement.executeQuery();// ���ص�����¼
		ResultSetMetaData metaData = resultSet.getMetaData();// ����е���Ϣ
		int col_lens = metaData.getColumnCount();
		while (resultSet.next()) {
			for (int i = 0; i < col_lens; i++) {
				String col_name = metaData.getColumnName(i + 1);// Ϊʲôô��i+1��
				// ��Ϊ����id��
				Object col_values = resultSet.getObject(col_name);
				if (col_values == "") {
					col_values = "";
				}
				map.put(col_name, col_values);
			}

		}

		return map;
	}

	/**
	 * ��ɶ����ݿ��ɾ�� ��� ���޸ĵĲ���
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean UpdateByPreparedStatement(String sql, List<Object> params)
			throws SQLException {
		boolean flag = false;
		int result = -1;// ��ʾ���ݿ��в���Ӱ�������
		psStatement = connection.prepareStatement(sql);// ������ݿ���䴫�����Ķ���
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {// ������Ӧ����
				psStatement.setObject(index++, params.get(i));// ���У���һ����ָ��SQL����еĵڼ����������ڶ�����Ҫ���õ�ֵ
			}

		}
		result = psStatement.executeUpdate();// ִ�����ݿ����
		flag = result > 0 ? true : false;// ��÷��ص�Ӱ�������
		return flag;

	}

	public List<Map<String, Object>> findMoreList(String sql,
			List<Object> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		psStatement = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {

				psStatement.setObject(index++, params.get(i));
			}
		}
		resultSet = psStatement.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int col_lens = metaData.getColumnCount();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < col_lens; i++) {

				String col_name = metaData.getColumnName(i + 1);
				Object col_values = resultSet.getObject(col_name);
				if (col_values == null) {
					col_values = "";
				}
				map.put(col_name, col_values);
			}
			list.add(map);
		}

		return list;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////
	// jdbc�ķ�װ�����ǿ����÷�����Ƶ�
	public <T> T findSimpleRefresult(String sql, List<Object> params,
			Class<T> cls) throws Exception {
		T resultObject = null;
		int index = 1;
		psStatement = connection.prepareStatement(sql);

		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {

				psStatement.setObject(index++, params.get(i));
			}
		}
		resultSet = psStatement.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();//
		int lens = metaData.getColumnCount();// �������
		while (resultSet.next()) {
			resultObject = cls.newInstance();// ͨ��������ƴ���һ��ʵ��
			for (int i = 0; i < lens; i++) {
				String col_name = metaData.getColumnName(i + 1);
				Object col_values = resultSet.getObject(col_name);

				if (col_values == null) {
					col_values = "";
				}
				Field field = cls.getDeclaredField(col_name);
				field.setAccessible(true);// ��javabean��private����Ȩ��
				field.set(resultObject, col_values);// ��col_values��ֵ��resultObject
			}
		}

		return resultObject;

	}

	public <T> List<T> findmoreRefResult(String sql,
			List<Map<String, Object>> params, Class<T> cls) throws Exception {

		List<T> list = new ArrayList<T>();
		int index = 1;
		psStatement = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				psStatement.setObject(index++, params.get(i));
			}

		}

		resultSet = psStatement.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int col_len = metaData.getColumnCount();
		while (resultSet.next()) {
			T resultRefobject = cls.newInstance();
			for (int i = 0; i < col_len; i++) {
				String col_name = metaData.getColumnName(i + 1);
				Object col_values = resultSet.getObject(col_name);
				if (col_values == null) {
					col_values = "";

				}
				Field field = cls.getDeclaredField(col_name);//
				field.setAccessible(true);// ��ô��ⲿд�뵽����private��Ա�ķ���
				field.set(resultRefobject, col_values);

			}
			list.add(resultRefobject);
		}

		return list;
	}

	public void release() {

		if (connection != null) {
			try {
				connection.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (psStatement != null) {
			try {
				psStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// public static void main(String[] args) {
	// jdbcutils jdbcutils = new jdbcutils();
	// jdbcutils.getConnection();// ������Ӷ���
	//
	// // String sql = "insert into userinfo(username,password)  values(?,?)";
	// // List<Object> params = new ArrayList<Object>();
	// // params.add("zhangsan");
	// // params.add("12345");
	// //
	// // try {
	// // boolean flag = jdbcutils.UpdateByPreparedStatement(sql, params);
	// // System.out.println("-----UpdateByPreparedStatement------>>" + flag);
	// // } catch (SQLException e) { // TODO Auto-generated catchblock
	// // e.printStackTrace();
	// // }
	// // ////////////////////////////////////////////////
	// // select * from userinfo ��ʾ���Ƿ��ض��м�¼ select * from userinfo where
	// // id=?��ʾ���Ƿ��ص��м�¼
	// // String sql = "select * from userinfo where id=?";//���в���
	// // List<Object> params = new ArrayList<Object>();
	// // params.add(1);
	//
	// String sql = "select * from userinfo ";// ���в���
	//
	// try {
	// // userinfo userinfo1 = jdbcutils.findSimpleRefresult(sql, params,
	// // userinfo.class);
	// // System.out.println("------>>" + userinfo1);
	//
	// // /*List<userinfo> list = jdbcutils.findmoreRefResult(sql, null,
	// // userinfo.class);*/
	// // System.out.println(list);
	//
	// } catch (Exception e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// } finally {
	// jdbcutils.release();
	// }
	//
	// }
};
