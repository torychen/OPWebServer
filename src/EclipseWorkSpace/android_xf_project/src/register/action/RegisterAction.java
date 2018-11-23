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

		response.setContentType("text/html;charset=utf-8");// =��ʾ�����ı���html����ʽ
															// ��������ʾ

		String path = request.getContextPath();

		PrintWriter out = response.getWriter();
		// PrintWriter�ô��ǽ������HTML����ʽ���ظ��ͻ��ˡ�
		// �����������Servlet�е��ã���JspWriter()����Jsp��ʹ�á�
		// PrintWriter out = response.getWriter();��һ��Servlet��Ӧ��ʱ����Ӧ��Ϣͨ��
		// out�����������ҳ�ϣ�����Ӧ����ʱ���Զ����رա�����Ҳ�������Ϊ��
		// �����ǵ���response.getWriter()�������ͬʱ�������ҳ�Ļ��ʣ�
		// ��ʱ��Ϳ���ͨ�������������ҳ�ϻ��κ�����Ҫ��ʾ�Ķ�����
		String username = request.getParameter("username");
		// request.getParameter("�ַ���");
		// ����ַ�����html��������
		// ʹ��request.getParameter���Ի�ñ���������ֵ
		String password = request.getParameter("password");
		String realname = request.getParameter("realname");

		List<Object> param = new ArrayList<Object>();
		param.add(username);
		param.add(password);
		param.add(realname);
		boolean flag = Service.registerUser(param);
		if (flag) {
			response.sendRedirect(path + "/index.jsp");// �ض������Ҫ���»ص�������

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
