package cn.tuyucheng.taketoday.patterns.intercepting.filter.commands;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Optional;

public class LogoutCommand extends FrontCommand {
	@Override
	public void process() throws ServletException, IOException {
		super.process();
		Optional.ofNullable(request.getSession(false))
			.ifPresent(session -> {
				session.removeAttribute("username");
				session.removeAttribute("order");
			});
		response.sendRedirect("/?command=Home");
	}
}
