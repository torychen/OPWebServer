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
			// ����ͼƬ����Ϣ ֻ���Ǵ洢ͼƬ��·��
			String sql = "insert into product (proid,proname,proprice,proaddress,proimage) values(?,?,?,?,?)";
			jdbcutil.getConnection();// �������ݿ�
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
		// ���Ҷ�����¼
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from product where (1=1) ";
		// ��1=1ʱ ��ʾ��product���е�������Ϣ
		// ��Ϊ1��Զ������1 ���� �⽫��һ����ѭ��
		// ���������ִ�к� product ���е����ݻ�һ����һ��ı���ȡ
		// ֱ������ĩ��
		// select����ѯ ��ʵ��
		// *���������ݣ�
		// from���ӡ����
		// where��������������
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();

		if (proname != null) {
			buffer.append(" and  proname like ? ");// �ʺű�ʾ����ռλ�� Ҳ����proname��ֵ
			params.add("%" + proname + "%");// ע�����޸��˺� Ҫע����ǹ��˺������� Ҫ��Ȼ�����
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
		// ���Ҷ�����¼
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from product where (1=1) ";
		// ��1=1ʱ ��ʾ��product���е�������Ϣ
		// ��Ϊ1��Զ������1 ���� �⽫��һ����ѭ��
		// ���������ִ�к� product ���е����ݻ�һ����һ��ı���ȡ
		// ֱ������ĩ��
		// select����ѯ ��ʵ��
		// *���������ݣ�
		// from���ӡ����
		// where��������������
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();

		if (proname != null) {
			buffer.append(" and  proname like ? ");// �ʺű�ʾ����ռλ�� Ҳ����proname��ֵ
			params.add("%" + proname + "%");// ע�����޸��˺� Ҫע����ǹ��˺������� Ҫ��Ȼ�����
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
			// SELECT Count(*) As MyCount FROM name ��������ʲô��˼
			// count()��һ���ۺϺ�����name�Ǳ��������������ͳ��name������ж��������ݣ��������������������ΪMyCount��

			map = jdbcutil.findSimpleresult(sql, null);
			count = Integer.parseInt(map.get("mycount").toString());// ��map�е�Mycountת��������

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcutil.release();// �ͷ�jdbc����
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
