package product.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import product.dao.ProductDao;
import product.service.ProductService;
import product.utils.DividePage;
import product.utils.UUIDtools;

public class ProductAction extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductService service;// 申请一个service

	/**
	 * Constructor of the object.
	 */
	public ProductAction() {
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

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();
		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("add")) {// 要是是添加的操作
			addProduct(request, response);
		} else if (action_flag.equals("list")) {// 要是是查询的操作
			ListProduct(request, response);
		} else if (action_flag.equals("del")) {
			delProduct(request, response);
		} else if (action_flag.equals("view")) {
			ViewProduct(request, response);
		}

		out.flush();
		out.close();
	}

	private void ViewProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubs
		String proid = request.getParameter("proid");
		Map<String, Object> map = service.viewProduct(proid);
		request.setAttribute("map", map);//
		//System.out.println("--ViewProduct----->>"+proid);
		//System.out.println("--ViewProduct----->>"+map.toString());
		request.getRequestDispatcher("/product/2_1_5xs.jsp").forward(request,// 给主界面
				response);
	}

	private void delProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getContextPath();// 获得路径
		String[] ids = request.getParameterValues("ids");// 获得product的值
		boolean flag = service.deleteProduct(ids);// 执行批处理
		if (flag) {
			response.sendRedirect(path
					+ "/servlet/ProductAction?action_flag=list");// 重新定义列表
		}

	}

	private void ListProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// String path = request.getContextPath();
		String proname = request.getParameter("proname");// 先从request中获得一个产品的名称
		int recoderCount = service.getItemCount();// 获得记录的总数

		int currentpager = 1;// 设置为第一页
		String PagerNum = request.getParameter("PagerNum");
		if (PagerNum != null) {
			currentpager = Integer.parseInt(PagerNum);
		}
		DividePage pDividePage = new DividePage(5, recoderCount, currentpager);
		int start = pDividePage.getFormIndex();// 显示条目的初始位置
		int end = pDividePage.getTopager();// 显示的条数
		// ////////////////////////////////
		// 已经进行数据分页的集合
		List<Map<String, Object>> list = service.ListProduct(proname, start,
				end);// 返回单行的数据
		// ////////////////////////////////////////////////////////
		// 给请求返回一个proname 和list proname是表示的是查询产品的名称 list是产品的详细信息
		request.setAttribute("pDividePage", pDividePage);// 把参数传给用户
		request.setAttribute("listproduct", list);//
		request.setAttribute("proname", proname);
		// ///////////////////////////////////////////////////////////////////////
		// request.getRequestDispatcher()是请求转发，前后页面共享一个request ;
		// response.sendRedirect()是重新定向，前后页面不是一个request。
		// request.getRequestDispather();返回的是一个RequestDispatcher对象
		request.getRequestDispatcher("/product/2_1_5.jsp").forward(request,// 给主界面
				response);
	}

	@SuppressWarnings("unchecked")
	private void addProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getContextPath();
		DiskFileItemFactory factory = new DiskFileItemFactory();// //
																// 基于磁盘文件项目创建一个工厂对象
		ServletFileUpload fileUpload = new ServletFileUpload(factory);// ; //
																		// 创建一个新的文件上传对象
		fileUpload.setFileSizeMax(3 * 1024 * 1024);// 用于设置单个上传文件的最大尺寸限制
		fileUpload.setSizeMax(6 * 1024 * 1024);// 用于设置请求消息实体内容（即所有上传数据）的最大尺寸限制，以防止客户端恶意上传超大文件来浪费服务器端的存储空间
		List<Object> params = new ArrayList<Object>();
		List<FileItem> list = null;
		params.add(UUIDtools.getUUID());// 数据库主键

		try {
			// parseRequest 方法是ServletFileUpload类的重要方法，它是对HTTP请求消息体内容进行解析的入口方法。
			// 它解析出FORM表单中的每个字段的数据，并将它们分别包装成独立的FileItem对象，
			// 然后将这些FileItem对象加入进一个List类型的集合对象中返回。
			list = fileUpload.parseRequest(request);// // 解析上传请求
			// ////////////////////////////////////////////////////
			// 因为存在表单和图片的区别
			for (FileItem fileitems : list) {
				if (fileitems.isFormField()) {// 要是为表单文本内容
					if (fileitems.getFieldName().equals("proname")) {
						params.add(fileitems.getString("utf-8"));
					}
					if (fileitems.getFieldName().equals("proprice")) {
						params.add(fileitems.getString("utf-8"));
					}
					if (fileitems.getFieldName().equals("proaddress")) {
						params.add(fileitems.getString("utf-8"));
					}
				} else {
					String imagename = fileitems.getName();// 获得文件的名称
					params.add(imagename);
					@SuppressWarnings("deprecation")
					String upload_path = request.getRealPath("/upload");// 获得服务器端的路径
					System.out.println("--服务器端文件的名称------>>" + upload_path);
					File real_pathFile = new File(upload_path + "/" + imagename);// 服务器端的路径和文件的名称
																					// 就是我的完全路径
					try {
						// ////////////////////////////////////////////////
						fileitems.write(real_pathFile);// 将上传的图片文件保存报本地disk中
						// write方法用于将FileItem对象中保存的主体内容保存到某个指定的文件中。
						// 如果FileItem对象中的主体内容是保存在某个临时文件中，该方法顺利完成后，
						// 临时文件有可能会被清除。该方法也可将普通表单字段内容写入到一个文件中，
						// 但它主要用途是将上传的文件内容保存在本地文件系统中
						// ///////////////////////////////////////
						// 把数据加到数据库中
						boolean flag = service.addProduct(params);// 也就是说 我加了产品后
						if (flag) {
							response.sendRedirect(path
									+ "/servlet/ProductAction?action_flag=list");
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		service = new ProductDao();
	}

}
