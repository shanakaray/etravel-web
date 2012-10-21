/**
 * 
 */
package com.yd.etravel.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.custom.user.UserSearchDTO;
import com.yd.etravel.domain.user.User;
import com.yd.etravel.web.common.BaseAction;

/**
 * @author yora
 * 
 */
public class UserAction extends BaseAction {

	/**
	 * 
	 */
	public UserAction() {
	}

	@Override
	protected ActionForward add(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward create(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		final UserForm userForm = (UserForm) form;
		User user = null;
		boolean isnew = false;

		if ((userForm.getId() != null) && (userForm.getId() > 0)) {
			user = getUserManager().findUserById(userForm.getId());
			user.setName(userForm.getName());
			user.setFirstName(userForm.getFirstName());
			user.setLastName(userForm.getLastName());
			user.setAddress(userForm.getAddress());
			user.setContact(userForm.getContact());
			user.setEmail(userForm.getEmail());
			user.setActive(userForm.getActive());

			if (userForm.isPasswordReset()) {
				user.setPassword(userForm.getPw());
				user.encriptPW();
			}

		} else {
			user = userForm.getUser();
			isnew = true;
		}

		getUserManager().save(user, userForm.getRoleIds());
		userForm.setPw("");
		userForm.setRepw("");
		userForm.setPasswordReset(false);
		if (isnew) {
			addInfoMessages(COM_SAVE_MSG);
		} else {
			addInfoMessages(COM_UPDATE_MSG);
		}
		return mapping.findForward(SUCCESS);
	}

	@Override
	protected ActionForward delete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		final UserForm userForm = (UserForm) form;
		getUserManager().deleteUser(userForm.getId());
		addInfoMessages(COM_DELETE_MSG);
		return mapping.findForward(SUCCESS);
	}

	@Override
	protected ActionForward edit(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		final UserForm userForm = (UserForm) form;

		init(mapping, form, request, response);

		final User user = getUserManager().findUserById(userForm.getId());
		userForm.setUser(user);
		userForm.setPw("");
		userForm.setRepw("");
		userForm.setPasswordReset(false);
		userForm.setRoleIds(user.getRoleIds().toArray(new Long[0]));
		return mapping.findForward(SUCCESS);
	}

	@Override
	protected ActionForward find(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		return null;
	}

	@Override
	protected ActionForward forward(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		init(mapping, form, request, response);
		return mapping.findForward(SUCCESS);
	}

	@Override
	protected ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		final UserForm userForm = (UserForm) form;
		userForm.setAllRoles(getUserManager().findAllActiveRoles());
		userForm.setAllUsers(getUserManager().findUsers(new UserSearchDTO()));
		return mapping.findForward(SUCCESS);
	}

	@Override
	public ActionForward process(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		return null;
	}

	@Override
	protected ActionForward save(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		return mapping.findForward(SUCCESS);
	}

	@Override
	public ActionForward search(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ActionForward send(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		return null;
	}

	@Override
	protected ActionForward sort(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		return null;
	}

}
