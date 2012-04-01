/**
 * 
 */
package com.yd.etravel.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.util.IUserProfile;
import com.yd.etravel.util.IConstants.ICommon;
import com.yd.etravel.util.IConstants.IUser;
import com.yd.etravel.web.common.BaseAction;

/**
 * @author XPPRESP3
 * 
 */
public class AuthanticationAction extends BaseAction {

    @Override
    public ActionForward process(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	AuthanticationForm authForm = (AuthanticationForm) form;
	IUserProfile profile = null;

	try {

	    profile = getUserManager().authanticateUser(authForm.getUsername(),
		    authForm.getPassword());

	} catch (ServiceException ex) {
	    authForm.setUsername(ICommon.EMPTY_STRING);
	    authForm.setPassword(ICommon.EMPTY_STRING);
	    throw ex;
	}
	if (profile != null) {
	    request.getSession().removeAttribute(IUser.USER_PROFILE);
	    request.getSession().setAttribute(IUser.USER_PROFILE, profile);
	    Thread.currentThread().setName(profile.getUsername());
	    return mapping.findForward(SUCCESS);
	} else {
	    authForm.setPassword(ICommon.EMPTY_STRING);
	    authForm.setUsername(ICommon.EMPTY_STRING);
	    return mapping.findForward(ERROR);
	}
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
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected ActionForward edit(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected ActionForward find(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected ActionForward forward(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected ActionForward init(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected ActionForward save(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected ActionForward send(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected ActionForward sort(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ActionForward create(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ActionForward search(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

}
