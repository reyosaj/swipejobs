/**
 * 
 */
package com.swipejobs.matchengine.wsm;

import java.util.List;

import com.swipejobs.matchengine.Criteria;
import com.swipejobs.services.rest.response.Job;
import com.swipejobs.services.rest.response.Worker;

/**
 * A utility holding all the <code>Criteria</code> configured for
 * <code>WeightedSumAggregator</code>
 * 
 * @see WeightedSumAggregator
 * 
 * @author reyo
 *
 */
public class Criterions {
	
	//singleton
	private Criterions() {
	}
	
	/**
	 * Implementation for Driving License Criteria.<br>
	 * <br>
	 * This Criteria is applied if a Job demands a driving license.
	 * <i>compute</i> method will return 1 for a match and 0 otherwise.
	 */
	public static final Criteria<Worker, Job> DrivingLicence = new Criteria<Worker, Job>() {

		@Override
		public int compute(Worker w, Job j) {
			if(j.isDriverLicenseRequired()) {
				return w.isHasDriversLicense() ? 1 : 0;
			}
			return 1;
		}

		@Override
		public String getType() {
			return Weight.LICENSE.name();
		}
	};
	
	/**
	 * Implementation for Certificates Criteria.<br>
	 * <br>
	 * This Criteria finds if the <code>Worker</code> has all the mandatory
	 * certification required for picking a <code>Job</code>. <br>
	 * <br>
	 * The <i>compute</i> method will return a value between 0 and 1 based on the
	 * number of mandatory certifications available with the Worker to pick a
	 * Job. <br>
	 * <i>0 - if no match, 1 - for all match</i>
	 */
	public static final Criteria<Worker, Job> Certificates = new Criteria<Worker, Job>() {

		@Override
		public int compute(Worker w, Job j) {
			List<String> workerCerts = w.getCertificates();
			int required = j.getRequiredCertificates().size();
			int matchedCount = (int) j.getRequiredCertificates().stream()
					.filter(p -> workerCerts.contains(p))
					.count();
			return (matchedCount > 0 && required > 0) ? (matchedCount / required) : 0;
		}

		@Override
		public String getType() {
			return Weight.CERTIFICATE.name();
		}
	};
	
	/**
	 * Implementation Criteria for Geo Location Criteria.
	 */
	public static final Criteria<Worker, Job> Location = new Criteria<Worker, Job>() {

		@Override
		public int compute(Worker e, Job t) {
			// TODO add location matching algo here.
			return 0;
		}

		@Override
		public String getType() {
			return Weight.LOCATION.name();
		}
	};
}
