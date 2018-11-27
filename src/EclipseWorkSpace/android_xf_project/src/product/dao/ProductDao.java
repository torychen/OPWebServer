package product.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.Request;

import Dbjdbc.jdbcutils;

import product.service.ProductService;

public class ProductDao implements ProductService {
	jdbcutils jdbcutil = null;

	public ProductDao() {
		// TODO Auto-generated constructor stub
		jdbcutil = new jdbcutils();
	}

	public boolean addProduct(List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			// ///////////////////////////////////////////////////
			// 对于图片的信息 只能是存储图片的路径
			String sql = "insert into product (proid,proname,proprice,proaddress,proimage) values(?,?,?,?,?)";
			jdbcutil.getConnection();// 连接数据库
			flag = jdbcutil.UpdateByPreparedStatement(sql, params);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcutil.release();
		}

		return flag;
	}

	public List<Map<String, Object>> ListProduct(String proname, int start,
			int end) {
		// TODO Auto-generated method stub
		// 查找多条记录
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from product where (1=1) ";
		// 当1=1时 显示出product表中的所有信息
		// 因为1永远都等于1 所以 这将是一个死循环
		// 当这个命令执行后 product 表中的数据会一遍又一遍的被读取
		// 直到世界末日
		// select（查询 现实）
		// *（所有数据）
		// from（从……里）
		// where（当……）条件
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();

		if (proname != null) {
			buffer.append(" and  proname like ? ");// 问号表示的是占位符 也就是proname的值
			params.add("%" + proname + "%");// 注意在修改了后 要注意的是关了后在启动 要不然会出错
			// System.out.println("---proname--->>" + proname);
			// System.out.println("---buffer--->>" + buffer.toString());
		}

		buffer.append("limit ?,? ");
		params.add(start);
		params.add(end);
		try {
			jdbcutil.getConnection();
			list = jdbcutil.findMoreList(buffer.toString(), params);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcutil.release();
		}

		return list;
	}
/////////////////////////////////////////////////////////////////////////////////////////////
	public List<Map<String, Object>> listProduct(String proname) {
		// TODO Auto-generated method stub
		// 查找多条记录
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from product where (1=1) ";
		// 当1=1时 显示出product表中的所有信息
		// 因为1永远都等于1 所以 这将是一个死循环
		// 当这个命令执行后 product 表中的数据会一遍又一遍的被读取
		// 直到世界末日
		// select（查询 现实）
		// *（所有数据）
		// from（从……里）
		// where（当……）条件
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();

		if (proname != null) {
			buffer.append(" and  proname like ? ");// 问号表示的是占位符 也就是proname的值
			params.add("%" + proname + "%");// 注意在修改了后 要注意的是关了后在启动 要不然会出错
			// System.out.println("---proname--->>" + proname);
			// System.out.println("---buffer--->>" + buffer.toString());
		}

		// buffer.append("limit ?,? ");
		// params.add(start);
		// params.add(end);
		try {
			jdbcutil.getConnection();
			list = jdbcutil.findMoreList(buffer.toString(), params);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcutil.release();
		}

		return list;
	}

	
	
	
	
	
	public int getItemCount() {
		// TODO Auto-generated method stub
		int count = 0;
		Map<String, Object> map = null;
		String sql = " select count(*) mycount from product ";
		try {
			jdbcutil.getConnection();
			// SELECT Count(*) As MyCount FROM name 这个语句是什么意思
			// count()是一个聚合函数，name是表名。这条语句是统计name这个表有多少条数据，并将查出的总数的列名为MyCount。

			map = jdbcutil.findSimpleresult(sql, null);
			count = Integer.parseInt(map.get("mycount").toString());// 把map中的Mycount转化成整数

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcutil.release();// 释放jdbc对象
		}
		return count;
	}

	public boolean deleteProduct(String[] ids) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			jdbcutil.getConnection();
			String[] sql = new String[ids.length];
			//System.out.println("------>>" + ids.length);
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					sql[i] = "delete from product where proid ='" + ids[i]
							+ "' ";
				}
			}
			flag = jdbcutil.deleteByBacth(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcutil.release();
		}
		return flag;
	}

	public Map<String, Object> viewProduct(String proid) {
		// TODO Auto-generated method stub
		Map<String, Object> map = null;
	
		try {
			jdbcutil.getConnection();
			String sql = "select * from product where proid = ? ";
			List<Object> params = new ArrayList<Object>();
			params.add(proid);
			map = jdbcutil.findSimpleresult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcutil.release();
		}

		return map;
	}
}
