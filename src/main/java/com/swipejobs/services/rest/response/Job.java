package com.swipejobs.services.rest.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents a Job
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Job {
	private boolean driverLicenseRequired = false;
	private List<String> requiredCertificates;
	private Location location;
	private String billingRate;
	private int workersRequired;
	private Date startDate;
	private String jobTitle;
	private String company;
	private String guid;
	private int jobId;

	/**
	 * @return the driverLicenseRequired
	 */
	public boolean isDriverLicenseRequired() {
		return driverLicenseRequired;
	}


	/**
	 * @param driverLicenseRequired the driverLicenseRequired to set
	 */
	public void setDriverLicenseRequired(boolean driverLicenseRequired) {
		this.driverLicenseRequired = driverLicenseRequired;
	}


	/**
	 * @return the requiredCertificates
	 */
	public List<String> getRequiredCertificates() {
		return requiredCertificates;
	}


	/**
	 * @param requiredCertificates the requiredCertificates to set
	 */
	public void setRequiredCertificates(List<String> requiredCertificates) {
		this.requiredCertificates = requiredCertificates;
	}


	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}


	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}


	/**
	 * @return the billingRate
	 */
	public String getBillingRate() {
		return billingRate;
	}


	/**
	 * @param billingRate the billingRate to set
	 */
	public void setBillingRate(String billingRate) {
		this.billingRate = billingRate;
	}


	/**
	 * @return the workersRequired
	 */
	public int getWorkersRequired() {
		return workersRequired;
	}


	/**
	 * @param workersRequired the workersRequired to set
	 */
	public void setWorkersRequired(int workersRequired) {
		this.workersRequired = workersRequired;
	}


	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}


	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}


	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}


	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
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
	 * @return the jobId
	 */
	public int getJobId() {
		return jobId;
	}


	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	
}
