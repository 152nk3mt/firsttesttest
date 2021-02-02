package cn.unowen.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object user = request.getSession().getAttribute("user");
		String url = request.getRequestURI();
		if (url.endsWith(".css") || url.endsWith(".js")) {
			return true;
		}
		if (null == user) {
			request.setAttribute("msg", "没有权限请先登陆");
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}
}
