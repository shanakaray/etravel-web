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
	    final BookingReportSearchDTO dto) throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder();
	    sb.append(
		    "SELECT new com.yd.etravel.domain.custom.booking.BookingReportDTO(")
		    .append("b.id,r.id,rt.id,h.id,u.id")
		    .append(",b.code,u.name,u.firstName,u.lastName,u.address,u.contact,u.email,h.name")
		    .append(",r.name,rt.name,b.status,b.depatureDate,b.bookingDate,b.cancelDate,b.expireDate")
		    .append(",b.totalPrice,b.roomPrice,b.paymentMethod,b.paidAmount")
		    .append(",hb.checkInDate,hb.checkOutDate,hb.noOfRoom,agt.id,agt.name")
		    .append(") FROM RoomBooking as rb JOIN rb.hotelBooking as hb JOIN hb.booking as b JOIN ")
		    .append("hb.hotel as h JOIN b.bookingUser as u")
		    .append(" JOIN rb.room as r JOIN r.roomType as rt LEFT JOIN b.agent agt ")
		    .append(" WHERE 1=1 ");

	    if (dto.getHotelId() != null && !dto.getHotelId().isEmpty()) {
		sb.append(" AND h.id IN (:hid)");
	    }

	    if (dto.getBookedBy() != null) {
		sb.append(" AND u.id=:uid");
	    }

	    if (dto.getRoomId() != null) {
		sb.append(" AND r.id=:rid");
	    }

	    if (dto.getRoomTypeId() != null) {
		sb.append(" AND rt.id=:rtid");
	    }

	    if (dto.getBookedFrom() != null) {
		sb.append(" AND b.bookingDate >= :bfrom");
	    }

	    if (dto.getBookedTo() != null) {
		sb.append(" AND b.bookingDate <= :bto");
	    }

	    if (dto.getId() != null) {
		sb.append(" AND b.id=:bid");
	    }

	    if (!StringUtils.isEmpty(dto.getUserName())) {
		sb.append(" AND UPPER(u.name)=UPPER(:uname) ");
	    }

	    if (!StringUtils.isEmpty(dto.getUserCode())) {
		sb.append(" AND UPPER(u.code)=UPPER(:ucode) ");
	    }

	    if (!StringUtils.isEmpty(dto.getBookingId())) {
		sb.append(" AND UPPER(b.code)=UPPER(:bcode) ");
	    }

	    if (dto.getCheckInFrom() != null && dto.getCheckInTo() != null) {
		sb.append(" AND (hb.checkInDate>=:cfdate OR (hb.checkOutDate>=:cfdate  AND hb.checkInDate<:cfdate)) AND hb.checkInDate<=:ctdate ");
	    }

	    if (dto.getStatusList() != null && !dto.getStatusList().isEmpty()) {
		sb.append(" AND b.status IN (:stalst) ");
	    }

	    sb.append(" ORDER BY hb.checkInDate ");
	    final Query query = getCurrentSession().createQuery(sb.toString());

	    if (dto.getHotelId() != null && !dto.getHotelId().isEmpty()) {
		query.setParameterList("hid", dto.getHotelId());
	    }
	    if (dto.getRoomId() != null) {
		query.setParameter("rid", dto.getRoomId());
	    }
	    if (dto.getBookedBy() != null) {
		query.setParameter("uid", dto.getBookedBy());
	    }
	    if (dto.getRoomTypeId() != null) {
		query.setParameter("rtid", dto.getRoomTypeId());
	    }

	    if (!StringUtils.isEmpty(dto.getUserName())) {
		query.setParameter("ucode", dto.getUserName());
	    }

	    if (!StringUtils.isEmpty(dto.getUserCode())) {
		query.setParameter("uname", dto.getUserCode());
	    }
	    if (!StringUtils.isEmpty(dto.getBookingId())) {
		query.setParameter("bcode", dto.getBookingId());
	    }
	    if (dto.getBookedFrom() != null) {
		query.setParameter("bfrom", dto.getBookedFrom());
	    }

	    if (dto.getBookedTo() != null) {
		query.setParameter("bto", dto.getBookedTo());
	    }

	    if (dto.getCheckInFrom() != null && dto.getCheckInTo() != null) {
		query.setParameter("cfdate", dto.getCheckInFrom());
		query.setParameter("ctdate", dto.getCheckInTo());
	    }
	    if (dto.getId() != null) {
		query.setParameter("bid", dto.getId());
	    }

	    if (dto.getStatusList() != null && !dto.getStatusList().isEmpty()) {
		query.setParameterList("stalst", dto.getStatusList());
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
    public RoomBooking findRoomBooking(final String bookingid)
	    throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder();
	    sb.append("SELECT r ")
		    .append(" FROM RoomBooking r JOIN fetch r.room rr JOIN fetch rr.roomType rt JOIN fetch r.hotelBooking h JOIN fetch h.hotel hh JOIN fetch h.booking b ")
		    .append(" WHERE b.code = :code");
	    final Query query = getCurrentSession().createQuery(sb.toString());
	    query.setParameter("code", bookingid);
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
