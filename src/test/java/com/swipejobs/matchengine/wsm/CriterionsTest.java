/**
 * 
 */
package com.swipejobs.matchengine.wsm;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.swipejobs.services.rest.response.Job;
import com.swipejobs.services.rest.response.Location;
import com.swipejobs.services.rest.response.SearchAddress;
import com.swipejobs.services.rest.response.Worker;

/**
 * @author reyo
 *
 */
public class CriterionsTest {

	private Worker worker;
	private Job job;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		worker = new Worker();
		worker.setCertificates(Arrays
				.asList(new String[] { "Outstanding Innovator", "The Behind the Scenes Wonder", "The Risk Taker" }));
		worker.setHasDriversLicense(false);

		job = new Job();
		job.setDriverLicenseRequired(true);
		job.setRequiredCertificates(Arrays.asList(new String[] { "Outstanding Innovator", "The Risk Taker" }));
	}

	@Test
	public void testDriversLicenseRequired() {
		float value = Criterions.DrivingLicence.compute(worker, job);
		assertTrue(value == 0);
	}
	
	@Test
	public void testDriversLicenseNotRequired() {
		job.setDriverLicenseRequired(false);
		assertTrue(Criterions.DrivingLicence.compute(worker, job) == 1);
	}
	
	@Test
	public void testCertificatesRequired() {
		assertTrue(Criterions.Certificates.compute(worker, job) == 1);
	}
	
	@Test
	public void testCertificatesNotAllInRequired() {
		worker.setCertificates(Arrays
				.asList(new String[] {"The Risk Taker" }));
		float value = Criterions.Certificates.compute(worker, job);
		assertTrue(value == 0.5);
	}
	
	@Test
	public void testCertificatesNothingMatchRequired() {
		worker.setCertificates(Arrays
				.asList(new String[] {"the only cert" }));
		float value = Criterions.Certificates.compute(worker, job);
		assertTrue(value == 0.0);
	}
	
	@Test
	public void testLocationWithinMaxDistance() {
		//distance between the points is 18km
		SearchAddress add = new SearchAddress();
		add.setLatitude("49.93359");
		add.setLongitude("13.864602");
		add.setMaxJobDistance(30);
		add.setUnit("km");
		worker.setJobSearchAddress(add);
		
		Location loc = new Location();
		loc.setLatitude("49.782281");
		loc.setLongitude("13.971284");
		job.setLocation(loc);
		
		float value =  Criterions.Location.compute(worker, job);
		assertTrue(value == 1.0);
	}
	
	@Test
	public void testLocationOutsideMaxDistance() {
		//distance between the points is more than 200km
		SearchAddress add = new SearchAddress();
		add.setLatitude("32.9697");
		add.setLongitude("-96.80322");
		add.setMaxJobDistance(30);
		add.setUnit("km");
		worker.setJobSearchAddress(add);
		
		Location loc = new Location();
		loc.setLatitude("29.46786");
		loc.setLongitude("-98.53506");
		job.setLocation(loc);
		
		float value =  Criterions.Location.compute(worker, job);
		assertTrue(value == 0);
	}

}
