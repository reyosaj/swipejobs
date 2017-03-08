package com.swipejobs.matchengine;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
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

import com.swipejobs.services.rest.response.Job;
import com.swipejobs.services.rest.response.Worker;

@RunWith(MockitoJUnitRunner.class)
public class MatchingEngineTest {

	@Mock
	private ScoreAggregator<Worker, Job> scoreAggregator;

	@Spy
	@InjectMocks
	private MatchingEngine matchEngine;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Job j1 = new Job();
		j1.setJobId(1);
		Job j2 = new Job();
		j2.setJobId(2);
		Job j3 = new Job();
		j3.setJobId(3);
		
		when(scoreAggregator.getScore(Mockito.any(), Matchers.eq(j1))).thenReturn(0.5);
		when(scoreAggregator.getScore(Mockito.any(), Matchers.eq(j2))).thenReturn(0.6);
		when(scoreAggregator.getScore(Mockito.any(), Matchers.eq(j3))).thenReturn(0.55);

		List<Job> jobs = matchEngine.bestNMatchJobs(new Worker(), Arrays.asList(j1, j2, j3), 2);

		assertTrue(jobs.size() == 2);
		assertTrue(jobs.get(0).getJobId() == 1);
		assertTrue(jobs.get(1).getJobId() == 3);
		
		verify(scoreAggregator, times(3)).getScore(any(), any());
	}

}
