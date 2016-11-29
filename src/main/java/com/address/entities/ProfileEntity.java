/**
 * 
 */
package com.address.entities;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;

import com.address.base.BaseEntity;

/**
 * @author prabhu kvn
 *
 */
public class ProfileEntity implements BaseEntity<ProfileEntity>, Comparable<ProfileEntity> {

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
	 * @param email
	 *            the email to set
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
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int compareTo(ProfileEntity arg0) {
		// TODO Auto-generated method stub
		if (Objects.nonNull(this) && Objects.nonNull(arg0)) {
			if (Strings.isNotBlank(this.email) && this.email.equals(arg0.email)) {

				if (Strings.isNotBlank(this.password) && this.password.equals(arg0.password)) {
					return 1;

				}
			}
		}
		return 0;
	}

}
