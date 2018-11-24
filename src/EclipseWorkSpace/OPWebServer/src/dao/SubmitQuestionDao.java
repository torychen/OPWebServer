package dao;
import java.sql.SQLException;
import java.util.ArrayList;
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
		
		//TODO so far only support update DB by body, answer, submitter and datetime.
		String sql = "insert into question(body,answer,submitter,datetime) values(?,?,?,?)";
		try {
			flag = dbUtil.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}
		
		dbUtil.release();
		return flag;
	}

}
