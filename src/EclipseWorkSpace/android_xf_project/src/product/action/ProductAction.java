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
	private ProductService service;// ����һ��service

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
		if (action_flag.equals("add")) {// Ҫ������ӵĲ���
			addProduct(request, response);
		} else if (action_flag.equals("list")) {// Ҫ���ǲ�ѯ�Ĳ���
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
		request.getRequestDispatcher("/product/2_1_5xs.jsp").forward(request,// ��������
				response);
	}

	private void delProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getContextPath();// ���·��
		String[] ids = request.getParameterValues("ids");// ���product��ֵ
		boolean flag = service.deleteProduct(ids);// ִ��������
		if (flag) {
			response.sendRedirect(path
					+ "/servlet/ProductAction?action_flag=list");// ���¶����б�
		}

	}

	private void ListProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// String path = request.getContextPath();
		String proname = request.getParameter("proname");// �ȴ�request�л��һ����Ʒ������
		int recoderCount = service.getItemCount();// ��ü�¼������

		int currentpager = 1;// ����Ϊ��һҳ
		String PagerNum = request.getParameter("PagerNum");
		if (PagerNum != null) {
			currentpager = Integer.parseInt(PagerNum);
		}
		DividePage pDividePage = new DividePage(5, recoderCount, currentpager);
		int start = pDividePage.getFormIndex();// ��ʾ��Ŀ�ĳ�ʼλ��
		int end = pDividePage.getTopager();// ��ʾ������
		// ////////////////////////////////
		// �Ѿ��������ݷ�ҳ�ļ���
		List<Map<String, Object>> list = service.ListProduct(proname, start,
				end);// ���ص��е�����
		// ////////////////////////////////////////////////////////
		// �����󷵻�һ��proname ��list proname�Ǳ�ʾ���ǲ�ѯ��Ʒ������ list�ǲ�Ʒ����ϸ��Ϣ
		request.setAttribute("pDividePage", pDividePage);// �Ѳ��������û�
		request.setAttribute("listproduct", list);//
		request.setAttribute("proname", proname);
		// ///////////////////////////////////////////////////////////////////////
		// request.getRequestDispatcher()������ת����ǰ��ҳ�湲��һ��request ;
		// response.sendRedirect()�����¶���ǰ��ҳ�治��һ��request��
		// request.getRequestDispather();���ص���һ��RequestDispatcher����
		request.getRequestDispatcher("/product/2_1_5.jsp").forward(request,// ��������
				response);
	}

	@SuppressWarnings("unchecked")
	private void addProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getContextPath();
		DiskFileItemFactory factory = new DiskFileItemFactory();// //
																// ���ڴ����ļ���Ŀ����һ����������
		ServletFileUpload fileUpload = new ServletFileUpload(factory);// ; //
																		// ����һ���µ��ļ��ϴ�����
		fileUpload.setFileSizeMax(3 * 1024 * 1024);// �������õ����ϴ��ļ������ߴ�����
		fileUpload.setSizeMax(6 * 1024 * 1024);// ��������������Ϣʵ�����ݣ��������ϴ����ݣ������ߴ����ƣ��Է�ֹ�ͻ��˶����ϴ������ļ����˷ѷ������˵Ĵ洢�ռ�
		List<Object> params = new ArrayList<Object>();
		List<FileItem> list = null;
		params.add(UUIDtools.getUUID());// ���ݿ�����

		try {
			// parseRequest ������ServletFileUpload�����Ҫ���������Ƕ�HTTP������Ϣ�����ݽ��н�������ڷ�����
			// ��������FORM���е�ÿ���ֶε����ݣ��������Ƿֱ��װ�ɶ�����FileItem����
			// Ȼ����ЩFileItem��������һ��List���͵ļ��϶����з��ء�
			list = fileUpload.parseRequest(request);// // �����ϴ�����
			// ////////////////////////////////////////////////////
			// ��Ϊ���ڱ���ͼƬ������
			for (FileItem fileitems : list) {
				if (fileitems.isFormField()) {// Ҫ��Ϊ���ı�����
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
					String imagename = fileitems.getName();// ����ļ�������
					params.add(imagename);
					@SuppressWarnings("deprecation")
					String upload_path = request.getRealPath("/upload");// ��÷������˵�·��
					System.out.println("--���������ļ�������------>>" + upload_path);
					File real_pathFile = new File(upload_path + "/" + imagename);// �������˵�·�����ļ�������
																					// �����ҵ���ȫ·��
					try {
						// ////////////////////////////////////////////////
						fileitems.write(real_pathFile);// ���ϴ���ͼƬ�ļ����汨����disk��
						// write�������ڽ�FileItem�����б�����������ݱ��浽ĳ��ָ�����ļ��С�
						// ���FileItem�����е����������Ǳ�����ĳ����ʱ�ļ��У��÷���˳����ɺ�
						// ��ʱ�ļ��п��ܻᱻ������÷���Ҳ�ɽ���ͨ���ֶ�����д�뵽һ���ļ��У�
						// ������Ҫ��;�ǽ��ϴ����ļ����ݱ����ڱ����ļ�ϵͳ��
						// ///////////////////////////////////////
						// �����ݼӵ����ݿ���
						boolean flag = service.addProduct(params);// Ҳ����˵ �Ҽ��˲�Ʒ��
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
