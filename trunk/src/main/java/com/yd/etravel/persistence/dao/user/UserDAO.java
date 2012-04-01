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

    public User findUser(final String username) throws PersistenceException {
	try {
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(FIND_USER.toString());
	    query.setParameter("uname", username);

	    List<User> list = query.list();
	    User user = null;

	    if (!list.isEmpty()) {
		user = list.get(0);
	    }

	    return user;

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}

    }

    public User findAuth(final String username, final String password,
	    List<String> roles) throws PersistenceException {

	final StringBuilder FIND_AUTH = new StringBuilder(
		"FROM User as usr join fetch usr.roles as rol where usr.active=1 AND")
		.append(" UPPER(usr.name) = UPPER(:uname) AND usr.password=:pw ");
	try {
	    if (roles != null && !roles.isEmpty()) {
		FIND_AUTH.append("AND rol.name IN (:roles) ");
	    }

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(FIND_AUTH.toString());
	    query.setParameter("uname", username);
	    query.setParameter("pw", password);

	    if (roles != null && !roles.isEmpty()) {
		query.setParameterList("roles", roles);
	    }

	    List<User> list = query.list();
	    User user = null;

	    if (!list.isEmpty()) {
		user = list.get(0);
	    }

	    return user;

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public List<Hotel> findAssignedHotels(final Long userId)
	    throws PersistenceException {
	try {
	    StringBuilder sqlBuilder = new StringBuilder(
		    "SELECT h FROM Hotel h join h.superUser u where u.id=:uid ");
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(sqlBuilder.toString());
	    query.setParameter("uid", userId);
	    return query.list();
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}

    }

    public boolean isUsernameExist(final String username, final Long id)
	    throws PersistenceException {
	try {
	    StringBuilder sb = new StringBuilder(
		    "SELECT usr FROM User as usr where ")
		    .append(" UPPER(usr.name)= UPPER(:uname) ");

	    if (id != null) {
		sb.append(" AND usr.id!=:id");
	    }

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(sb.toString());
	    query.setParameter("uname", username);

	    if (id != null) {
		query.setParameter("id", id);
	    }

	    return (!query.list().isEmpty());
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public boolean isUserRoleExist(String username) throws PersistenceException {
	try {
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(IS_USER_ROLE_EXIST.toString());
	    query.setParameter("rolename", username);
	    return (!query.list().isEmpty());
	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public List<User> findUsers(UserSearchDTO userSearchDTO)
	    throws PersistenceException {
	try {

	    StringBuilder sqlBuilder = new StringBuilder(
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

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();

	    Query query = session.createQuery(sqlBuilder.toString());

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

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public User findById(Long id) throws PersistenceException {
	try {
	    StringBuilder sb = new StringBuilder(
		    "SELECT usr FROM User as usr left join fetch usr.roles as rol where ");
	    sb.append(" usr.id=:id");

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(sb.toString());
	    query.setParameter("id", id);

	    List<User> list = query.list();

	    if (!list.isEmpty()) {
		return list.get(0);
	    }

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}

	return null;
    }

    public int deleteUser(Long id) throws PersistenceException {
	try {

	    final StringBuilder sb = new StringBuilder("delete from User ")
		    .append(" as obj Where obj.id = (:id) ");
	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();

	    SQLQuery qry = session
		    .createSQLQuery("delete from T_USER_ROLE where FK_USER_ID = :id ");
	    qry.setParameter("id", id);
	    qry.executeUpdate();

	    Query query = session.createQuery(sb.toString());
	    query.setParameter("id", id);

	    return query.executeUpdate();

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	} catch (DataAccessException e) {
	    throw new PersistenceException(e);
	}

    }

    public List<Function> findFunctionByRoleId(Long id)
	    throws PersistenceException {
	try {
	    StringBuilder sb = new StringBuilder(
		    "SELECT f FROM Role as r join r.function as f where ");
	    sb.append(" r.id=:id order by f.key ASC ");

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(sb.toString());
	    query.setParameter("id", id);

	    return query.list();

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

    public List<Function> findAllFunctions() throws PersistenceException {
	try {
	    StringBuilder sb = new StringBuilder("SELECT f FROM Function as f ");
	    sb.append(" order by f.key ASC ");

	    Session session = getHibernateTemplate().getSessionFactory()
		    .getCurrentSession();
	    Query query = session.createQuery(sb.toString());
	    return query.list();

	} catch (HibernateException e) {
	    throw new PersistenceException(e);
	}
    }

}
