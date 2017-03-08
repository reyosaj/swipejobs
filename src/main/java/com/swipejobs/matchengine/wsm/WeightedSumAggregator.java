/**
 * 
 */
package com.swipejobs.matchengine.wsm;

import java.util.List;

import com.swipejobs.matchengine.Criteria;
import com.swipejobs.matchengine.ScoreAggregator;


/**
 * A simple implementation of <code>ScoreAggregator</code> using <b>Weighted Sum
 * Model(WSM)</b>.<br>
 * <br>
 * Given <i>m</i> Criteria <i>(C)</i> and Weight <i>(w)</i> for each Criteria,
 * for each pair of <i>(Job, Worker)</i> MSD calculates the sum of the product
 * of <i>Criteria(Job, Worker)</i> and its corresponding weight.
 * 
 * <pre>
 * weightedScore = C1(Job, Worker) * w(C1) +  C2(Job, Worker) * w(C2) + ... + Cm(Job, Worker) * w(Cm)
 * </pre>
 * 
 * @author reyo
 * @param <E>
 *
 */
public class WeightedSumAggregator<Worker, Job> implements ScoreAggregator<Worker, Job> {
	
	/**
	 * List of  configured <code>Cirteria</code> injected to the bean.
	 */
	private List<Criteria<Worker, Job>> criterions;
	
	/**
	 * @param criterions
	 */
	public WeightedSumAggregator(List<Criteria<Worker, Job>> criterions) {
		this.criterions = criterions;
	}

	/* (non-Javadoc)
	 * @see com.swipejobs.matchengine.ScoreAggregator#getScore(java.lang.Object, java.lang.Object)
	 */
	@Override
	public double getScore(Worker e, Job j) {
		// find the sum of all weighted criteria.
		return criterions.stream().mapToDouble(c -> {
			float weight = Weight.valueOf(c.getType()).getRank();
			return c.compute(e, j) * weight;
		}).sum();
	}

}
