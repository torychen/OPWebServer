package org.jystudio.dbutil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * the interface of DBUtil which should apply to both web server and Android client.
 * @author Administrator
 *
 */
public interface IDBUtil {
	public boolean getConnection();
	public Map<String, Object> findSimpleresult(String sql, List<Object> params) throws SQLException;
	public boolean updateByPreparedStatement(String sql, List<Object> params)throws SQLException;
	public List<Map<String, Object>> findMoreList(String sql,List<Object> params)throws SQLException;
	public void release();

}
