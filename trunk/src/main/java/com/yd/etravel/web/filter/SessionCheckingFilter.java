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

    public void destroy() {
	config = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain filterChain) throws IOException, ServletException {
	String URI = ((HttpServletRequest) request).getRequestURI();
	HttpSession session = ((HttpServletRequest) request).getSession(false);
	String[] valStrings = URI.split(APP_NAME);
	String uriend = "";
	if (valStrings != null) {
	    uriend = valStrings[valStrings.length - 1];
	}

	if (urls.contains(uriend)) {
	    filterChain.doFilter(request, response);
	    return;

	} else if (URI.startsWith(APP_NAME+"/admin/")) {
	    if (session == null
		    || session.getAttribute(IConstants.IUser.USER_PROFILE) == null) {
		((HttpServletResponse) response)
			.sendRedirect(APP_NAME+"/admin/login.jsp");
		return;
	    }
	} else if (URI.startsWith(APP_NAME+"/customer/")) {
	    if (session == null
		    || session.getAttribute(IConstants.IUser.USER_PROFILE) == null) {
		((HttpServletResponse) response)
			.sendRedirect(APP_NAME+"/customer/initCustomerCreate.do");
		return;
	    } else {
		filterChain.doFilter(request, response);
		return;
	    }
	}
	filterChain.doFilter(request, response);
	return;
    }

    
    public void init(FilterConfig config) throws ServletException {
	this.config = config;
    }

}
