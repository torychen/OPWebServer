package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getContextPath();// ���·��
		// ////////////////////////////////////////////////////////////
		// �����û����� �ж��Ƿ��¼
		HttpServletRequest servletRequest = (HttpServletRequest) request;// �������Ķ���
		HttpServletResponse servletResponse = (HttpServletResponse) response;// ��÷��صĶ���
		servletRequest.setCharacterEncoding("utf-8");// ת����������
		servletResponse.setCharacterEncoding("utf-8");

	
		String username = (String) servletRequest.getSession().getAttribute(
				"username");// ����û��������� Ҫ��û������ ���ǲ���ֱ�ӵ���һ������ �� �Ǿʹﵽ�˹��˵�Ч��
		if (username == null) {
			servletResponse.sendRedirect(path+"/index.jsp");
		}
		// ///////////////////////////////////////////////////////////
		// Ҫ��������¼ ��ֱ�ӽ�����һ������
		chain.doFilter(servletRequest, servletResponse);
		// http://192.168.1.104:8080/android_xf_project/main.jsp
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
