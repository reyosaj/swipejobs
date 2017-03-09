/**
 * 
 */
package com.swipejobs.matchengine.wsm;

/**
 * Enum representing the Weight assigned to each <code>Criteria</code>. <br>
 * <br>
 * Each <code>Criteria</code> can be assigned a weight value between 0.0 and 1.0,
 * provided the total weight for all <code>Criteria</code> remains at 1.0.
 * Larger the value in the range, the more priority in the ranking process.
 * 
 * @see <code>WeightedSumAggregator</code>
 * 
 * @author reyo
 *
 */
public enum Weight {

	LICENSE(0.20F), CERTIFICATE(0.50F), LOCATION(0.30F);

	private final float rank;

	Weight(float rank) {
		this.rank = rank;
	}

	/**
	 * weight
	 * 
	 * @return
	 */
	public Float getRank() {
		return this.rank;
	}

}
