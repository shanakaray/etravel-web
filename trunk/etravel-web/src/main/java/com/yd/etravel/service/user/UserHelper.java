/**
 * 
 */
package com.yd.etravel.service.user;

import com.yd.etravel.domain.user.User;
import com.yd.etravel.service.util.IUserProfile;
import com.yd.etravel.service.util.UserProfile;

/**
 * @author yora
 * 
 */
public class UserHelper {

	public IUserProfile getUserProfile(IUserProfile profile, final User user) {
		if (profile == null) {
			profile = new UserProfile();
		}
		profile.setUsername(user.getName());
		profile.setFirstname(user.getFirstName());
		profile.setLastname(user.getLastName());
		profile.setNic(user.getCode());
		profile.setEmail(user.getEmail());
		profile.setAddress(user.getAddress());
		profile.setRoles(user.getRoleNames());
		profile.setId(user.getId());
		profile.setFunctionKeySet(user.getFunctionSet());
		profile.setPassword(user.getPassword());
		return profile;
	}

}
