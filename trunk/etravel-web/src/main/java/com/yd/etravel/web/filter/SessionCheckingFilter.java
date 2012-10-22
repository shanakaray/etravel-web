package com.yd.etravel.web.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
import com.yd.etravel.util.StringUtils;

public class SessionCheckingFilter implements Filter {

	private static final String APP_NAME = "/etravel-web";
	private static final List<String> URL_LIST = Arrays.asList(
			"/customer/initCustomerCreate.do", "/customer/customer_reg.jsp",
			"/customer/forwardCustomer.do", "/customer/createCustomer.do",
			"/admin/login.jsp", "/admin/authanticate.do",
			"/admin/findSearch.do", "/admin/processSearch.do",
			"/admin/initBookingCreate.do", "/admin/initSearch.do",
			"/admin/createBooking.do");

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
		if (!StringUtils.isEmpty(valStrings) && valStrings.length >= 2) {
			urlEnd = valStrings[valStrings.length - 1];
		} else {
			urlEnd = url;
			hasAppname = false;
		}

		if (URL_LIST.contains(urlEnd)) {
			filterChain.doFilter(request, response);
			return;

		} else {
			final HttpServletResponse httpResponse = (HttpServletResponse) response;
			if (url.contains("/admin/")) {
				if (isSessionNull(session)) {
					redirect("/admin/login.jsp", hasAppname, httpResponse);
					return;
				}
			} else if (url.contains("/customer/")) {
				if (isSessionNull(session)) {
					redirect("/customer/initCustomerCreate.do", hasAppname,
							httpResponse);
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

	private String getUrl(final String uri, final boolean hasAppname) {
		return hasAppname ? APP_NAME + uri : uri;
	}

	@Override
	public void init(final FilterConfig config) throws ServletException {
		this.config = config;
	}

	protected boolean isSessionNull(final HttpSession session) {
		return (session == null)
				|| (session.getAttribute(IConstants.IUser.USER_PROFILE) == null);
	}

	protected void redirect(final String url, final boolean hasAppname,
			final HttpServletResponse response) throws IOException {
		response.sendRedirect(getUrl(url, hasAppname));
	}

}
