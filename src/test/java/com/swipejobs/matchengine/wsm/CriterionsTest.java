/**
 * 
 */
package com.swipejobs.matchengine.wsm;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.swipejobs.services.rest.response.Job;
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

}
