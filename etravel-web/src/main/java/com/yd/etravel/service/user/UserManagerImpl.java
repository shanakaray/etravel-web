/**
 * 
 */
package com.yd.etravel.service.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.yd.etravel.domain.custom.user.UserSearchDTO;
import com.yd.etravel.domain.hotel.Hotel;
import com.yd.etravel.domain.user.User;
import com.yd.etravel.domain.user.role.Function;
import com.yd.etravel.domain.user.role.Role;
import com.yd.etravel.persistence.dao.user.IUserDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;
import com.yd.etravel.service.util.IUserProfile;
import com.yd.etravel.service.util.UserProfile;
import com.yd.etravel.util.IConstants;
import com.yd.etravel.util.IConstants.IUserFunctions;
import com.yd.etravel.util.IConstants.IUserRoles;
import com.yd.etravel.util.PasswordEncrypt;
import com.yd.etravel.util.mail.MailMessage;

/**
 * 
 * @author : Yohan Ranasinghe. Created Date : Feb 4, 2009 : 5:48:56 PM Type :
 *         com.yd.etravel.service.user.UserManagerImpl
 * 
 */
public class UserManagerImpl implements IUserManager {

    private static List<String> ACCESS_USER_ROLES = new ArrayList<String>();
    private static List<String> ACCESS_CUSTOMER_ROLES = new ArrayList<String>();

    static {
	ACCESS_USER_ROLES.add(IConstants.IUserRoles.SYSADMIN_ROLE_NAME);
	ACCESS_USER_ROLES.add(IConstants.IUserRoles.HOTEL_ADMIN_ROLE_NAME);
	ACCESS_CUSTOMER_ROLES.add(IConstants.IUserRoles.CUSTOMER_ROLE_NAME);
	ACCESS_CUSTOMER_ROLES.add(IConstants.IUserRoles.AGENT_ROLE_NAME);
    }

    private IUserDAO userDAO;
    private MailMessage mailMessage;

    public IUserProfile getProfile() {
	return new UserProfile();
    }

    public void setUserDAO(IUserDAO userDAO) {
	this.userDAO = userDAO;
    }

    public MailMessage getMailMessage() {
	return mailMessage;
    }

    public void setMailMessage(MailMessage mailMessage) {
	this.mailMessage = mailMessage;
    }

