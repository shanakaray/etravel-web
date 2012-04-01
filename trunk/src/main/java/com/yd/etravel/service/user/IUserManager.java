/**
 * 
 */
package com.yd.etravel.service.user;

import java.util.List;

import com.yd.etravel.domain.custom.user.UserSearchDTO;
import com.yd.etravel.domain.user.User;
import com.yd.etravel.domain.user.role.Function;
import com.yd.etravel.domain.user.role.Role;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.util.IUserProfile;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Feb 4, 2009 : 6:26:39 PM Type :
 *         com.yd.etravel.service.user.IUserManager
 * 
 */
public interface IUserManager {

    public IUserProfile authanticateUser(final String username,
	    final String password) throws ServiceException;

    public IUserProfile findUserProfile(final String username)
	    throws ServiceException;

    public User save(final User user) throws ServiceException;

    public User save(final User user, final Long[] ids) throws ServiceException;

    public int deleteUser(Long id) throws ServiceException;

    public User findUserById(final Long id) throws ServiceException;

    public List<User> findUsers(final UserSearchDTO userSearchDTO)
	    throws ServiceException;

    public List<Role> findAllActiveRoles() throws ServiceException;

    public Role saveUserRole(final Role role) throws ServiceException;

    public List<Role> findAllRoles() throws ServiceException;

    public Role findRoleById(Long id) throws ServiceException;

    public List<Role> findRolesById(Long[] id) throws ServiceException;

    public int deleteRole(Long id) throws ServiceException;

    public IUserProfile saveCustomer(User user) throws ServiceException;

    public IUserProfile authanticateCustomer(final String username,
	    final String password) throws ServiceException;

    public List<Function> findAllFunctions() throws ServiceException;

    public List<Function> findFindByRoleId(Long id) throws ServiceException;

    public void saveUserRoleFunctions(Long roleId, List<Long> functionIds)
	    throws ServiceException;

    public void saveUserPassWord(Long userId, String oldPw, String newPw)
	    throws ServiceException;
}
