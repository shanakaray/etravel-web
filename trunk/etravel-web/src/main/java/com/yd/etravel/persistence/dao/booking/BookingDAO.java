/**
 * 
 */
package com.yd.etravel.persistence.dao.booking;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yd.etravel.domain.booking.Booking;
import com.yd.etravel.domain.booking.ExtraItemBooking;
import com.yd.etravel.domain.booking.HotelBooking;
import com.yd.etravel.domain.booking.Payment;
import com.yd.etravel.domain.booking.RoomBooking;
import com.yd.etravel.domain.custom.booking.BookingReportDTO;
import com.yd.etravel.domain.custom.booking.BookingReportSearchDTO;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.util.StringUtils;

@Repository
public class BookingDAO extends BaseDAO<Booking> implements IBookingDAO {

	final static StringBuilder FIND_ALL_BOOKING = new StringBuilder(
			"SELECT roomBookong FROM com.yd.etravel.domain.booking.RoomBooking as roomBookong "
					+ "join fetch  roomBookong.hotelBooking as hotelBooking  "
					+ "join fetch  roomBookong.room as room  "
					+ "join fetch  hotelBooking.hotel as hotel "
					+ "join fetch  hotelBooking.booking as booking ");

	@Override
	public List<RoomBooking> findAllBooking() throws PersistenceException {
		try {
			return getHibernateTemplate().find(FIND_ALL_BOOKING.toString());
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<BookingReportDTO> findBookingDetail(
			final BookingReportSearchDTO searchDTO) throws PersistenceException {
		try {
			final StringBuilder stringBuilder = new StringBuilder();
			stringBuilder
					.append("SELECT new com.yd.etravel.domain.custom.booking.BookingReportDTO(")
					.append("b.id,r.id,rt.id,h.id,u.id")
					.append(",b.code,u.name,u.firstName,u.lastName,u.address,u.contact,u.email,h.name")
					.append(",r.name,rt.name,b.status,b.depatureDate,b.bookingDate,b.cancelDate,b.expireDate")
					.append(",b.totalPrice,b.roomPrice,b.paymentMethod,b.paidAmount")
					.append(",hb.checkInDate,hb.checkOutDate,hb.noOfRoom,agt.id,agt.name")
					.append(") FROM RoomBooking as rb JOIN rb.hotelBooking as hb JOIN hb.booking as b JOIN ")
					.append("hb.hotel as h JOIN b.bookingUser as u")
					.append(" JOIN rb.room as r JOIN r.roomType as rt LEFT JOIN b.agent agt ")
					.append(" WHERE 1=1 ");

			if (searchDTO.getHotelId() != null
					&& !searchDTO.getHotelId().isEmpty()) {
				stringBuilder.append(" AND h.id IN (:hid)");
			}

			if (searchDTO.getBookedBy() != null) {
				stringBuilder.append(" AND u.id=:uid");
			}

			if (searchDTO.getRoomId() != null) {
				stringBuilder.append(" AND r.id=:rid");
			}

			if (searchDTO.getRoomTypeId() != null) {
				stringBuilder.append(" AND rt.id=:rtid");
			}

			if (searchDTO.getBookedFrom() != null) {
				stringBuilder.append(" AND b.bookingDate >= :bfrom");
			}

			if (searchDTO.getBookedTo() != null) {
				stringBuilder.append(" AND b.bookingDate <= :bto");
			}

			if (searchDTO.getId() != null) {
				stringBuilder.append(" AND b.id=:bid");
			}

			if (!StringUtils.isEmpty(searchDTO.getUserName())) {
				stringBuilder.append(" AND UPPER(u.name)=UPPER(:uname) ");
			}

			if (!StringUtils.isEmpty(searchDTO.getUserCode())) {
				stringBuilder.append(" AND UPPER(u.code)=UPPER(:ucode) ");
			}

			if (!StringUtils.isEmpty(searchDTO.getBookingId())) {
				stringBuilder.append(" AND UPPER(b.code)=UPPER(:bcode) ");
			}

			if (searchDTO.getCheckInFrom() != null
					&& searchDTO.getCheckInTo() != null) {
				stringBuilder
						.append(" AND (hb.checkInDate>=:cfdate OR (hb.checkOutDate>=:cfdate  AND hb.checkInDate<:cfdate)) AND hb.checkInDate<=:ctdate ");
			}

			if (searchDTO.getStatusList() != null
					&& !searchDTO.getStatusList().isEmpty()) {
				stringBuilder.append(" AND b.status IN (:stalst) ");
			}

			stringBuilder.append(" ORDER BY hb.checkInDate ");
			final Query query = getCurrentSession().createQuery(
					stringBuilder.toString());

			if (searchDTO.getHotelId() != null
					&& !searchDTO.getHotelId().isEmpty()) {
				query.setParameterList("hid", searchDTO.getHotelId());
			}
			if (searchDTO.getRoomId() != null) {
				query.setParameter("rid", searchDTO.getRoomId());
			}
			if (searchDTO.getBookedBy() != null) {
				query.setParameter("uid", searchDTO.getBookedBy());
			}
			if (searchDTO.getRoomTypeId() != null) {
				query.setParameter("rtid", searchDTO.getRoomTypeId());
			}

			if (!StringUtils.isEmpty(searchDTO.getUserName())) {
				query.setParameter("ucode", searchDTO.getUserName());
			}

			if (!StringUtils.isEmpty(searchDTO.getUserCode())) {
				query.setParameter("uname", searchDTO.getUserCode());
			}
			if (!StringUtils.isEmpty(searchDTO.getBookingId())) {
				query.setParameter("bcode", searchDTO.getBookingId());
			}
			if (searchDTO.getBookedFrom() != null) {
				query.setParameter("bfrom", searchDTO.getBookedFrom());
			}

			if (searchDTO.getBookedTo() != null) {
				query.setParameter("bto", searchDTO.getBookedTo());
			}

			if (searchDTO.getCheckInFrom() != null
					&& searchDTO.getCheckInTo() != null) {
				query.setParameter("cfdate", searchDTO.getCheckInFrom());
				query.setParameter("ctdate", searchDTO.getCheckInTo());
			}
			if (searchDTO.getId() != null) {
				query.setParameter("bid", searchDTO.getId());
			}

			if (searchDTO.getStatusList() != null
					&& !searchDTO.getStatusList().isEmpty()) {
				query.setParameterList("stalst", searchDTO.getStatusList());
			}

			return query.list();

		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<RoomBooking> findExpiredBookings(final Date date,
			final String status, final String paymentMethod)
			throws PersistenceException {
		try {
			final StringBuilder sb = new StringBuilder();
			sb.append("SELECT r ")
					.append(" FROM RoomBooking r JOIN fetch r.hotelBooking h JOIN fetch h.booking b ")
					.append(" WHERE b.expireDate <= :expdate and  b.status=:status and  b.paymentMethod=:pm ");
			final Query query = getCurrentSession().createQuery(sb.toString());
			query.setParameter("expdate", date);
			query.setParameter("status", status);
			query.setParameter("pm", paymentMethod);
			return query.list();

		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public RoomBooking findRoomBooking(final String bookingId)
			throws PersistenceException {
		try {
			final StringBuilder stringBuilder = new StringBuilder();
			stringBuilder
					.append("SELECT r ")
					.append(" FROM RoomBooking r JOIN fetch r.room rr JOIN fetch rr.roomType rt JOIN fetch r.hotelBooking h JOIN fetch h.hotel hh JOIN fetch h.booking b ")
					.append(" WHERE b.code = :code");
			final Query query = getCurrentSession().createQuery(
					stringBuilder.toString());
			query.setParameter("code", bookingId);
			final List<RoomBooking> list = query.list();
			return !list.isEmpty() ? list.get(0) : null;

		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	protected Class getEntityClass() {
		return RoomBooking.class;
	}

	@Override
	public HotelBooking save(final HotelBooking hotelBooking)
			throws PersistenceException {
		try {
			getCurrentSession().save(hotelBooking);
			return hotelBooking;
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public RoomBooking save(final RoomBooking roomBooking)
			throws PersistenceException {
		try {
			getCurrentSession().save(roomBooking);
			return roomBooking;
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public Payment save(final Payment payment) throws PersistenceException {
		try {
			getCurrentSession().save(payment);
			return payment;
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public ExtraItemBooking merge(final ExtraItemBooking extraItemBooking)
			throws PersistenceException {
		try {
			getCurrentSession().save(extraItemBooking);
			return extraItemBooking;
		} catch (final HibernateException e) {
			throw new PersistenceException(e);
		}
	}

}
