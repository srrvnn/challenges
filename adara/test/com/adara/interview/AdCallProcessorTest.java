package com.adara.interview;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class AdCallProcessorTest {
	
	AdCallProcessor adCallProcessor; 
	Stub_Database stub_Database; 
	
	int placementId;
	
	@Before
	public void setUp() throws Exception{
	
		// instantiate the class to be tested
		
		adCallProcessor = new AdCallProcessor();
		
		// instantiate the stub for database (collaborator with AdCallProcessor)
		
		stub_Database = new Stub_Database(); 
		adCallProcessor.setStub(stub_Database);
		
		// generate a random PlacementId 
		
		placementId = new Random().nextInt();
	}	

	@Test
	public void chooseAd_NormalAds_ReturnAdId() {	
		
		stub_Database.config("NormalAds");
		
		for(int i = 0; i < 100; i++) {
			
			// assert no null is returned when ads are normal
		
			assertNotNull("Choosing ads return null when ads exist", adCallProcessor.handleAdCall(placementId));		
		}
	}
	
	@Test
	public void chooseAd_MoreRequestsThanWeight_ReturnAdId() {	
		
		stub_Database.config("NumberAds");
		
		for(int i = 0; i < 100; i++) {
			
			// assert ads are being returned even after weights have been exhausted
		
			assertNotNull("Choosing ads return null when ads exist", adCallProcessor.handleAdCall(placementId));		
		}
	}
	
	@Test
	public void chooseAd_OneAd_ReturnSame() {
		
		stub_Database.config("OneAd");
		
		// asset the same ad is returned when only one exists
		
		assertEquals("Choosing ads when only ad exists breaks", adCallProcessor.handleAdCall(placementId), 1);
	}
	
	@Test
	public void chooseAd_HeavilyWeightedAds_ReturnRepeatedly() {
		
		stub_Database.config("HeavilyWeightAds");
		
		int sumAdIds = 0;
		
		for(int i = 0; i < 150; i++)			
			sumAdIds += adCallProcessor.handleAdCall( placementId );
		
		// assert ids are returned in the same frequency as weights
		
		assertEquals("Choosing ads follows weights", sumAdIds, 251);
	}
	
	@Test
	public void chooseAd_EmptyAds_ReturnZero() {
		
		stub_Database.config("EmptyAds");
		
		// assert a zero is returned with no ads are found
		
		assertEquals("Choosing ads when none exists doesn't return null", adCallProcessor.handleAdCall(placementId), 0);
	}
	
	@Test
	public void chooseAd_SameWeightAds_ReturnAll() {
		
		stub_Database.config("SameWeightAds");		
		
		int sumAdIds = 0;
		
		for(int i = 0; i < 90; i++)			
			sumAdIds += adCallProcessor.handleAdCall( placementId );
		
		// assert all ads are returned when they are of equal weight
		
		assertEquals("Choosing ads with same weight is not happening uniformly", sumAdIds, 135);
	}
	
	@Test
	public void chooseAd_AdsWithZeroWeights_NeverReturnThem() {
		
		stub_Database.config("AdWithZero");		
		
		int sumAdIds = 0;
		
		for(int i = 0; i < 50; i++)			
			sumAdIds += adCallProcessor.handleAdCall( placementId );
		
		// assert an ad with zero weight is never returned
		
		assertEquals("Choosing ads with a weight zero is happening", sumAdIds & 1, 0);
	}
	
}