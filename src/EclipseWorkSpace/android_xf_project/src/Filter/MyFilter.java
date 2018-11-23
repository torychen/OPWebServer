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
		String path = ((HttpServletRequest) request).getContextPath();// 获得路径
		// ////////////////////////////////////////////////////////////
		// 过滤用户请求 判断是否登录
		HttpServletRequest servletRequest = (HttpServletRequest) request;// 获得请求的对象
		HttpServletResponse servletResponse = (HttpServletResponse) response;// 获得返回的对象
		servletRequest.setCharacterEncoding("utf-8");// 转化成中文码
		servletResponse.setCharacterEncoding("utf-8");

	
		String username = (String) servletRequest.getSession().getAttribute(
				"username");// 获得用户名的输入 要是没有输入 那是不能直接到下一个界面 的 那就达到了过滤的效果
		if (username == null) {
			servletResponse.sendRedirect(path+"/index.jsp");
		}
		// ///////////////////////////////////////////////////////////
		// 要是正常登录 就直接进入下一个界面
		chain.doFilter(servletRequest, servletResponse);
		// http://192.168.1.104:8080/android_xf_project/main.jsp
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
