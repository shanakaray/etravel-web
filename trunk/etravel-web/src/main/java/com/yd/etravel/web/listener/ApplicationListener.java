/**
 * 
 */
package com.yd.etravel.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yd.etravel.util.ServiceHelper;

/**
 * @author yora com.yd.etravel.web.listener.ApplicationListener
 * 
 */
public class ApplicationListener implements ServletContextListener {

	private static Log log = LogFactory.getLog(ApplicationListener.class);

	public ApplicationListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void contextDestroyed(final ServletContextEvent event) {

	}

	@Override
	public void contextInitialized(final ServletContextEvent event) {
		try {
			final ServletContext servletContext = event.getServletContext();
			final WebApplicationContext wac = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			ServiceHelper.getInstance().setApplicationContext(wac);

		} catch (final Exception e) {
			log.fatal(e.getMessage(), e);
		}
	}

}
