package cn.tuyucheng.taketoday.patterns.intercepting.filter.filters;

import cn.tuyucheng.taketoday.patterns.intercepting.filter.commands.FrontCommand;
import cn.tuyucheng.taketoday.patterns.intercepting.filter.commands.LoginCommand;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
	private OnIntercept callback;

	public AuthenticationFilter(OnIntercept callback) {
		this.callback = callback;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain chain
	) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpServletRequest.getSession(false);
		if (session == null || session.getAttribute("username") == null) {
			callback.intercept();
			FrontCommand command = new LoginCommand();
			command.init(httpServletRequest, httpServletResponse);
			command.process();
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}
}
