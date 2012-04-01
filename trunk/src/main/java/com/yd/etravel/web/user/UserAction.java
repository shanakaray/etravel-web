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
    public ActionForward process(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	return null;
    }

    @Override
    protected ActionForward add(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected ActionForward back(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected ActionForward delete(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	final UserForm userForm = (UserForm) form;
	getUserManager().deleteUser(userForm.getId());
	addInfoMessages(COM_DELETE_MSG);
	return mapping.findForward(SUCCESS);
    }

    @Override
    protected ActionForward edit(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	final UserForm userForm = (UserForm) form;

	init(mapping, form, request, response);

	User user = getUserManager().findUserById(userForm.getId());
	userForm.setUser(user);
	userForm.setPw("");
	userForm.setRepw("");
	userForm.setPasswordReset(false);
	userForm.setRoleIds(user.getRoleIds().toArray(new Long[0]));
	return mapping.findForward(SUCCESS);
    }

    @Override
    protected ActionForward find(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	return null;
    }

    @Override
    protected ActionForward forward(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	init(mapping, form, request, response);
	return mapping.findForward(SUCCESS);
    }

    @Override
    protected ActionForward init(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	final UserForm userForm = (UserForm) form;
	userForm.setAllRoles(getUserManager().findAllActiveRoles());
	userForm.setAllUsers(getUserManager().findUsers(new UserSearchDTO()));
	return mapping.findForward(SUCCESS);
    }

    @Override
    protected ActionForward save(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	return mapping.findForward(SUCCESS);
    }

    @Override
    protected ActionForward send(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	return null;
    }

    @Override
    protected ActionForward sort(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	return null;
    }

    @Override
    public ActionForward create(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	final UserForm userForm = (UserForm) form;
	User user = null;
	boolean isnew = false;

	if (userForm.getId() != null && userForm.getId() > 0) {
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
    public ActionForward search(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

}
