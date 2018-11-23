package register.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import register.service.registerService;
import registerDao.RegisterDao;

public class RegisterAction extends HttpServlet {

	private registerService Service;
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public RegisterAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");// =表示的是文本用html的形式
															// 用中文显示

		String path = request.getContextPath();

		PrintWriter out = response.getWriter();
		// PrintWriter用处是将结果以HTML的形式返回给客户端。
		// 这个方法是在Servlet中调用，而JspWriter()是在Jsp中使用。
		// PrintWriter out = response.getWriter();当一个Servlet响应的时候将响应信息通过
		// out对象输出到网页上，当响应结束时它自动被关闭。所以也可以理解为：
		// 当我们调用response.getWriter()这个对象同时获得了网页的画笔，
		// 这时你就可以通过这个画笔在网页上画任何你想要显示的东西。
		String username = request.getParameter("username");
		// request.getParameter("字符串");
		// 这个字符串是html表单的名字
		// 使用request.getParameter可以获得表单传过来的值
		String password = request.getParameter("password");
		String realname = request.getParameter("realname");

		List<Object> param = new ArrayList<Object>();
		param.add(username);
		param.add(password);
		param.add(realname);
		boolean flag = Service.registerUser(param);
		if (flag) {
			response.sendRedirect(path + "/index.jsp");// 重定向就是要重新回到主界面

		}

		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */    
	public void init() throws ServletException {
		// Put your code here
		Service = new RegisterDao();
	}

}
