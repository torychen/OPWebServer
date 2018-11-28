package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubmitQuestionDao;
import service.SubmitQuestionService;
import java.util.Date;
import java.text.SimpleDateFormat;
import util.*;

/**
 * Servlet implementation class SubmitQuestionAction
 */
@WebServlet("/SubmitQuestionAction")
public class SubmitQuestionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SubmitQuestionService service;

    /**
     * Default constructor. 
     */
    public SubmitQuestionAction() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Just use doPost method.
		this.doPost(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String path = request.getContextPath();
		path = "3;url=" + path + "/index.jsp";
		//System.out.println("path is " + path);
		
		// request.getParameter("<name>");
		//TODO  how to handle various input?
		String body = request.getParameter("body");
		if (Util.isEmpty(body)) {
			response.getWriter().write("问题不能为空。3秒钟跳到主页");
			response.setHeader("refresh", path);
			return;
		}
		
		//System.out.println("Body is" + body);
		
		String answer = request.getParameter("answer");
		if (Util.isEmpty(answer)) {
			answer = "na";
		}
		
		String submitter = request.getParameter("submitter");
		if (Util.isEmpty(submitter)) {
			submitter = "xiao ming";//为什么用小明，数据库中是乱码？
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = df.format(new Date());
		System.out.println(datetime);
		
		List<Object> param = new ArrayList<Object>();
		param.add(body);
		param.add(answer);
		param.add(submitter);
		param.add(datetime);
		
		
		boolean flag = service.submitQuestion(param);
		
		if (flag) {
			response.getWriter().write("提交成功！3秒钟跳到主页");
		} else {
			
			response.getWriter().write("提交失败！3秒钟跳到主页");
		}
		
		response.setHeader("refresh", path);
		
		return;
	}
	
	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */    
	public void init() throws ServletException {
		service = new SubmitQuestionDao();
	}

}
