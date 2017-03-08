/**
 * 
 */
package com.swipejobs.services.rest.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents a worker.
 * @author reyo
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Worker {
	
	private int rating;
	private boolean isActive;
	private List<String> certificates;
	private List<String> skills;
	private SearchAddress jobSearchAddress;
	private String transportation;
	private boolean hasDriversLicense = false;
	private List<Availability> availability;
	private String phone;
	private String email;
	private Name name;
	private int age;
	private String guid;
	private int userId;
	
	public static class Name {
		private String first;
		private String last;
		/**
		 * @return the first
		 */
		public String getFirst() {
			return first;
		}
		/**
		 * @param first the first to set
		 */
		public void setFirst(String first) {
			this.first = first;
		}
		/**
		 * @return the last
		 */
		public String getLast() {
			return last;
		}
		/**
		 * @param last the last to set
		 */
		public void setLast(String last) {
			this.last = last;
		}
	}
	

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}


	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}


	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}


	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	/**
	 * @return the certificates
	 */
	public List<String> getCertificates() {
		return certificates;
	}


	/**
	 * @param certificates the certificates to set
	 */
	public void setCertificates(List<String> certificates) {
		this.certificates = certificates;
	}


	/**
	 * @return the skills
	 */
	public List<String> getSkills() {
		return skills;
	}


	/**
	 * @param skills the skills to set
	 */
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}


	/**
	 * @return the jobSearchAddress
	 */
	public SearchAddress getJobSearchAddress() {
		return jobSearchAddress;
	}


	/**
	 * @param jobSearchAddress the jobSearchAddress to set
	 */
	public void setJobSearchAddress(SearchAddress jobSearchAddress) {
		this.jobSearchAddress = jobSearchAddress;
	}


	/**
	 * @return the transportation
	 */
	public String getTransportation() {
		return transportation;
	}


	/**
	 * @param transportation the transportation to set
	 */
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}


	/**
	 * @return the hasDriversLicense
	 */
	public boolean isHasDriversLicense() {
		return hasDriversLicense;
	}


	/**
	 * @param hasDriversLicense the hasDriversLicense to set
	 */
	public void setHasDriversLicense(boolean hasDriversLicense) {
		this.hasDriversLicense = hasDriversLicense;
	}


	/**
	 * @return the availability
	 */
	public List<Availability> getAvailability() {
		return availability;
	}


	/**
	 * @param availability the availability to set
	 */
	public void setAvailability(List<Availability> availability) {
		this.availability = availability;
	}


	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @return the name
	 */
	public Name getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(Name name) {
		this.name = name;
	}


	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}


	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}


	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}


	/**
	 * @param guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}


	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

}
