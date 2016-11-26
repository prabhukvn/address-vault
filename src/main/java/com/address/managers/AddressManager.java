/**
 * 
 */
package com.address.managers;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import com.address.entities.AddressEntity;
import com.address.entities.AddressEntity.AddressData;
import com.address.repositories.AddressRepository;

/**
 * @author prabhu kvn
 *
 */
public class AddressManager {

	public static final Logger logger = LogManager.getLogger(AddressManager.class);
	
	private AddressRepository repository = new AddressRepository();

	/**
	 * 
	 */
	public AddressManager() {
		// TODO Auto-generated constructor stub
	}

	public boolean addAddress(AddressEntity address) {

		logger.debug("Incoming Address Entity:{}",address.toJson());
		boolean status = false;
		if(address !=null && address instanceof AddressEntity){
			AddressEntity addressEntity = (	AddressEntity)address;
			String key = addressEntity.getKey();
			List<AddressData> addressData = addressEntity.getAddresses();
			if(!Objects.isNull(addressData) && !addressData.isEmpty()){
				int records =0;
				for(AddressData data: addressData){
					Long record = this.repository.addAddress(key, data.getAddressName(),data.getJson());
					
					records+=record;
				}
				logger.debug("Number of records updated {}",records);
				status= true;
			}else {
				logger.debug("Address not updated for {}.",key);
				status= false;
			}
		}
		return status;
	}

	public String getAllAddressesForEmail(String email) {

		logger.debug("Address for email: {}",email);
		
		if(Objects.isNull(email)){
			return null;
		}else {
			Map<String, String> addresses =repository.getAllFromHSet(email);
			AddressEntity addressEntity = new AddressEntity();
			addressEntity.setEmail(email);
			for(String addressName: addresses.keySet()){
				String data = addresses.get(addressName);
				logger.debug("Address data Retrieved {}", data);
				AddressData addressData =  addressEntity.new AddressData().toJson(data);
				addressEntity.getAddresses().add(addressData);
			}
			return addressEntity.toJson();
		}
		
	}

	public AddressEntity getAddress(String email, String addressName) {
		// TODO Auto-generated method stub
		if(Strings.isNotEmpty(email) && Strings.isNotEmpty(addressName)){
			String addressJson = this.repository.getAddress(email, addressName);
			AddressEntity address = new AddressEntity();
			return address.fromJson(addressJson);
		}
		return null;
	}

	/**
	 * Delete all addresses from a profile
	 * @param email
	 * @return
	 */
	public Long deleteAddress(String email) {
		// TODO Auto-generated method stub
		if(Strings.isNotEmpty(email)){
			return this.repository.deleteAllAddresses(email);
		}else {
			return Long.valueOf(0);
		}
	}

	public Long deleteAddress(String email, String addressName) {
		return this.repository.deleteAddress(email,addressName);
		
	}

}
