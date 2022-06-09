package com.ers.models;

import java.util.Objects;

public class Manager {

	private String mUsername;
	private String mPassword;
	public Manager() {
		super();
	}
	
	public Manager(String mUsername, String mPassword) {
		super();
		this.mUsername = mUsername;
		this.mPassword = mPassword;
	}
	public String getmUsername() {
		return mUsername;
	}
	
	public void setmUsername(String mUsername) {
		this.mUsername = mUsername;
	}
	
	public String getmPassword() {
		return mPassword;
	}
	
	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(mPassword, mUsername);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manager other = (Manager) obj;
		return Objects.equals(mPassword, other.mPassword) && Objects.equals(mUsername, other.mUsername);
	}
	
	@Override
	public String toString() {
		return "Manager [mUsername=" + mUsername + ", mPassword=" + mPassword + "]";
	}
}
