package com.yd.etravel.persistence.dao.user;

import java.util.List;

import com.yd.etravel.domain.custom.user.UserSearchDTO;
import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.user.User;
import com.yd.etravel.domain.user.role.Function;
import com.yd.etravel.domain.user.role.Role;
import com.yd.etravel.persistence.dao.common.IBaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

public interface IUserDAO extends IBaseDAO<User> {

	public int deleteRole(Long id) throws PersistenceException;

	public int deleteUser(Long id) throws PersistenceException;

	public List<Role> findAllActiveRoles() throws PersistenceException;

	public List<Function> findAllFunctions() throws PersistenceException;

	public List<Role> findAllRoles() throws PersistenceException;

	public List<Role> findAllUserRoles(Long[] ids) throws PersistenceException;

	public List<Hotel> findAssignedHotels(final Long userId)
			throws PersistenceException;

	public User findAuth(final String username, final String password,
			final List<String> roles) throws PersistenceException;

	@Override
	public User findById(Long id) throws PersistenceException;

	public Function findFunctionById(Long fid) throws PersistenceException;

	public List<Function> findFunctionByRoleId(Long id)
			throws PersistenceException;

	public Role findRoleById(Long customerRoleId) throws PersistenceException;

	public User findUser(final String username) throws PersistenceException;

	public List<User> findUsers(UserSearchDTO userSearchDTO)
			throws PersistenceException;

	public boolean isUsernameExist(final String username, final Long id)
			throws PersistenceException;

	public boolean isUserRoleExist(final String username)
			throws PersistenceException;

	public void saveRole(Role role) throws PersistenceException;

	public void updateRole(Role role) throws PersistenceException;
}
