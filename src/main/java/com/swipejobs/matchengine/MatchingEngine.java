/**
 * 
 */
package com.swipejobs.matchengine;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swipejobs.services.rest.response.Job;
import com.swipejobs.services.rest.response.Worker;

/**
 * MatchingEngine finds the best available matching Jobs.<br>
 * <br>
 * MatchingEngine is agnostic to the underlying algorithm used to find the best
 * match.This class only uses the scores provided by the matching algorithm to
 * compute the final result. Use <code>ScoreAggregator</code> to provide your
 * own matching algorithms
 * 
 * @see ScoreAggregator
 * @author reyo
 *
 */
@Component
public class MatchingEngine {

	/**
	 * A configurable matching algorithm.
	 */
	@Autowired
	private ScoreAggregator<Worker, Job> scoreAggregator;

	// Data holder mainly for sorting the results based on score.
	private static class MatchPair {
		private double score;
		private Job job;

		public MatchPair(double score, Job job) {
			this.score = score;
			this.job = job;
		}
	}

	/**
	 * Given a Worker and the List of available Jobs, this method will return
	 * the best matching Jobs. <br>
	 * <br>
	 * The number of Jobs returned will be capped to the upper bound specified
	 * by the <i>limit</i> parameter.
	 * 
	 * @param wrk
	 * @param jobs
	 * @param limit
	 * @return
	 */
	public List<Job> bestNMatchJobs(Worker wrk, List<Job> jobs, Integer limit) {

		return jobs.stream()
				// find the score for each pair of worker and job
				.map(job -> { 
					double score = scoreAggregator.getScore(wrk, job);
					return new MatchPair(score, job);
				})
				//sort the result on descending score; higher the first
				.sorted((m1, m2) -> Double.compare(m1.score, m2.score))
				// limit the size
				.limit(limit).map(m -> m.job)
				// collect to a list
				.collect(Collectors.toList());
	}

}
