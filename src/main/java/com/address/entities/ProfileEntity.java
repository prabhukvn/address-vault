/**
 * 
 */
package com.address.entities;

import com.address.base.BaseEntity;

/**
 * @author prabhu kvn
 *
 */
public class ProfileEntity implements BaseEntity<ProfileEntity> {

	private String email;
	private String password;
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return this.email;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
