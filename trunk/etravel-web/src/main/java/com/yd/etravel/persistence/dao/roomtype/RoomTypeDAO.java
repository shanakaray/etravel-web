/**
 * 
 */
package com.yd.etravel.persistence.dao.roomtype;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

public class RoomTypeDAO extends BaseDAO implements IRoomTypeDAO {

    final static StringBuilder IS_ROOM_TYPE_EXIST = new StringBuilder(
	    "SELECT roomType FROM com.yd.etravel.domain.roomtype.RoomType as roomType where ")
	    .append(" UPPER(roomType.name)= UPPER(:name) ");

    /**
	 * 
	 */
    public RoomTypeDAO() {
	// TODO Auto-generated constructor stub
    }

    @Override
    public boolean isRoomTypeNameExist(final String type, final Long id)
	    throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder(
		    "SELECT roomType FROM com.yd.etravel.domain.roomtype.RoomType as roomType where ")
		    .append(" UPPER(roomType.name)= UPPER(:name) ");

	    if (id != null) {
		sb.append(" AND roomType.id != :id");
	    }

	    final Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    final Query query = session.createQuery(sb.toString());
	    query.setParameter("name", type);

	    if (id != null) {
		query.setParameter("id", id);
	    }

	    return !query.list().isEmpty();
	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

}
