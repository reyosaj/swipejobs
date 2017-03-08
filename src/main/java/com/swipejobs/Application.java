/**
 * 
 */
package com.swipejobs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.swipejobs.matchengine.Criteria;
import com.swipejobs.matchengine.ScoreAggregator;
import com.swipejobs.matchengine.wsm.Criterions;
import com.swipejobs.matchengine.wsm.WeightedSumAggregator;
import com.swipejobs.services.rest.response.Job;
import com.swipejobs.services.rest.response.Worker;

/**
 * @author reyo
 *
 */
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		//Start the application
		SpringApplication.run(Application.class, args);
	}
	
	
	
	// ******** Bean definitions Start ********
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public ScoreAggregator<Worker, Job> getScoreAggregator() {
		//configure the list of criteria 
		List<Criteria<Worker, Job>> criterions = new ArrayList<>();
		criterions.add(Criterions.DrivingLicence);
		criterions.add(Criterions.Certificates);
		criterions.add(Criterions.Location);
		
		// algorithm used to find the match.
		return new WeightedSumAggregator<>(criterions);
	}

}
