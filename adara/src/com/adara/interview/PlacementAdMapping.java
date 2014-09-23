package com.adara.interview;

/**
 * Maps an ad to a placement
 */
public class PlacementAdMapping {
    private int placementId;
    private int adId;
    private int weight;   
    private int value;

    public PlacementAdMapping(int placementId, int adId, int weight) {
        this.placementId = placementId;
        this.adId = adId;        
        this.weight = weight; // changes after every use
        this.value = weight; //  is constant to update weight after a run

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
    public int getValue() {
    	return value;
    }
    
    public String toString() {
        return "PlacementAdMapping [adId=" + adId + ", placementId="
                + placementId + ", weight=" + weight + "]";
    }
}

