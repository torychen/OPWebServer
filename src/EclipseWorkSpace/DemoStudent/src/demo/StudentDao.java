package demo;

public interface StudentDao {
	/**
	 * 根据查询条件，查询学生分页信息
	 * @param searchModel 封装查询条件
	 * @param pageNum 查询第几条数据
	 * @param pageSize 显示多少数据
	 * @return
	 */
	public Pager<Student> findStudent(Student searchModel,int pageNum,int pageSize);
}
