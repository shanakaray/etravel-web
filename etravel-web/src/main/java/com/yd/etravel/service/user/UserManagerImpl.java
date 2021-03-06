/**
 * 
 */
package com.yd.etravel.service.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
@Service(value = "userService")
@Transactional(propagation = Propagation.SUPPORTS)
public class UserManagerImpl implements IUserManager {

	private static List<String> ACCESSCUSTOMERROLES = Arrays
			.asList(new String[] { IConstants.IUserRoles.CUSTOMERROLENAME,
					IConstants.IUserRoles.AGENTROLENAME });

	@Autowired(required = true)
	private IUserDAO userDAO;
	@Autowired(required = true)
	private MailMessage userNotificationMail;

	@Override
	public IUserProfile authanticateCustomer(final String username,
			final String password) throws ServiceException {
		IUserProfile userProfile = getProfile();
		try {
			final User user = this.userDAO.findAuth(username,
					PasswordEncrypt.encrypt(password), ACCESSCUSTOMERROLES);
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

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
		return userProfile;
	}

	@Override
	public IUserProfile authanticateUser(final String username,
			final String password) throws ServiceException {
		IUserProfile userProfile = getProfile();
		try {
			final User user = this.userDAO.findAuth(username,
					PasswordEncrypt.encrypt(password), null);
			if (user == null) {
				throw new ServiceException(
						ValidationHelper
								.getMessageHolder("etravel.authantication.fail"));
			} else {
				userProfile = new UserHelper()
						.getUserProfile(userProfile, user);
			}

			final List<Hotel> hlist = this.userDAO.findAssignedHotels(user
					.getId());
			for (final Hotel h : hlist) {
				userProfile.putHotel(h.getId(), h.getName());
			}

			if (!userProfile.hasFunction(IUserFunctions.IS_USER_CAN_LOGIN)) {
				throw new ServiceException(
						ValidationHelper
								.getMessageHolder("etravel.authantication.no.rights"));
			}
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
		return userProfile;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int deleteRole(final Long id) throws ServiceException {
		int flag = 0;
		try {
			flag = this.userDAO.deleteRole(id);

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
		return flag;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int deleteUser(final Long id) throws ServiceException {
		int flag = 0;
		try {
			flag = this.userDAO.deleteUser(id);

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
		return flag;
	}

	@Override
	public List<Role> findAllActiveRoles() throws ServiceException {
		try {
			return this.userDAO.findAllActiveRoles();

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public List<Function> findAllFunctions() throws ServiceException {
		try {
			final List<Function> list = this.userDAO.findAllFunctions();
			return list;
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public List<Role> findAllRoles() throws ServiceException {
		try {
			return this.userDAO.findAllRoles();

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public List<Function> findFindByRoleId(final Long id)
			throws ServiceException {
		try {
			return this.userDAO.findFunctionByRoleId(id);
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public Role findRoleById(final Long id) throws ServiceException {
		try {
			final Role role = this.userDAO.findAllUserRoles(new Long[] { id })
					.get(0);
			role.toString();
			return role;

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public List<Role> findRolesById(final Long[] id) throws ServiceException {
		try {
			return this.userDAO.findAllUserRoles(id);
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}

	}

	@Override
	public User findUserById(final Long id) throws ServiceException {
		try {
			return this.userDAO.findById(id);
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Override
	public IUserProfile findUserProfile(final String username)
			throws ServiceException {
		IUserProfile profile = getProfile();
		try {
			final User user = this.userDAO.findUser(username);
			if (user == null) {
				throw new ServiceException(
						ValidationHelper
								.getMessageHolder("etravel.authantication.fail"));
			} else {
				profile = new UserHelper().getUserProfile(profile, user);
			}

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
		return profile;
	}

	@Override
	public List<User> findUsers(final UserSearchDTO userSearchDTO)
			throws ServiceException {
		try {
			return this.userDAO.findUsers(userSearchDTO);

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	public IUserProfile getProfile() {
		return new UserProfile();
	}

	public MailMessage getUserNotificationMail() {
		return this.userNotificationMail;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public User save(final User user) throws ServiceException {
		try {

			if (this.userDAO.isUsernameExist(user.getName(), user.getId())) {
				throw new ServiceException(
						ValidationHelper
								.getMessageHolder("etravel.username.exist"));
			}

			if (user.getId() == null) {
				user.encriptPW();
			}
			return this.userDAO.saveOrUpdate(user);

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public User save(final User user, final Long[] ids) throws ServiceException {
		try {

			if (this.userDAO.isUsernameExist(user.getName(), user.getId())) {
				throw new ServiceException(
						ValidationHelper
								.getMessageHolder("etravel.username.exist"));
			}

			if ((ids != null) && (ids.length > 0)) {
				final List<Role> roles = this.userDAO.findAllUserRoles(ids);
				user.setRoles(roles);

			}

			if (user.getId() == null) {
				user.encriptPW();
			}
			return this.userDAO.saveOrUpdate(user);

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public IUserProfile saveCustomer(User user) throws ServiceException {

		IUserProfile userProfile = getProfile();
		try {

			if (this.userDAO.isUsernameExist(user.getName(), user.getId())) {
				throw new ServiceException(
						ValidationHelper
								.getMessageHolder("etravel.username.exist"));
			}

			final List<Role> roles = new ArrayList<Role>();
			final Role role = this.userDAO.findAllUserRoles(
					new Long[] { IUserRoles.CUSTOMER_ROLE_ID }).get(0);
			roles.add(role);
			user.setRoles(roles);

			userProfile = new UserHelper().getUserProfile(userProfile, user);

			user.encriptPW();
			if (user.getId() == null) {
				this.userNotificationMail.setParam(userProfile.getParams());
				this.userNotificationMail.sendMail();
			}
			user = this.userDAO.saveOrUpdate(user);

			userProfile.setFunctionKeySet(user.getFunctionSet());
			userProfile.setId(user.getId());

		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
		return userProfile;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void saveUserPassWord(final Long userId, final String oldPw,
			final String newPw) throws ServiceException {
		try {
			final User user = this.userDAO.findById(userId);

			if ((user == null)
					|| !user.getPassword().equals(
							PasswordEncrypt.encrypt(oldPw.trim()))) {
				throw new ServiceException(
						ValidationHelper
								.getMessageHolder("etravel.error.old.pw.invalid"));
			}

			user.setPassword(PasswordEncrypt.encrypt(newPw.trim()));
			this.userDAO.saveOrUpdate(user);
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Role saveUserRole(final Role role) throws ServiceException {
		try {
			if (role.getId() == null) {
				if (this.userDAO.isUserRoleExist(role.getName())) {
					throw new ServiceException(
							ValidationHelper
									.getMessageHolder("etravel.userrole.exist"));
				}
				this.userDAO.saveRole(role);
			} else {
				this.userDAO.updateRole(role);
			}
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
		return role;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void saveUserRoleFunctions(final Long roleId,
			final List<Long> functionIds) throws ServiceException {
		try {

			final Role role = this.userDAO.findRoleById(roleId);
			role.getFunction().size();
			for (final Iterator<Function> funcIt = role.getFunction()
					.iterator(); funcIt.hasNext();) {
				if (!functionIds.contains(funcIt.next().getId())) {
					funcIt.remove();
				}
			}

			for (final Long fid : functionIds) {
				if (!role.hasFunctionId(fid)) {
					role.getFunction().add(this.userDAO.findFunctionById(fid));
				}
			}

			this.userDAO.saveRole(role);
		} catch (final PersistenceException e) {
			throw new ServiceException(null, e);
		}
	}

	public void setUserDAO(final IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setUserNotificationMail(final MailMessage userNotificationMail) {
		this.userNotificationMail = userNotificationMail;
	}

}
