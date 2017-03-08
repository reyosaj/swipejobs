/**
 * 
 */
package com.swipejobs.matchengine.wsm;

/**
 * Enum representing the Weight assigned to each <code>Criteria</code>. 
 * @see <code>WeightedSumAggregator</code>
 * 
 * @author reyo
 *
 */
public enum Weight {
	
	LICENSE(0.50F),
	CERTIFICATE(0.50F),
	LOCATION(0.0F);

	private final float rank;
	
	Weight(float rank) {
		this.rank = rank;
	}
	
	/**
	 * weight
	 * @return
	 */
	public Float getRank() {
		return this.rank;
	}

}
