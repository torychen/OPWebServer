package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QueryQuesionDao;
import service.QueryQuestionService;
import myutil.*;

/**
 * Servlet implementation class QueryQuestionAction
 */
@WebServlet("/QueryQuestionAction")
public class QueryQuestionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QueryQuestionService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryQuestionAction() {
        super();
    }

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String path = request.getContextPath();
		int recordCount = service.getRecordCount();
		//MyUtil.dbg("recordCount is " + recordCount);
		
		int currentPage = 1;// 设置为第一页
		String pageNum = request.getParameter("pageNum");
		if (pageNum != null) {
			//MyUtil.dbg("get pageNum is "+ pageNum);
			
			currentPage = Integer.parseInt(pageNum);
			
		}
		
		currentPage = currentPage <= 0 ? 1 : currentPage;
		//MyUtil.dbg("currentPageNum is " + currentPage);
		
		int pageSize = 5;// 显示的条数
		DividePage pDividePage = new DividePage(pageSize, recordCount, currentPage);
		int start = pDividePage.getStartIndex();// 显示条目的初始位置
		//MyUtil.dbg("start is " + start);
		
		List<Map<String, Object>> maps = null;
		
		try {
			maps = service.listQuestion(start, pageSize);
			//MyUtil.dbg("map size is " + maps.size());
		} catch (Exception e) {
			System.out.println("Error when query db.");
		}
		
		if ((maps == null) || (maps.size() == 0)) {
			//For debug purpose.
			System.out.println("no data return!");
			
			Map<String, Object> map = new HashMap<>();
			map.put("title", "for debug purpose title");
			map.put("body", "for debug purpose body");
			map.put("answer", "for debug purpose answer");
			map.put("submitter", "for debug purpose submitter");
			map.put("datetime", "for debug purpose datetime");
			
			maps = new ArrayList<Map<String,Object>>();
			maps.add(map);
		}
		
		request.setAttribute("pDividePage", pDividePage);// 把参数传给用户
		request.setAttribute("listQuestion", maps);
		request.getRequestDispatcher("/queryResult.jsp").forward(request,response);
	}
		
	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		service = new QueryQuesionDao();
	}

}
