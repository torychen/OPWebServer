package demo;


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


public class DBUtil {
	// ///////////////////////////////////////////////////////////////////////
	// 这里的两个值是与你的系统相关的
	// 定义数据库的用户名
	private static final String UESRNAME = "root";
	// 定义数据库的密码
	private static final String PASSWORD = "DB123456";
	// 定义数据库的加载驱动
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	// 访问数据库的地址
	private static final String URL = "jdbc:mysql://localhost:3306/op.db?useSSL=true";
	// ///////////////////////////////////////////////////////////////////////
	// 定义数据库的连接
	private Connection connection;
	// 定义数据库的执行对象
	private PreparedStatement psStatement;
	// 定义查询返回的结果集合
	private ResultSet resultSet;
	// 实现批处理
	private Statement statement;

	public DBUtil() {
		try {
			Class.forName(DRIVER);
			//System.out.println("注册驱动成功!!!");
			//System.out.println("java.version=" + System.getProperty("java.version"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("注册驱动 error !!!");
		}
	}

	// ////////////////////////////////
	// 定义数据库的连接
	public boolean getConnection() {
		boolean flag = false;
		try {
			// 获得一个返回的对象 这个对象是连接到了数据库的一个对象 可以用这个对象来连接数据库
			connection = DriverManager.getConnection(URL, UESRNAME, PASSWORD);
			flag = true;
		} catch (Exception e) {
			System.out.println("Fail to getConnection!");
			e.printStackTrace();
		}
		return flag;
	}
	
	public int getRecordCount(String tableName) {
		int count = 0;
		
		String sql = "select count(*) from ";
		StringBuffer buffer = new StringBuffer(sql);
		buffer.append(tableName);
		
		//System.out.println(buffer.toString());
		
		try {
			psStatement = connection.prepareStatement(buffer.toString());
			resultSet = psStatement.executeQuery();// 返回单条记录
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Error no result get from the table: " + tableName);
			
		}
		
		return (count <= 0 ? 0 : count);
	}
	
	/**
	 * 执行查询操作
	 * sql sql语句
	 * params 执行参数
	 */
	public List<Map<String, Object>> findResult(String sql,List<?> params)throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		int index = 1;
		psStatement = connection.prepareStatement(sql);
		/*
		 * 填充查询语句的参数
		 */
		if(params != null && !params.isEmpty()){
			for(int i = 0;i<params.size();i++){
				psStatement.setObject(index ++, params.get(i));
			}
		}
		resultSet = psStatement.executeQuery();
		/*
		 * 通过ResultSetMetaData获取一个ResultSet的列的类型和关于列的属性信息，如列名、列数等
		 */
		ResultSetMetaData metaData = resultSet.getMetaData();
		//获取列的数量
		int cols_len = metaData.getColumnCount();
		/*
		 * 遍历resultSet
		 */
		while(resultSet.next()){
			Map<String,Object> map = new HashMap<String, Object>();
			for(int i= 0;i<cols_len;i++){
				//获取列名
				String cols_name = metaData.getColumnName(i+1);
				//根据列名获取列值
				Object cols_value = resultSet.getObject(cols_name);
				if(cols_value == null){
					cols_value ="";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}


	/**	
	 * to be discussed.
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteByBacth(String[] sql) throws SQLException {
		boolean flag = false;
		statement = (Statement) connection.createStatement();// 创建批处理
		if (sql != null) {
			for (int i = 0; i < sql.length; i++) {
				statement.addBatch(sql[i]);//增加到
			}
		}
		int[] count = statement.executeBatch();// 返回处理的行数
		if (count != null) {// 要是返回的行数不为空
			flag = true;
		}
		return flag;
	}

	/**
	 * query single result
	 */
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
		resultSet = psStatement.executeQuery();// 返回单条记录
		ResultSetMetaData metaData = resultSet.getMetaData();// 获得列的信息
		int col_lens = metaData.getColumnCount();
		while (resultSet.next()) {
			for (int i = 0; i < col_lens; i++) {
				String col_name = metaData.getColumnName(i + 1);// 为什么么是i+1呢
				// 因为还有id号
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
	 * 完成对数据库的删除 添加 和修改的操作
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean updateByPreparedStatement(String sql, List<Object> params)
			throws SQLException {
		boolean flag = false;
		int result = -1;// 表示数据库中操作影响的行数
		psStatement = connection.prepareStatement(sql);// 获得数据库语句传递语句的对象
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {// 操作相应的行
				psStatement.setObject(index++, params.get(i));// 其中，第一个是指你SQL语句中的第几个参数，第二个是要设置的值
			}

		}
		result = psStatement.executeUpdate();// 执行数据库语句
		flag = result > 0 ? true : false;// 获得返回的影响的行数
		return flag;

	}

	/**
	 * query more results
	 */
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
		if (resultSet != null && metaData != null) {
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
		} else {
			System.out.println("no date return from db.");
		}
		
		return list;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////
	// jdbc的封装机制是可以用反射机制的
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
		int lens = metaData.getColumnCount();// 获得列数
		while (resultSet.next()) {
			resultObject = cls.newInstance();// 通过反射机制创建一个实列
			for (int i = 0; i < lens; i++) {
				String col_name = metaData.getColumnName(i + 1);
				Object col_values = resultSet.getObject(col_name);

				if (col_values == null) {
					col_values = "";
				}
				Field field = cls.getDeclaredField(col_name);
				field.setAccessible(true);// 打开javabean的private访问权限
				field.set(resultObject, col_values);// 把col_values赋值给resultObject
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
				field.setAccessible(true);// 获得从外部写入到类中private成员的方法
				field.set(resultRefobject, col_values);

			}
			list.add(resultRefobject);
		}

		return list;
	}

	/**
	 * release the connection.
	 */
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
	// jdbcutils.getConnection();// 获得连接对象
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
	// // select * from userinfo 表示的是返回多行记录 select * from userinfo where
	// // id=?表示的是返回单行记录
	// // String sql = "select * from userinfo where id=?";//单行操作
	// // List<Object> params = new ArrayList<Object>();
	// // params.add(1);
	//
	// String sql = "select * from userinfo ";// 多行操作
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

