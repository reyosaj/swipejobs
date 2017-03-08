/**
 * 
 */
package com.swipejobs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swipejobs.matchengine.MatchingEngine;
import com.swipejobs.services.rest.response.Job;
import com.swipejobs.services.rest.response.Worker;

/**
 * This service uses a <code>MatchingEngine</code> to find the best matching <code>Job</code>s.  
 * 
 * @author reyo
 *
 */
@Component
public class MatchingService {
	
	// Upper limit for the number of matches returned to user
	private static final int MAX_MATCHES = 3;
	
	/**
	 * The matching engine
	 */
	@Autowired
	private MatchingEngine matchEngine;

	/**
	 * Uses a <code>MatchingEngine</code> to find the best matching
	 * <code>Job</code>s for a given <code>Worker</code>. The result 
	 * size is capped to the MAX_MATCHES property.
	 * 
	 * @param worker
	 * @param jobs
	 *            list of all available Jobs.
	 * @return <code>List</code> of best matching <code>Job</code>
	 */
	public List<Job> findBestMatches(Worker worker, List<Job> jobs) {
		
		return matchEngine.bestNMatchJobs(worker, jobs, MAX_MATCHES);
	}

}