    public IUserProfile authanticateUser(final String username,
	    final String password) throws ServiceException {
	IUserProfile userProfile = getProfile();
	try {
	    User user = userDAO.findAuth(username,
		    PasswordEncrypt.encrypt(password), null);
	    if (user == null) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.authantication.fail"));
	    } else {
		userProfile = new UserHelper()
			.getUserProfile(userProfile, user);
	    }

	    List<Hotel> hlist = userDAO.findAssignedHotels(user.getId());
	    for (Hotel h : hlist) {
		userProfile.putHotel(h.getId(), h.getName());
	    }

	    if (!userProfile.hasFunction(IUserFunctions.IS_USER_CAN_LOGIN)) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.authantication.no.rights"));
	    }
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return userProfile;
    }

    public IUserProfile authanticateCustomer(final String username,
	    final String password) throws ServiceException {
	IUserProfile userProfile = getProfile();
	try {
	    User user = userDAO.findAuth(username,
		    PasswordEncrypt.encrypt(password), ACCESS_CUSTOMER_ROLES);
	    if (user == null) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.authantication.fail"));
	    } else {
		userProfile = new UserHelper()
			.getUserProfile(userProfile, user);
	    }

	    if (!userProfile.hasFunction(IUserFunctions.IS_USER_CAN_LOGIN)) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.authantication.no.rights"));
	    }

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return userProfile;
    }

    public User save(final User user, Long[] ids) throws ServiceException {
	try {

	    if (userDAO.isUsernameExist(user.getName(), user.getId())) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.username.exist"));
	    }

	    if (ids != null && ids.length > 0) {
		List<Role> roles = userDAO.findAll(Role.class, ids);
		user.setRoles(roles);

	    }

	    if (user.getId() == null) {
		user.encriptPW();
		userDAO.save(user);
	    } else {
		userDAO.update(user);
	    }

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return user;
    }

    public User save(final User user) throws ServiceException {
	try {

	    if (userDAO.isUsernameExist(user.getName(), user.getId())) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.username.exist"));
	    }

	    if (user.getId() == null) {
		userDAO.save(user);
		user.encriptPW();
	    } else {
		userDAO.update(user);
	    }
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return user;
    }

    public IUserProfile saveCustomer(User user) throws ServiceException {

	IUserProfile userProfile = getProfile();
	try {

	    if (userDAO.isUsernameExist(user.getName(), user.getId())) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.username.exist"));
	    }

	    List<Role> roles = new ArrayList<Role>();
	    Role role = (Role) userDAO.findById(Role.class,
		    IUserRoles.CUSTOMER_ROLE_ID);
	    roles.add(role);
	    user.setRoles(roles);

	    userProfile = new UserHelper().getUserProfile(userProfile, user);

	    user.encriptPW();
	    if (user.getId() == null) {
		user = (User) userDAO.save(user);
		mailMessage.setParam(userProfile.getParams());
		mailMessage.sendMail();
	    } else {
		user = (User) userDAO.update(user);
	    }
	    userProfile.setFunctionKeySet(user.getFunctionSet());
	    userProfile.setId(user.getId());

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return userProfile;
    }

    public Role saveUserRole(final Role role) throws ServiceException {
	try {
	    if (role.getId() == null) {
		if (userDAO.isUserRoleExist(role.getName())) {
		    throw new ServiceException(
			    ValidationHelper
				    .getMessageHolder("etravel.userrole.exist"));
		}
		userDAO.save(role);
	    } else {
		userDAO.update(role);
	    }
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return role;
    }

    public List<Role> findAllActiveRoles() throws ServiceException {
	try {
	    return userDAO.findAllActive(Role.class);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public Role findRoleById(Long id) throws ServiceException {
	Role role = null;
	try {
	    role = (Role) userDAO.findById(Role.class, id);
	    role.toString();

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return role;
    }

    public List<Role> findAllRoles() throws ServiceException {
	try {
	    return userDAO.findAll(Role.class);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public int deleteRole(Long id) throws ServiceException {
	int flag = 0;
	try {
	    flag = userDAO.deleteAny(Role.class, id);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return flag;
    }

    public List<User> findUsers(UserSearchDTO userSearchDTO)
	    throws ServiceException {
	try {
	    return  userDAO.findUsers(userSearchDTO);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public User findUserById(Long id) throws ServiceException {
	User usr;
	try {
	    usr = userDAO.findById(id);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return usr;
    }

    public List<Role> findRolesById(Long[] id) throws ServiceException {
	try {
	    List<Role> list = userDAO.findAll(Role.class, id);
	    return list;
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}

    }

    public int deleteUser(Long id) throws ServiceException {
	int flag = 0;
	try {
	    flag = userDAO.deleteUser(id);

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return flag;
    }

    public List<Function> findAllFunctions() throws ServiceException {
	try {
	    List<Function> list = userDAO.findAllFunctions();
	    return list;
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public List<Function> findFindByRoleId(Long id) throws ServiceException {
	try {
	    List<Function> list = userDAO.findFunctionByRoleId(id);
	    return list;
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public void saveUserRoleFunctions(Long roleId, List<Long> functionIds)
	    throws ServiceException {
	try {

	    Role role = (Role) userDAO.findById(Role.class, roleId);
	    role.getFunction().size();
	    for (Iterator<Function> funcIt = role.getFunction().iterator(); funcIt.hasNext();) {
		if (!functionIds.contains(funcIt.next().getId())) {
		    funcIt.remove();
		}
	    }

	    for (Long fid : functionIds) {
		if (!role.hasFunctionId(fid)) {
		    role.getFunction().add((Function) userDAO.findById(Function.class, fid));
		}
	    }

	    userDAO.save(role);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public IUserProfile findUserProfile(String username)
	    throws ServiceException {
	IUserProfile profile = getProfile();
	try {
	    User user = userDAO.findUser(username);
	    if (user == null) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.authantication.fail"));
	    } else {
		profile = new UserHelper().getUserProfile(profile, user);
	    }

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return profile;
    }

    public void saveUserPassWord(Long userId, String oldPw, String newPw)
	    throws ServiceException {
	try {
	    User usr = (User) userDAO.findById(userId);

	    if (usr == null
		    || !usr.getPassword().equals(
			    PasswordEncrypt.encrypt(oldPw.trim()))) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.error.old.pw.invalid"));
	    }

	    usr.setPassword(PasswordEncrypt.encrypt(newPw.trim()));
	    userDAO.update(usr);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}

    }

}
