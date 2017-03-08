package com.swipejobs.services.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Address of Worker
 * @author reyo
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchAddress {
	private String unit;
	private int maxJobDistance;
	private String longitude;
	private String latitude;
	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @return the maxJobDistance
	 */
	public int getMaxJobDistance() {
		return maxJobDistance;
	}
	/**
	 * @param maxJobDistance the maxJobDistance to set
	 */
	public void setMaxJobDistance(int maxJobDistance) {
		this.maxJobDistance = maxJobDistance;
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
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
