/**
 * 
 */
package com.address.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.address.base.BaseRepository;
import com.address.entities.AddressEntity;

/**
 * @author prabhu kvn
 *
 */

public class AddressRepository extends BaseRepository<AddressEntity> {

	public static final Logger logger = LogManager.getLogger(AddressRepository.class);
	
	public AddressEntity getAddress(String key) {
		// TODO Auto-generated method stub
		logger.debug("Get Address key {}.",key);
		return new AddressEntity().toJson(super.get(key));
	}

	public String getAddress(String email, String addressName) {
		return this.getFromHSet(email, addressName);
	}

	public Long deleteAllAddresses(String email) {
		// TODO Auto-generated method stub
		logger.debug("Deleting the address for {} :",email);
		return this.delFromHSet(email); 
		
	}

	public Long deleteAddress(String email, String addressName) {
		// TODO Auto-generated method stub
		logger.debug("Deleting the address for email {} and name {}",email,addressName);
		return this.delFromHSet(email,addressName);
	}

	public Long addAddress(String key, String addressName, String json) {
		// TODO Auto-generated method stub
		return super.setInHSet(key,addressName,json);
	}

	
	
	
	
}
