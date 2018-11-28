package demo;

public interface StudentService {
	public Pager<Student> findStudent(Student searchModel, int pageNum,			int pageSize);
}
