/**
 * 
 */
package com.swipejobs.matchengine.wsm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swipejobs.matchengine.Criteria;
import com.swipejobs.matchengine.geo.DistanceCalculator;
import com.swipejobs.services.rest.response.Job;
import com.swipejobs.services.rest.response.SearchAddress;
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
	
	public static final Logger log = LoggerFactory.getLogger(Criterions.class);
	
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
		public float compute(Worker w, Job j) {
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
		public float compute(Worker w, Job j) {
			List<String> workerCerts = w.getCertificates();
			int required = j.getRequiredCertificates().size();
			int matchedCount = (int) j.getRequiredCertificates().stream()
					.filter(p -> workerCerts.contains(p))
					.count();
			return (matchedCount > 0 && required > 0) ? ((float)matchedCount / required) : 0.0f;
		}

		@Override
		public String getType() {
			return Weight.CERTIFICATE.name();
		}
	};
	
	/**
	 * Implementation Criteria for Geo Location Criteria. <br>
	 * <br>
	 * This Criteria finds the location score based on the <code>Worker</code>
	 * preference and the <code>Job</code> location <br>
	 * <br>
	 * The <i>compute</i> method will normalize the distance to a score between
	 * 0 and 1, where 0 stands for a bad match and 1 stands for a good match.
	 * <br>
	 */
	public static final Criteria<Worker, Job> Location = new Criteria<Worker, Job>() {

		@Override
		public float compute(Worker w, Job j) {
			SearchAddress wAdd = w.getJobSearchAddress();
			com.swipejobs.services.rest.response.Location jLoc = j.getLocation();
			if(wAdd == null || jLoc == null) {
				// location preference is not available. no need to proceed 
				return 1;
			}
			
			
			double mMaxDistance = (double)wAdd.getMaxJobDistance();
			double distance = 0.0;
			try {
				double lat1 = Double.valueOf(wAdd.getLatitude());
				double lon1 = Double.valueOf(wAdd.getLongitude());
				
				double lat2 = Double.valueOf(jLoc.getLatitude());
				double lon2 = Double.valueOf(jLoc.getLongitude());
				String unit = "K";
				if(wAdd.getUnit() != null) {
					unit = wAdd.getUnit().equalsIgnoreCase("KM") ? "K" : "M"; 
				}
				
				//calculate to distance between two points.
				distance = DistanceCalculator.distance(lat1, lon1, lat2, lon2, unit);
			} catch (NumberFormatException e) {
				log.error(String.format("error calculating distance for %s", j.getJobId()));
				return 0;
			}
			
			// the distance is normalized between 0 and 1
			float diff = (float) (mMaxDistance - distance);
			return Float.min(Float.max(diff, 0.0f), 1.0f);
		}

		@Override
		public String getType() {
			return Weight.LOCATION.name();
		}
	};
}
