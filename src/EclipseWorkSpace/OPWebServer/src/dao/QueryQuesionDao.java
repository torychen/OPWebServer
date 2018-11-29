package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jystudio.dbutil.DBUtil;

import myutil.MyUtil;
import service.MyConstant;
import service.QueryQuestionService;

public class QueryQuesionDao implements QueryQuestionService {
	private DBUtil dbUtil;
	
	public QueryQuesionDao() {
		dbUtil = new DBUtil();
	}
	
	@Override
	public List<Map<String, Object>> listQuestion(int start, int pageSize) {
		String sql = "select * from question limit ?, ?";
		
		List<Object> params = new ArrayList<Object>();
		params.add(start);
		params.add(pageSize);
		
		List<Map<String, Object>> maps = null;

		try {
			dbUtil.getConnection();
			maps = dbUtil.findMoreList(sql, params);
		} catch (Exception e) {
			MyUtil.dbg("Fail to query from db");
			e.printStackTrace();
		} finally {
			dbUtil.release();
		}

		return maps;
	}

	@Override
	public int getRecordCount() {
		int count = 0;
		dbUtil.getConnection();
		try {
			count = dbUtil.getRecordCount(MyConstant.QUESTION_TABLE_NAME);
		} catch (Exception e) {
			MyUtil.dbg("Fail to getRecordCount.");
		}
		
		dbUtil.release();
		
		return count;
	}

}
