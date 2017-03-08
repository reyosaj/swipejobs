/**
 * 
 */
package com.swipejobs.services.rest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.swipejobs.services.rest.response.Job;
import com.swipejobs.services.rest.response.Worker;

/**
 * @author reyo
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@Spy
	@InjectMocks
	private ClientService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetWorker() {
		Worker w = new Worker();
		w.setUserId(123);
		ResponseEntity<Worker[]> entity = new ResponseEntity<>(new Worker[] { w }, HttpStatus.OK);
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Matchers.eq(Worker[].class))).thenReturn(entity);

		RestResult<Worker> result = service.getWorker(123);

		assertFalse(result.isError());
	}

	@Test
	public void testGetWorkerStatusNotOk() {
		ResponseEntity<Worker[]> entity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Matchers.eq(Worker[].class)))
			.thenReturn(entity);

		RestResult<Worker> result = service.getWorker(123);

		assertTrue(result.isError());
		assertTrue(result.getHttpStatus() == HttpStatus.NO_CONTENT);
		assertTrue(result.getErrorMessage().equals("Unknow Error"));
	}

	@Test
	public void testGetWorkerTemplateThrowException() {
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Matchers.eq(Worker[].class)))
				.thenThrow(new RestClientException("ding"));

		RestResult<Worker> result = service.getWorker(123);

		assertTrue(result.isError());
		assertTrue(result.getHttpStatus() == HttpStatus.INTERNAL_SERVER_ERROR);
		assertTrue(result.getErrorMessage().equals("ding"));
	}

	@Test
	public void testGetJobs() {
		Job j = new Job();
		ResponseEntity<Job[]> entity = new ResponseEntity<>(new Job[] { j }, HttpStatus.OK);
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Matchers.eq(Job[].class)))
			.thenReturn(entity);

		RestResult<List<Job>> result = service.getJobs();

		assertFalse(result.isError());
	}
	
	@Test
	public void testGetJobsStatusNotOk() {
		ResponseEntity<Job[]> entity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Matchers.eq(Job[].class)))
			.thenReturn(entity);

		RestResult<List<Job>> result = service.getJobs();

		assertTrue(result.isError());
		assertTrue(result.getHttpStatus() == HttpStatus.NO_CONTENT);
	}
	
	@Test
	public void testGetJobsTemplateThrowException() {
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Matchers.eq(Job[].class)))
				.thenThrow(new RestClientException("ding"));

		RestResult<List<Job>> result = service.getJobs();

		assertTrue(result.isError());
		assertTrue(result.getHttpStatus() == HttpStatus.INTERNAL_SERVER_ERROR);
		assertTrue(result.getErrorMessage().equals("ding"));
	}
		

}
