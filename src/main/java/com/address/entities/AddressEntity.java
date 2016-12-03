/**
 * 
 */
package com.address.entities;

import java.util.ArrayList;
import java.util.List;

import com.address.base.BaseEntity;
import com.google.gson.Gson;

/**
 * @author prabhu kvn
 *
 */
public class AddressEntity implements BaseEntity<AddressEntity>{
	
	/**
	 * Instance Variables
	 */
	private String email="";
	
	private List<AddressData> addresses = new ArrayList<>();
	
	/**
	 * Empty Constructor
	 */
	public AddressEntity() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @return
	 */
	public String getKey(){
		return this.email;
	}
	
	/**
	 * 
	 * @param email
	 * @param addressName
	 * @return
	 */
	public String getKey(String email){
		return email;
	}
	
	
	
	
	
	/**
	 * @return the adsresses
	 */
	public List<AddressData> getAddresses() {
		return addresses;
	}
	/**
	 * @param adsresses the adsresses to set
	 */
	public void setAddresses(List<AddressData> adsresses) {
		this.addresses = adsresses;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * Inner class to represent address data
	 * @author prabhu kvn
	 *
	 */
	public class AddressData{
		
		private String addressName = "";
		private String firstName = "";
		private String lastName="";
		private String middleName="";
		private long mobileNumber;
		private long phoneNumber;
		
		private String fullAddress="";
		private int pinCode;
		private String landMark="";
		private String lattitude="";
		private String longitude="";
		private String qrCodeURL="";
		/**
		 * @return the addressName
		 */
		public String getAddressName() {
			return addressName;
		}
		/**
		 * @param addressName the addressName to set
		 */
		public void setAddressName(String addressName) {
			this.addressName = addressName;
		}
		/**
		 * @return the firstName
		 */
		public String getFirstName() {
			return firstName;
		}
		/**
		 * @param firstName the firstName to set
		 */
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		/**
		 * @return the lastName
		 */
		public String getLastName() {
			return lastName;
		}
		/**
		 * @param lastName the lastName to set
		 */
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		/**
		 * @return the middleName
		 */
		public String getMiddleName() {
			return middleName;
		}
		/**
		 * @param middleName the middleName to set
		 */
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
		/**
		 * @return the mobileNumber
		 */
		public long getMobileNumber() {
			return mobileNumber;
		}
		/**
		 * @param mobileNumber the mobileNumber to set
		 */
		public void setMobileNumber(long mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		/**
		 * @return the phoneNumber
		 */
		public long getPhoneNumber() {
			return phoneNumber;
		}
		/**
		 * @param phoneNumber the phoneNumber to set
		 */
		public void setPhoneNumber(long phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		/**
		 * @return the fullAddress
		 */
		public String getFullAddress() {
			return fullAddress;
		}
		/**
		 * @param fullAddress the fullAddress to set
		 */
		public void setFullAddress(String fullAddress) {
			this.fullAddress = fullAddress;
		}
		/**
		 * @return the pinCode
		 */
		public int getPinCode() {
			return pinCode;
		}
		/**
		 * @param pinCode the pinCode to set
		 */
		public void setPinCode(int pinCode) {
			this.pinCode = pinCode;
		}
		/**
		 * @return the landMark
		 */
		public String getLandMark() {
			return landMark;
		}
		/**
		 * @param landMark the landMark to set
		 */
		public void setLandMark(String landMark) {
			this.landMark = landMark;
		}
		/**
		 * @return the lattitude
		 */
		public String getLattitude() {
			return lattitude;
		}
		/**
		 * @param lattitude the lattitude to set
		 */
		public void setLattitude(String lattitude) {
			this.lattitude = lattitude;
		}
		/**
		 * @return the longitude
		 */
		public String getLongitude() {
			return longitude;
		}
		/**
		 * @param longitude the longitude to set
		 */
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		/**
		 * @return the qrCodeURL
		 */
		public String getQrCodeURL() {
			return qrCodeURL;
		}
		/**
		 * @param qrCodeURL the qrCodeURL to set
		 */
		public void setQrCodeURL(String qrCodeURL) {
			this.qrCodeURL = qrCodeURL;
		}
		
		
		public String getJson() {
			// TODO Auto-generated method stub
			return new Gson().toJson(this);
		}
		
		public AddressData toJson(String value){
			
			return new Gson().fromJson(value, getClass());
		}
		
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.toJson();
	}
	

	
}
