/**
 * 
 */
package com.yd.etravel.persistence.dao.user;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;

import com.yd.etravel.domain.custom.user.UserSearchDTO;
import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.user.User;
import com.yd.etravel.domain.user.role.Function;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

public class UserDAO extends BaseDAO implements IUserDAO {

    final static StringBuilder FIND_USER = new StringBuilder(
	    "FROM User as usr join fetch usr.roles as rol where usr.active=1 AND")
	    .append(" UPPER(usr.name) = UPPER(:uname)");

    final static StringBuilder IS_USER_NAME_EXIST = new StringBuilder(
	    "SELECT usr FROM User as usr where ")
	    .append(" UPPER(usr.name)= UPPER(:uname) ");

    final static StringBuilder IS_USER_ROLE_EXIST = new StringBuilder(
	    "SELECT r FROM Role as r where ")
	    .append(" UPPER(r.name)= UPPER(:rolename) ");

    /**
	 * 
	 */
    public UserDAO() {
    }

    @Override
    public User findUser(final String username) throws PersistenceException {
	try {
	    final Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    final Query query = session.createQuery(FIND_USER.toString());
	    query.setParameter("uname", username);

	    final List<User> list = query.list();
	    User user = null;

	    if (!list.isEmpty()) {
		user = list.get(0);
	    }

	    return user;

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}

    }

    @Override
    public User findAuth(final String username, final String password,
	    final List<String> roles) throws PersistenceException {

	final StringBuilder FIND_AUTH = new StringBuilder(
		"FROM User as usr join fetch usr.roles as rol where usr.active=1 AND")
		.append(" UPPER(usr.name) = UPPER(:uname) AND usr.password=:pw ");
	try {
	    if (roles != null && !roles.isEmpty()) {
		FIND_AUTH.append("AND rol.name IN (:roles) ");
	    }

	    final Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    final Query query = session.createQuery(FIND_AUTH.toString());
	    query.setParameter("uname", username);
	    query.setParameter("pw", password);

	    if (roles != null && !roles.isEmpty()) {
		query.setParameterList("roles", roles);
	    }

	    final List<User> list = query.list();
	    User user = null;

	    if (!list.isEmpty()) {
		user = list.get(0);
	    }

	    return user;

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public List<Hotel> findAssignedHotels(final Long userId)
	    throws PersistenceException {
	try {
	    final StringBuilder sqlBuilder = new StringBuilder(
		    "SELECT h FROM Hotel h join h.superUser u where u.id=:uid ");
	    final Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    final Query query = session.createQuery(sqlBuilder.toString());
	    query.setParameter("uid", userId);
	    return query.list();
	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}

    }

    @Override
    public boolean isUsernameExist(final String username, final Long id)
	    throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder(
		    "SELECT usr FROM User as usr where ")
		    .append(" UPPER(usr.name)= UPPER(:uname) ");

	    if (id != null) {
		sb.append(" AND usr.id!=:id");
	    }

	    final Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    final Query query = session.createQuery(sb.toString());
	    query.setParameter("uname", username);

	    if (id != null) {
		query.setParameter("id", id);
	    }

	    return !query.list().isEmpty();
	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public boolean isUserRoleExist(final String username) throws PersistenceException {
	try {
	    final Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    final Query query = session.createQuery(IS_USER_ROLE_EXIST.toString());
	    query.setParameter("rolename", username);
	    return !query.list().isEmpty();
	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public List<User> findUsers(final UserSearchDTO userSearchDTO)
	    throws PersistenceException {
	try {

	    final StringBuilder sqlBuilder = new StringBuilder(
		    "SELECT user FROM User user ");

	    if (userSearchDTO.hasRolesIds()) {
		sqlBuilder.append("JOIN user.roles as rol ");
	    }

	    sqlBuilder.append("WHERE 1=1 ");

	    if (userSearchDTO.getUserNameSet() != null) {
		sqlBuilder.append("and user.name IN (:nameset) ");
	    }

	    if (userSearchDTO.getIds() != null) {
		sqlBuilder.append("and user.id IN (:ids) ");
	    }

	    if (userSearchDTO.hasRolesIds()) {
		sqlBuilder.append("and rol.id IN (:roleids) ");
	    }

	    if (userSearchDTO.getActive() != null) {
		sqlBuilder.append("and user.active = :active ");
	    }

	    final Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();

	    final Query query = session.createQuery(sqlBuilder.toString());

	    if (userSearchDTO.getUserNameSet() != null) {
		query.setParameterList("nameset",
			userSearchDTO.getUserNameSet());
	    }

	    if (userSearchDTO.getIds() != null) {
		query.setParameterList("ids", userSearchDTO.getIds());
	    }

	    if (userSearchDTO.hasRolesIds()) {
		query.setParameterList("roleids", userSearchDTO.getRoleIds());
	    }

	    if (userSearchDTO.getActive() != null) {
		query.setParameter("active", userSearchDTO.getActive());
	    }

	    return query.list();

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public User findById(final Long id) throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder(
		    "SELECT usr FROM User as usr left join fetch usr.roles as rol where ");
	    sb.append(" usr.id=:id");

	    final Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    final Query query = session.createQuery(sb.toString());
	    query.setParameter("id", id);

	    final List<User> list = query.list();

	    if (!list.isEmpty()) {
		return list.get(0);
	    }

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}

	return null;
    }

    @Override
    public int deleteUser(final Long id) throws PersistenceException {
	try {

	    final StringBuilder sb = new StringBuilder("delete from User ")
		    .append(" as obj Where obj.id = (:id) ");
	    final Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();

	    final SQLQuery qry = session
		    .createSQLQuery("delete from T_USER_ROLE where FK_USER_ID = :id ");
	    qry.setParameter("id", id);
	    qry.executeUpdate();

	    final Query query = session.createQuery(sb.toString());
	    query.setParameter("id", id);

	    return query.executeUpdate();

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	} catch (final DataAccessException e) {
	    throw new PersistenceException(e);
	}

    }

    @Override
    public List<Function> findFunctionByRoleId(final Long id)
	    throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder(
		    "SELECT f FROM Role as r join r.function as f where ");
	    sb.append(" r.id=:id order by f.key ASC ");

	    final Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    final Query query = session.createQuery(sb.toString());
	    query.setParameter("id", id);

	    return query.list();

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    @Override
    public List<Function> findAllFunctions() throws PersistenceException {
	try {
	    final StringBuilder sb = new StringBuilder("SELECT f FROM Function as f ");
	    sb.append(" order by f.key ASC ");

	    final Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    final Query query = session.createQuery(sb.toString());
	    return query.list();

	} catch (final HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

}
