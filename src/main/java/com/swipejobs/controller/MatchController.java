/**
 * 
 */
package com.swipejobs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.swipejobs.service.MatchingService;
import com.swipejobs.services.rest.ClientService;
import com.swipejobs.services.rest.RestResult;
import com.swipejobs.services.rest.response.Job;
import com.swipejobs.services.rest.response.Worker;

/**
 * Rest controller for mapping client requests
 * @author reyo
 *
 */
@RestController
public class MatchController {
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	MatchingService matchingService;
	
	@RequestMapping(path="/matches/{workerId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<List<Job>> findMatches(@PathVariable(name="workerId", required=true) Integer workerId) {
		
		RestResult<Worker> workerResult = clientService.getWorker(workerId);
		
		if(workerResult.isError()) {
			// something went wrong. cannot progress! 
			return ResponseEntity.status(workerResult.getHttpStatus()).build();
		}
		
		RestResult<List<Job>> jobsResult = clientService.getJobs();
		
		if(jobsResult.isError()) {
			// no Jobs, so return
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		//find matching jobs
		List<Job> matchingJobs = matchingService.findBestMatches(workerResult.getValue(), jobsResult.getValue());
		
		if(matchingJobs.isEmpty()) {
			//no matching jobs found.
			return ResponseEntity.notFound().build();
		}
		
		
		return ResponseEntity.ok(matchingJobs);
	}

}
