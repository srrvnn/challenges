package com.adara.interview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;


public class AdCallProcessor {
	
	private Stub_Database stub_Database;  	
    
    /**
     * Set the stub going to be used by the AdCallProcessor
     * 
     * @param newStub customized stub usually sent from driver 
     * @author srrvnn
     */
    public void setStub(Stub_Database newStub) {
    	
    	stub_Database = newStub;
    }

    //hypothetical high-level ad call logic...
    public int handleAdCall( int placementId ){

        //which ads are mapped to this placement
        Collection<PlacementAdMapping> eligibleAds = getAdsForPlacement( placementId );

        //choose one of the ads based on relative weight
        PlacementAdMapping chosenAd = chooseAd( eligibleAds, stub_Database.getTotalWeight( placementId ) );

    	// we're sampling without replacement therefore updating weight of the chosen ad.
        if (chosenAd != null) {
        	
        	stub_Database.updateWeight(chosenAd);        	
        	
        	if ( stub_Database.getTotalWeight( placementId ) < 1 ){    		
        		stub_Database.resetWeight( placementId );
        	}
        	
        	// return the ad 
        	return chosenAd.getCreativeId();
        	
        } else {

        	// chosenAd is null. return zero for further processing
        	
        	return 0;
        }
    }

    /**
     * Choose one of the ads ( based on relative weight )
     *
     * @param ads list of ads to choose from
     * @param totalWeight sum of the list of all ads
     * @return the chosen ad
     * @author srrvnn
     */
    public PlacementAdMapping chooseAd( Collection<PlacementAdMapping> ads, int totalWeightOfAds ){
    	
    	PlacementAdMapping chosenAd = null;
    	
    	// return null if there are no eligible ads
    	
    	if ( ads == null || ads.size() < 1 ){
    		return chosenAd;
    	}    		
    	
    	int progressiveWeight = 0;
    	PlacementAdMapping tempPlacementAdMapping = null;
    	
    	Iterator<PlacementAdMapping> i = ads.iterator();    	
    	
    	int randomWeight = new Random().nextInt(totalWeightOfAds);
    	
    	while(i.hasNext()) {
    		
    		tempPlacementAdMapping = i.next();
    		progressiveWeight += tempPlacementAdMapping.getWeight();    		
    		
    		if(randomWeight < progressiveWeight) {
    			
    			chosenAd = tempPlacementAdMapping;    	
    			break;
    		} 
    	}    
    	
        return chosenAd;
    }

    /**
     * returns the ads mapped to a given placement
     *
     * @param placementId
     * @return collection of PlacementAd objects mapped to placement
     */
    protected Collection<PlacementAdMapping> getAdsForPlacement( int placementId ){
        //
        // In a "real" implementation, this info would probably be read from a DB...
        //
        // For this exercise, feel free to override this for the purpose of testing
        //

        ArrayList<PlacementAdMapping> randomEligibleAds 
        	= stub_Database.getAdsForPlacement( placementId );

        return randomEligibleAds;
    }

}
