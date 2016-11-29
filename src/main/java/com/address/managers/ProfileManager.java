/**
 * 
 */
package com.address.managers;

import com.address.entities.ProfileEntity;
import com.address.repositories.ProfileRepository;

import io.vertx.core.json.JsonObject;

/**
 * @author prabhu kvn
 *
 */
public class ProfileManager {

	ProfileRepository profileRepository = new ProfileRepository();
	AddressManager addressManager = new AddressManager();
	

	/**
	 * Add the profile to repository
	 * 
	 * @param profileEntity
	 * @return
	 */
	public String addProfile(ProfileEntity profileEntity) {

		return profileRepository.addProfile(profileEntity);
	}

	/**
	 * Delete profile from repository
	 * 
	 * @param profileEntity
	 * @return
	 */
	public Long deleteProfile(ProfileEntity profileEntity) {
		// TODO Auto-generated method stub
		return profileRepository.deleteProfile(profileEntity);

	}

	public int loginProfile(ProfileEntity profileEntity) {
		// TODO Auto-generated method stub
		String profileString =  profileRepository.get(profileEntity.getKey());
		ProfileEntity existingProfileEntity = new ProfileEntity().fromJson(profileString);
		return profileEntity.compareTo(existingProfileEntity);
		
	}

	public boolean isProfileExists(ProfileEntity profileEntity) {
		// TODO Auto-generated method stub
		String profileString =  profileRepository.get(profileEntity.getKey());
		ProfileEntity existingProfileEntity = new ProfileEntity().fromJson(profileString);
		return profileEntity.compareTo(existingProfileEntity)>0;
	}

	public boolean isEmailExists(ProfileEntity profileEntity) {
		String profileString =  profileRepository.get(profileEntity.getKey());
		ProfileEntity existingProfileEntity = new ProfileEntity().fromJson(profileString);
		return profileEntity.getEmail().equals(existingProfileEntity.getEmail());
	}

	public String updateProfile(ProfileEntity profileEntity) {
		// TODO Auto-generated method stub
		String profileString =  profileRepository.get(profileEntity.getKey());
		ProfileEntity existingProfileEntity = new ProfileEntity().fromJson(profileString);
		if(profileEntity.compareTo(existingProfileEntity)>0){
			return profileRepository.updateProfile(profileEntity);
		}else {
			return null;
		}
	}

	public String fullProfile(ProfileEntity profileEntity) {
		// TODO Auto-generated method stub
		String address = addressManager.getAllAddressesForEmail(profileEntity.getEmail());
		JsonObject jsonObject = new JsonObject();
		jsonObject.put("profile", profileEntity.toJson());
		jsonObject.put("addresses", address);
		return jsonObject.encodePrettily();
	}

}
