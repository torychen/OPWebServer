package demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SublistServlet
 */

public class SublistServlet extends HttpServlet {
	//创建service对象
	private StudentService studentService = new SublistStudentServiceImpl();
	public SublistServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 设置编码格式，防止解析中文参数乱码
		 */
		request.setCharacterEncoding("utf-8");
		/* 接收request参数
		 * 学生姓名
		 */
		String stuName = request.getParameter("stuName");
		/*
		 * 性别，默认是0，表示全部，不论男女
		 */
		int gender = Constant.DEFAULT_GENDER;
		String genderStr = request.getParameter("gender");
		if(genderStr != null && !"".equals(genderStr.trim())){
			gender = Integer.parseInt(genderStr);// 获取学生性别
		}
		/*
		 * 当前请求第几页
		 */
		int pageNum = Constant.DEFAULT_PAGENUM;
		String pageNumStr = request.getParameter("pageNum");
		//参数校验，是否是数字
		if(pageNumStr != null && !StringUtil.isNum(pageNumStr)){
			request.setAttribute("errorMsg", "参数输入错误");
			request.getRequestDispatcher("sublistStudent.jsp").forward(request,
					response);
			return;
		}
		if(pageNumStr != null && !"".equals(pageNumStr.trim())){
			pageNum = Integer.parseInt(pageNumStr);//获取当前请求第几页
		}
		/*
		 * 每页显示多少条数据
		 */
		int pageSize = Constant.DEFAULT_PAGE_SIZE;
		String pageSizeStr = request.getParameter("pageSize");
		if(pageSizeStr != null && !"".equals(pageSizeStr.trim())){
			pageSize = Integer.parseInt(pageSizeStr);// 每页显示多少条记录
		}
		// 组装查询条件
		Student searchModel = new Student();
		searchModel.setStuName(stuName);
		searchModel.setGender(gender);
		// 调用service查询结果
		Pager<Student> result = studentService.findStudent(searchModel,
				pageNum, pageSize);
		// 返回结果到页面
		request.setAttribute("result", result);
		request.getRequestDispatcher("sublistStudent.jsp").forward(request,
				response);
	}
}
