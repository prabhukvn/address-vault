/**
 * 
 */
package com.address.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.address.base.BaseRepository;
import com.address.entities.ProfileEntity;

/**
 * @author prabhu kvn
 *
 */
public class ProfileRepository extends BaseRepository<ProfileEntity>{

	Logger logger = LogManager.getLogger(ProfileRepository.class);
	public static int ADDRESS_DATABASE_INDEX = 1;
	
	public ProfileRepository() {
		// TODO Auto-generated constructor stub
		super(ADDRESS_DATABASE_INDEX);
	}
	
	public String addProfile(ProfileEntity profileEntity) {

	
		logger.debug("Adding profile:{}",  profileEntity.toJson());
		return this.setAsString(profileEntity);
		
		
	}
	
}
