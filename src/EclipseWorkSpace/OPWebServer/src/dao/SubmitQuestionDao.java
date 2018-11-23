package dao;
import java.util.List;

import org.jystudio.dbutil.*;

import service.SubmitQuestionService;

public class SubmitQuestionDao implements SubmitQuestionService {
	private DBUtil dbUtil;
	
	public SubmitQuestionDao() {
		dbUtil = new DBUtil();
	}
	
	public boolean submitQuestion(List<Object> params)
	{
		boolean flag = false;
		if (!dbUtil.getConnection()) {
			return flag;
		}
		
		
		dbUtil.release();
		return false;
	}

}
