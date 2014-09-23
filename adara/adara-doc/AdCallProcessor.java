package com.adara;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Random;
import java.lang.Integer;

public class AdCallProcessor {

    private Stub_AdCallProcessor stub_AdCallProcessor;

    public AdCallProcessor() {

        stub_AdCallProcessor =  new Stub_AdCallProcessor();
    }

    //hypothetical high-level ad call logic...
    public void handleAdCall( int placementId ){

        //which ads are mapped to this placement
        Collection<PlacementAdMapping> eligibleAds = getAdsForPlacement( placementId );

        //choose one of the ads based on relative weight
        PlacementAdMapping chosenAd = chooseAd( eligibleAds );

        //... render the ad
        System.out.println( chosenAd );
    }

    /**
     * Choose one of the ads ( based on relative weight )
     *
     * @param ads list of ads to choose from
     * @return the chosen ad
     */
    public PlacementAdMapping chooseAd( Collection<PlacementAdMapping> ads ){



        return null;
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

        ArrayList<PlacementAdMapping> randomEligibleAds = stub_AdCallProcessor.getAdsForPlacement;

        return randomEligibleAds;
    }





}