/**
 * 
 */
package com.address.managers;

import com.address.entities.ProfileEntity;
import com.address.repositories.ProfileRepository;

/**
 * @author prabhu kvn
 *
 */
public class ProfileManager {
	
	ProfileRepository profileRepository = new ProfileRepository();

	public String addProfile(ProfileEntity profileEntity) {

		return profileRepository.addProfile(profileEntity);
	}

}
