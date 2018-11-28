package demo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SublistStudentDaoImpl implements StudentDao{
	@Override
	public Pager<Student> findStudent(Student searchModel, int pageNum,
			int pageSize) {
		/*
		 * 根据条件获取所有数据
		 */
		List<Student> allStudentList = getAllStudent(searchModel);
		/*
		 * 根据参数创建分页对象
		 */
		Pager<Student> pager = new Pager<Student>(pageNum,pageSize,allStudentList);
		return pager;
	}
	/*
	 * 获取所有数据
	 */
	private List<Student> getAllStudent(Student searchModel){
		List<Student> result = new ArrayList<Student>();
		List<Object> paramList = new ArrayList<Object>();
		String stuName = searchModel.getStuName();
		int gender = searchModel.getGender();
		StringBuilder sql = new StringBuilder("select * from t_student where 1=1");
		if(stuName != null && !stuName.equals("")){
			sql.append(" and stu_name like ?");
			paramList.add("%"+stuName+"%");
		}
		if(gender == Constant.GENDER_FEMALE || gender== Constant.GENDER_MALE){
			sql.append(" and gender = ?");
			paramList.add(gender);
		}
		DBUtil jdbcUtil = null;
		try {
			jdbcUtil = new DBUtil();
			jdbcUtil.getConnection();
			List<Map<String, Object>> mapList = jdbcUtil.findResult(sql.toString(), paramList);
			if(mapList != null){
				for(Map<String, Object> map : mapList){
					Student s = new Student(map);
					result.add(s);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("查询所有数据异常！",e);
		}finally{
			if(jdbcUtil != null){
				jdbcUtil.release();
			}
		}
		return result;
	}
}

