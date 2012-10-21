/**
 * 
 */
package com.yd.etravel.service.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yd.etravel.service.booking.IBookingManager;

/**
 * com.yd.etravel.service.task.BookingTask
 * 
 * @author shanaka
 * 
 */
public class BookingTask implements ITask {
	@Autowired(required = true)
	private IBookingManager bookingManager;
	protected static final Log LOG = LogFactory.getLog(BookingTask.class);

	public BookingTask() {
	}

	public IBookingManager getBookingManager() {
		return this.bookingManager;
	}

	@Override
	public void runTask() {
		try {
			Thread.currentThread().setName(THREADNAME);
			getBookingManager().saveFailedOnRequestBookings();
			getBookingManager().saveFailedOnlineBookings();
		} catch (final Exception e) {
			LOG.fatal(e.getMessage(), e);
		}

	}

	public void setBookingManager(final IBookingManager bookingManager) {
		this.bookingManager = bookingManager;
	}

}
