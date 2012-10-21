package com.yd.etravel.web.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yd.etravel.util.IConstants;

public class SessionCheckingFilter implements Filter {

	private static final String APP_NAME = "/etravel-web";
	private static Set<String> urls = new HashSet<String>();
	static {
		urls.add("/customer/initCustomerCreate.do");
		urls.add("/customer/customer_reg.jsp");
		urls.add("/customer/forwardCustomer.do");
		urls.add("/customer/createCustomer.do");
		urls.add("/admin/login.jsp");
		urls.add("/admin/authanticate.do");
		urls.add("/admin/findSearch.do");
		urls.add("/admin/processSearch.do");
		urls.add("/admin/initBookingCreate.do");
		urls.add("/admin/initSearch.do");
		urls.add("/admin/createBooking.do");
	}

	private FilterConfig config = null;

	@Override
	public void destroy() {
		this.config = null;
	}

	@Override
	public void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain filterChain)
			throws IOException, ServletException {
		final String url = ((HttpServletRequest) request).getRequestURI();
		final HttpSession session = ((HttpServletRequest) request)
				.getSession(false);
		final String[] valStrings = url.split(APP_NAME);
		String urlEnd = "";
		boolean hasAppname = true;
		if (valStrings != null && valStrings.length > 1) {
			urlEnd = valStrings[valStrings.length - 1];
		} else {
			urlEnd = url;
			hasAppname = false;
		}

		if (urls.contains(urlEnd)) {
			filterChain.doFilter(request, response);
			return;

		} else {
			final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			if (url.contains("/admin/")) {
				if (isSessionNull(session)) {
					redirect("/admin/login.jsp", hasAppname,
							httpServletResponse);
					return;
				}
			} else if (url.contains("/customer/")) {
				if (isSessionNull(session)) {
					redirect("/customer/initCustomerCreate.do", hasAppname,
							httpServletResponse);
					return;
				} else {
					filterChain.doFilter(request, response);
					return;
				}
			}
		}
		filterChain.doFilter(request, response);
		return;
	}

	protected void redirect(final String url, final boolean hasAppname,
			final HttpServletResponse response) throws IOException {
		response.sendRedirect(getUrl(url, hasAppname));
	}

	protected boolean isSessionNull(final HttpSession session) {
		return session == null
				|| session.getAttribute(IConstants.IUser.USER_PROFILE) == null;
	}

	private String getUrl(final String uri, final boolean hasAppname) {
		return hasAppname ? APP_NAME + uri : uri;
	}

	@Override
	public void init(final FilterConfig config) throws ServletException {
		this.config = config;
	}

}
