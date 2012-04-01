package com.yd.etravel.persistence.dao.user;

import java.util.List;

import com.yd.etravel.domain.custom.user.UserSearchDTO;
import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.user.User;
import com.yd.etravel.domain.user.role.Function;
import com.yd.etravel.persistence.dao.common.IBaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

public interface IUserDAO extends IBaseDAO {

    public User findAuth(final String username, final String password,
	    final List<String> roles) throws PersistenceException;

    public User findUser(final String username) throws PersistenceException;

    public boolean isUsernameExist(final String username, final Long id)
	    throws PersistenceException;

    public boolean isUserRoleExist(final String username)
	    throws PersistenceException;

    public List<User> findUsers(UserSearchDTO userSearchDTO)
	    throws PersistenceException;

    public User findById(Long id) throws PersistenceException;

    public int deleteUser(Long id) throws PersistenceException;

    public List<Function> findFunctionByRoleId(Long id)
	    throws PersistenceException;

    public List<Function> findAllFunctions() throws PersistenceException;

    public List<Hotel> findAssignedHotels(final Long userId)
	    throws PersistenceException;
}
