/**
 * 
 */
package com.swipejobs.services.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.swipejobs.services.rest.response.Job;
import com.swipejobs.services.rest.response.Worker;

/**
 * Service class for retrieving swipejobs Workers and Jobs
 * @author reyo
 *
 */
@Component
public class ClientService {

	private static final String WORKER_URL = "http://test.swipejobs.com/api/workers";
	private static final String JOBS_URL = "http://test.swipejobs.com/api/jobs";
	
	private static final Logger log = LoggerFactory.getLogger(ClientService.class);

	@Autowired
	RestTemplate restTemplate;

	
	/**
	 * find the <code>Worker</code> with the requested userId .
	 * 
	 * @param workerId
	 * @return <code>RestResult<Worker></code> will either have a
	 *         <code>Worker</code> matching the workerId, OR the failure reason.
	 */
	public RestResult<Worker> getWorker(final Integer workerId) {
		
		RestResult<Worker> result = null;
		try {
			ResponseEntity<Worker[]> response = restTemplate.getForEntity(WORKER_URL, Worker[].class);
			if (response.getStatusCode() == HttpStatus.OK) {
				//get the first element matching the filter
				Optional<Worker> o = Arrays.asList(response.getBody())
						.stream()
						.filter(p -> workerId.equals(p.getUserId()))
						.findFirst();
				
				if (o.isPresent()) {
					result = new RestResult<>(o.get());
				} else {
					result = new RestResult<>(HttpStatus.BAD_REQUEST, workerId + " NOT Found.");
				}
			} else {
				result = new RestResult<>(response.getStatusCode(), "Unknow Error");
			}
		} catch (RestClientException e) {
			log.error(String.format("Err in getworker(%d) - %s", workerId, e.getMessage()));
			result = getResultFromException(result, e);
		}

		return result;
	}

	/**
	 * Find all available <code>Job</code>
	 * @return <code>RestResult<List<Job>></code> will either have a List of available 
	 *         <code>Job</code> OR the reason for the failure to get the Jobs.
	 */
	public RestResult<List<Job>> getJobs() {
		RestResult<List<Job>> result = null;
		try {
			ResponseEntity<Job[]> response = restTemplate.getForEntity(JOBS_URL, Job[].class);
			
			if (response.getStatusCode() == HttpStatus.OK) {
				List<Job> jobs =  Arrays.asList(response.getBody());
				if(jobs.isEmpty()) {
					result = new RestResult<>(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
				} else {
					result = new RestResult<>(jobs);
				}
			} else {
				result = new RestResult<>(response.getStatusCode(), "Unknow Error");
			}
		} catch (RestClientException e) {
			log.error(String.format("Err in getJobs() - %s", e.getMessage()));
			result = getResultFromException(result, e);
		}
		
		return result;
	}
	
	/**
	 * A Utility to convert <code>RestClientException</code> to <code>RestResult</code>
	 * @param result
	 * @param e
	 * @return RestResult
	 */
	private <E> RestResult<E> getResultFromException(RestResult<E> result, RestClientException e) {
		if(e instanceof RestClientResponseException) {
			RestClientResponseException rc = (RestClientResponseException) e;
			result = new RestResult<>(HttpStatus.INTERNAL_SERVER_ERROR, rc.getRawStatusCode() + 
					" " + rc.getMessage());
		} else {
			result = new RestResult<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		return result;
	}

}
