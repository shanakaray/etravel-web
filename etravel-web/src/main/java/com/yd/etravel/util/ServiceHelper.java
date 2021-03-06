/**
 * 
 */
package com.yd.etravel.util;

import org.springframework.web.context.WebApplicationContext;

import com.yd.etravel.service.booking.IBookingManager;
import com.yd.etravel.service.cmt.content.IContentManager;
import com.yd.etravel.service.hotel.IHotelManager;
import com.yd.etravel.service.room.IRoomManager;
import com.yd.etravel.service.user.IUserManager;
import com.yd.etravel.util.IConstants.IServiceNames;

/**
 * @author yora
 * 
 */
public final class ServiceHelper implements IServiceNames {
	private static ServiceHelper instance = null;

	public static ServiceHelper getInstance() {
		if (instance == null) {
			instance = new ServiceHelper();
		}
		return instance;
	}

	private WebApplicationContext applicationContext;

	private ServiceHelper() {
	}

	public WebApplicationContext getApplicationContext() {
		return this.applicationContext;
	}

	public IBookingManager getBookingManager() {
		return (IBookingManager) this.applicationContext.getBean(BOOKING);
	}

	public IContentManager getContentService() {
		return (IContentManager) this.applicationContext.getBean(CONTENT);
	}

	public IHotelManager getHotelManager() {
		return (IHotelManager) this.applicationContext.getBean(HOTEL);
	}

	public IpgUtil getIpgUtil() {
		return (IpgUtil) this.applicationContext.getBean(IPG);
	}

	public IRoomManager getRoomManager() {
		return (IRoomManager) this.applicationContext.getBean(ROOM);
	}

	public IUserManager getUserManager() {
		return (IUserManager) this.applicationContext.getBean(USER);
	}

	public void setApplicationContext(
			final WebApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}
