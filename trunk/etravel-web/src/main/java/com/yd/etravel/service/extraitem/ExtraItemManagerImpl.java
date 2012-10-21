package com.yd.etravel.service.extraitem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yd.etravel.domain.booking.ExtraItemBooking;
import com.yd.etravel.domain.extraitem.ExtraItem;
import com.yd.etravel.persistence.dao.extraitem.IExtraItemDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;

@Service(value = "itemService")
@Transactional(propagation = Propagation.SUPPORTS)
public class ExtraItemManagerImpl implements IExtraItemManager {

	@Autowired(required = true)
	private IExtraItemDAO itemDAO;

	@Transactional
	@Override
	public int deleteExtraItem(final Long id) throws ServiceException {
		final int val = 0;
		try {
			final ExtraItem extraItem = this.itemDAO.findById(id);
			extraItem.getHotel().clear();
			this.itemDAO.delete(extraItem);
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
		return val;
	}

	@Override
	public List<ExtraItem> findAllActiveExtraItems() throws ServiceException {
		try {
			return this.itemDAO.findAllActive();
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public List<ExtraItem> findAllExtraItem() throws ServiceException {
		try {
			return this.itemDAO.findAll();
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public List<ExtraItemBooking> findByBookingId(final Long bookingId)
			throws ServiceException {
		try {
			return this.itemDAO.findByBookingId(bookingId);
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public ExtraItem findExtraItemById(final Long id) throws ServiceException {
		ExtraItem extraItem = null;
		try {

			extraItem = this.itemDAO.findById(id);

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
		return extraItem;
	}

	@Override
	public List<ExtraItem> findExtraItemsByHotel(final Long hotelId)
			throws ServiceException {
		try {
			return this.itemDAO.findAllByHotelId(hotelId);
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	public IExtraItemDAO getItemDAO() {
		return this.itemDAO;
	}

	@Transactional
	@Override
	public ExtraItem saveExtraItem(ExtraItem item) throws ServiceException {
		try {
			if (this.itemDAO.isExist(item.getName(), item.getCode(),
					item.getId())) {
				throw new ServiceException(
						ValidationHelper
								.getMessageHolder("etravel.hotel.extraitem.exist"));
			}

			item = this.itemDAO.saveOrUpdate(item);

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
		return item;
	}

	public void setItemDAO(final IExtraItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

}
