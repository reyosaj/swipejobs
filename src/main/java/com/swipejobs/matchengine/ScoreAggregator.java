/**
 * 
 */
package com.swipejobs.matchengine;

/**
 * This interface can be used to customize score aggregation for the
 * <code>MatchingEngine</code>. 
 * 
 * @see MatchingEngine
 * 
 * @author reyo
 *
 * @param <E>
 * @param <T>
 */
@FunctionalInterface
public interface ScoreAggregator<E, T> {
	/**
	 * Given two elements and <i>n</i> Criteria to be tested, this method should
	 * find the individual score of each Criteria and compute the aggregate of
	 * all score for the given pair of elements.
	 * 
	 * @param e
	 *            Generic type E
	 * @param t
	 *            Generic type T
	 * @return aggregate score of all criteria
	 */
	public double getScore(E e, T t);
}
