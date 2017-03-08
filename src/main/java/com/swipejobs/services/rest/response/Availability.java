/**
 * 
 */
package com.swipejobs.services.rest.response;

/**
 * Availability of the Worker
 * @author reyo
 *
 */
public class Availability {
	private String title;
	private int dayIndex;
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the dayIndex
	 */
	public int getDayIndex() {
		return dayIndex;
	}
	/**
	 * @param dayIndex the dayIndex to set
	 */
	public void setDayIndex(int dayIndex) {
		this.dayIndex = dayIndex;
	}
}
