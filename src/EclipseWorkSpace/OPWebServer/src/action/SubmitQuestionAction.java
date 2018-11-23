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

		String path = request.getContextPath();

		
		// request.getParameter("字符串");
		// 这个字符串是html表单的名字
		// 使用request.getParameter可以获得表单传过来的值
		String body = request.getParameter("body");
		if (Util.isEmpty(body)) {
			return;
		}
		
		//Create 
		
		String answer = request.getParameter("answer");
		String submitter = request.getParameter("submitter");
		String knower = request.getParameter("knower");
		String datetime = request.getParameter("datetime");
		if (datetime == null || datetime == "") {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			datetime = df.format(new Date());
			System.out.println(datetime);
		}
		
		String language = request.getParameter("language");
		String sort = request.getParameter("sort");
		String company = request.getParameter("company");

		List<Object> param = new ArrayList<Object>();
		param.add(body);
		param.add(answer);
		param.add(submitter);
		//boolean flag = Service.registerUser(param);
//		if (flag) {
//			response.sendRedirect(path + "/index.jsp");// 重定向就是要重新回到主界面
//		}
		
		PrintWriter out = response.getWriter();
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
                            "Username: " + submitter + " <br> " + 
                            "Body: " + body +
                            "datetime:" + datetime +
                        "</font></body> \n" +
                      "</html>" 
                );
        

		out.flush();
		out.close();
	}

}
