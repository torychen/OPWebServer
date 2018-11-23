package org.jystudio.dbutil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IDBUtil {
	public boolean getConnection();
	public Map<String, Object> findSimpleresult(String sql, List<Object> params) throws SQLException;
	public boolean UpdateByPreparedStatement(String sql, List<Object> params)throws SQLException;
	public List<Map<String, Object>> findMoreList(String sql,List<Object> params)throws SQLException;
	public void release();

}
