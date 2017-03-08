/**
 * 
 */
package com.swipejobs.matchengine;

/**
 * Represents a single Criteria to be tested for a given pair of elements. <br>
 * <br>
 * 
 * @see ScoreAggregator
 * 
 * @author reyo
 * @param <E,
 *            R>
 *
 */
public interface Criteria<E, T> {

	/**
	 * For the given pair of elements, this method should compute the value for
	 * the Criteria. Score returned by this method will be used by
	 * <code>ScoreAggregator</code> algorithm to decide the best match.
	 * 
	 * @param e
	 *            Generic element E
	 * @param t
	 *            Generic element T
	 * @return range of the float value may vary based the algorithm used.
	 */
	public float compute(E e, T t);

	/**
	 * @return the type/name used to identify the Criteria. All implementation
	 *         of this Interface should return a unique value
	 */
	public String getType();
}
