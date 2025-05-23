package kr.kh.team3final.filter;

import static org.mockito.ArgumentMatchers.matches;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class SavePreviousUrlFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();

		String uri = request.getRequestURI();

		// 확장자 검사
		boolean isStatic = uri.matches(".*\\.(css|js|png|jpg|jpeg|gif|ico|svg|woff2?)$");
		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		boolean isProtected = uri.matches("^/(pay|error).*");
		boolean isToolingRequest = uri.startsWith("/.well-known");

		if (!isStatic && !isAjax && !isProtected && !isToolingRequest) {
			request.getSession().setAttribute("prevPage", uri);
		}

		chain.doFilter(req, res);
	}
}