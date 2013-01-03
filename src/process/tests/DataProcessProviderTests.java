package process.tests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;
import process.DataProcessProvider;
import common.Collections;
import domainobjects.BirdSightingImpl;

public class DataProcessProviderTests {
	
	Integer stoppedThreads = 0;
	Integer threadCount = 5;

	@Test
	public void testStartProcessing() {

		DataProcessProvider.getInstance().start(threadCount);
		Assert.assertEquals(threadCount, DataProcessProvider.getInstance().runningThreads());
		
		loadSightings();
		
		//DataProcessProvider.getInstance().stop();
		//Assert.assertEquals(stoppedThreads, DataProcessProvider.getInstance().runningThreads());
	}
	
	@Test
	public void testStopProcessing() {
		
		DataProcessProvider.getInstance().start(threadCount);
		Assert.assertEquals(threadCount, DataProcessProvider.getInstance().runningThreads());
		DataProcessProvider.getInstance().stop();
		
		while(DataProcessProvider.getInstance().running){
			
		}
		System.out.print(DataProcessProvider.getInstance().runningThreads());
		//Assert.assertEquals(stoppedThreads, );
	}	
	
	private static void loadSightings(){
		final List<String> birdNames = new ArrayList<String>();
		birdNames.add("Sparrow");
		birdNames.add("Jay");
		birdNames.add("Blackbird");
		birdNames.add("Robin");
		birdNames.add("Crow");
		birdNames.add("Red Kite");
		birdNames.add("Chaffinch");
		birdNames.add("Goldcrest");
		birdNames.add("Mallard");
		birdNames.add("Goldfinch");
		
		for (int i = 0; i < 10; i++) {
			Collections.sightings.add(new BirdSightingImpl(UUID.randomUUID(), new Date(), birdNames.get(new Random().nextInt(9))));
		}
	}

}
