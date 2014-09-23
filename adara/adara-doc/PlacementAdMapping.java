package com.adara;

/**
 * Maps an ad to a placement
 */
public class PlacementAdMapping {
    private int placementId;
    private int adId;
    private int weight;

    public PlacementAdMapping(int placementId, int adId, int weight) {
        this.placementId = placementId;
        this.adId = adId;
        this.weight = weight;
    }

    public int getPlacementId() {
        return placementId;
    }
    public void setPlacementId(int placementId) {
        this.placementId = placementId;
    }
    public int getCreativeId() {
        return adId;
    }
    public void setCreativeId(int adId) {
        this.adId = adId;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String toString() {
        return "PlacementAdMapping [adId=" + adId + ", placementId="
                + placementId + ", weight=" + weight + "]";
    }

}
