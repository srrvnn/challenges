package com.adara.interview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;

import com.adara.interview.PlacementAdMapping;

/**
 * @author srrvnn
 *
 */
public class Stub_Database {
	
	private Map<Integer, ArrayList<PlacementAdMapping>> randomData;
	private Map<Integer, Integer> randomDataTotalWeight;
	
	private boolean config;
	
	private int numberOfAds;
	private int[] idOfAds;
	private int[] weightOfAds;
	
	public Stub_Database() {
		
		randomData = new HashMap<Integer, ArrayList<PlacementAdMapping>>();
		randomDataTotalWeight = new HashMap<Integer, Integer>();
	}
	
	public void config(String configuration){
		
		// specific configuration that return ads function will follow
		
		config = true;
		
		switch(configuration) {
		
		// no configuration - normal ads
		
		case "NormalAds":
			config = false;
			break;
			
		// only one ad
			
		case "OneAd": 
			numberOfAds = 1;
			weightOfAds = new int[]{ 5 };
			idOfAds = new int[]{ 1 };
			break;
			
		// two ads with varying weights
			
		case "HeavilyWeightAds":
			numberOfAds = 3;
			weightOfAds = new int[]{ 99, 1, 50 };
			idOfAds = new int[]{ 1, 2, 3 };
			break;
			
		// setting number of ads
			
		case "NumberAds":
			numberOfAds = 10;
			weightOfAds = new int[]{ 1, 1, 3, 2, 5, 4, 6, 1, 7, 2 };
			idOfAds = new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			break;
			
		// no ads
			
		case "EmptyAds":
			numberOfAds = 0;
			break;
			
		// ads with same weight
			
		case "SameWeightAds":
			numberOfAds = 2;
			weightOfAds = new int[]{ 1, 1};
			idOfAds = new int[]{ 1, 2 };
			break;
			
		// ads with zero weight
			
		case "AdWithZero":
			numberOfAds = 2;
			weightOfAds = new int[]{ 0, 1};
			idOfAds = new int[]{ 1, 2 };
			break;		
		}
	}
	
	/**
	 * Return eligible ads randomly and according to configuration as needed for test 
	 * 
	 * @param placementId
	 * 
	 * @return list for eligible ads for each placementId
	 */
	public ArrayList<PlacementAdMapping> getAdsForPlacement( int placementId ) {
		
		Integer integerPlacementId = new Integer(placementId);
		
		if (randomData.containsKey(integerPlacementId)) {
			
			// feed existing data if it was already created		
			
			return randomData.get(integerPlacementId);			
			
		} else {
		
			// create new data if necessary			
		
			ArrayList<PlacementAdMapping> randomEligibleAds = new ArrayList<PlacementAdMapping>();

			Random r = new Random();
			
			// randomize number of ads if not already configured
			
			int theNumberOfAds = config ? numberOfAds : r.nextInt(100);			
			
			int totalWeight = 0;
			
			// for each ad

			for(int i = 0; i < theNumberOfAds; i++) {
				
				// randomize id and weight if not already configured

				int randomAdId = config ? idOfAds[i] : r.nextInt(Integer.MAX_VALUE);
				int randomWeight = config ? weightOfAds[i] : r.nextInt(20);

				// create a new PlacementAdMapping with the random info

				PlacementAdMapping newPlacementAdMapping
	         		= new PlacementAdMapping(placementId, randomAdId, randomWeight);

				// add the PlacementAdMapping to the Collection.

				randomEligibleAds.add(newPlacementAdMapping);
				
				totalWeight += randomWeight;
			}
			
			// store information for persistence 
			
			randomData.put(integerPlacementId, randomEligibleAds);
			randomDataTotalWeight.put(integerPlacementId, totalWeight);
			
			// return collection

			return randomEligibleAds;
	     
		}
		
	}
	
	public int getTotalWeight( int placementId ) { 
		
		return randomDataTotalWeight.get(new Integer(placementId)).intValue();
	}

	/**
	 * Update the weight of a chosen ad.
	 * 
	 * @param placementAdMapping 
	 */
	public void updateWeight( PlacementAdMapping placementAdMapping ) {
		
		// this process will be more efficient from a database.
		// since this is only a stub, efficiency isn't considered critical
		
		// get placement and ad id 
		
		Integer placementId = placementAdMapping.getPlacementId();
		Integer adId = placementAdMapping.getCreativeId();
		
		// get all eligible ads 
		
		ArrayList<PlacementAdMapping> eligibleAds = randomData.get(placementId);		
		Iterator<PlacementAdMapping> i = eligibleAds.iterator();
		
		// find the one with the ad id 
		
		while(i.hasNext()) {
			
			PlacementAdMapping temp = i.next();
			
			if (adId == temp.getCreativeId()) {
				
				temp.setWeight(temp.getWeight() - 1);				
				break;
			}
		}
		
		// update total weight
		
		Integer totalWeight = randomDataTotalWeight.get(placementId);
		randomDataTotalWeight.put(placementId, totalWeight - 1);
	}
	
	/**
	 * Update weights of all ads for a placement after a single run  
	 * 
	 * @param placementId
	 */
	public void resetWeight( int placementId ) {
		
		int totalWeight = 0;
		
		ArrayList<PlacementAdMapping> eligibleAds = randomData.get(placementId);
		
		// find all ads for a placement
		
		Iterator<PlacementAdMapping> i = eligibleAds.iterator();
		
		// update their weight from their value. 
		
		while(i.hasNext()) {
			
			PlacementAdMapping temp = i.next();
			
			temp.setWeight(temp.getValue());
			
			totalWeight += temp.getWeight();
		}
		
		// update total weight
		
		randomDataTotalWeight.put(new Integer(placementId), totalWeight);	

	}
		
	
}