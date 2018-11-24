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
		System.out.println("enter doPost()");

		// request.getParameter("字符串");
		// 这个字符串是html表单的名字
		// 使用request.getParameter可以获得表单传过来的值
		//TODO  how to handle various input?
		String body = request.getParameter("body");
		if (Util.isEmpty(body)) {
			return;
		}
		
		String answer = request.getParameter("answer");
		if (Util.isEmpty(answer)) {
			answer = "我不知道";
		}
		
		String submitter = request.getParameter("submitter");
		if (Util.isEmpty(submitter)) {
			submitter = "小明";
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String datetime = df.format(new Date());
		System.out.println(datetime);
		
		List<Object> param = new ArrayList<Object>();
		param.add(body);
		param.add(answer);
		param.add(submitter);
		param.add(datetime);
		
		
		boolean flag = service.submitQuestion(param);
		
		PrintWriter out = response.getWriter();
		
		if (flag) {
			//Thanks for your submission.
			out.println(
	                "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
	                          "http://www.w3.org/TR/html4/loose.dtd\">\n" +
	                      "<html> \n" +
	                        "<head> \n" +
	                          "<meta http-equiv=\"Content-Type\" content=\"text/html; " +
	                            "charset=UTF-8\"> \n" +
	                          "<title> Thanks for your submition.  </title> \n" +
	                        "</head> \n" +
	                        "<body> <div align='center'> \n" +
	                          "<style= \"font-size=\"12px\" color='black'\"" + "\">" +
	                            "Thank you for your submission at " + datetime + ". <br> " + 
	                        "</font></body> \n" +
	                      "</html>" 
	                );
	        
		} else {
			//Sorry for something wrong when update DB.
			out.println(
	                "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
	                          "http://www.w3.org/TR/html4/loose.dtd\">\n" +
	                      "<html> \n" +
	                        "<head> \n" +
	                          "<meta http-equiv=\"Content-Type\" content=\"text/html; " +
	                            "charset=UTF-8\"> \n" +
	                          "<title> Thanks for your submition.  </title> \n" +
	                        "</head> \n" +
	                        "<body> <div align='center'> \n" +
	                          "<style= \"font-size=\"12px\" color='black'\"" + "\">" +
	                            "Sorry. Fail to submit. Please try again later." + " <br> " + 
	                        "</font></body> \n" +
	                      "</html>" 
	                );
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
		service = new SubmitQuestionDao();
	}

}
