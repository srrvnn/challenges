public class Stub_AdCallProcessor {

	/**
     * stub for returning the ads mapped to a given placement
     *
     * @return collection of random PlacementAd
     */
    public Collection<PlacementAdMapping> getAdsForPlacement() {

        ArrayList<PlacementAdMapping> randomEligibleAds = new ArrayList<PlacementAdMapping>();

        Random randomGenerator = new Random();

        // Create a random placementId

        int thePlacementId = randomGenerator.nextInt(Integer.MAX_VALUE);

        // Create a random number of ads

        int theNumberOfAds = randomGenerator.nextInt(Integer.MAX_VALUE);

        // for n times

        for(int i = 0; i < randomNumberOfAds; i++) {


            int randomAdId = randomGenerator.nextInt(Integer.MAX_VALUE);
            int randomWeight = randomGenerator.nextInt(Integer.MAX_VALUE);

            // create a new PlacementAdMapping with the random info

            PlacementAdMapping newPlacementAdMapping
                = new PlacementAdMapping(thePlacementId, randomAdId, randomWeight);

            // add the PlacementAdMapping to the Collection.

            randomEligibleAds.add(newPlacementAdMapping);
        }

        // return the Collection

        return randomEligibleAds;
    }
}