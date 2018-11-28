package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jystudio.dbutil.DBUtil;

import service.QueryQuestionService;

public class QueryQuesionDao implements QueryQuestionService {
	private DBUtil dbUtil;
	
	private int number;//number per page
	
	public QueryQuesionDao() {
		dbUtil = new DBUtil();
	}
	
	@Override
	public List<Map<String, Object>> listQuestion(int number) {
		this.number=number;
		
		String sql = "select * from question";
		List<Map<String, Object>> maps = null;
		
		try {
			dbUtil.getConnection();
			maps = dbUtil.findMoreList(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbUtil.release();
		}
		
		return maps;
	}

	@Override
	public List<Map<String, Object>> listQuestion() {
		int number = 10;
		return listQuestion(number);
	}

}
