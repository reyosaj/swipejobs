/**
 * 
 */
package com.swipejobs.matchengine.wsm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.swipejobs.matchengine.Criteria;
import com.swipejobs.matchengine.ScoreAggregator;
import com.swipejobs.services.rest.response.Job;
import com.swipejobs.services.rest.response.Worker;

/**
 * @author reyo
 *
 */
public class WeightedSumAggregatorTest {
	
	private ScoreAggregator<Worker, Job> aggregator;
	private List<Criteria<Worker, Job>> criterions;
	private Worker worker;
	private Job job;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		criterions = new ArrayList<>();
		criterions.add(Criterions.DrivingLicence);
		criterions.add(Criterions.Certificates);
		
		aggregator = new WeightedSumAggregator<>(criterions);
		
		worker = new Worker();
		worker.setCertificates(Arrays
				.asList(new String[] { "Outstanding Innovator", "The Behind the Scenes Wonder", "The Risk Taker" }));
		worker.setHasDriversLicense(false);

		job = new Job();
		job.setDriverLicenseRequired(true);
		job.setRequiredCertificates(Arrays.asList(new String[] { "Outstanding Innovator", "The Risk Taker" }));
	}

	@Test
	public void testPartScore() {
		double score = aggregator.getScore(worker, job);
		assertTrue(score == 0.5);
	}
	
	@Test
	public void testFullScore() {
		worker.setHasDriversLicense(true);
		double score = aggregator.getScore(worker, job);
		assertTrue(score == 1.0);
	}

}
