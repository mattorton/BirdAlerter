import input.ImportProvider;
import java.util.concurrent.LinkedBlockingQueue;

import common.Collections;

import process.DataProcessProvider;
import domainobjects.BirdSightingAlert;
import domainobjects.IBirdSighting;

public class SightingsMonitor {

	/**
	 * The wrapper class from which the BirdAlerter runs
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		Collections.sightings = new LinkedBlockingQueue<IBirdSighting>();
		Collections.sightingAlerts = new LinkedBlockingQueue<BirdSightingAlert>();
		//Collections.sightingAlertProcessors = SightingAlertProcessorFactory.getInstance().getAlertProcessors();
		
		// Start the data import threads via the ImportProvider
		ImportProvider.getInstance().startImporters();
		
		// Start the data process threads via the DataProcessProvider
		DataProcessProvider.getInstance().Start(2);
		
		// Load the shared queue with some sample sightings
		//loadSightings();
		

		
		// Load the shared queue with some sample sightings
		//loadSightings();
		
		
		//Thread.sleep(500);
		//System.out.println("slept");
		//Collections.sightings.add(new BirdSightingImpl(UUID.randomUUID(), new Date(), "Vulture"));
	}
	
//	private static void loadSightings(){
//		final List<String> birdNames = new ArrayList<String>();
//		birdNames.add("Sparrow");
//		birdNames.add("Jay");
//		birdNames.add("Blackbird");
//		birdNames.add("Robin");
//		birdNames.add("Crow");
//		birdNames.add("Red Kite");
//		birdNames.add("Chaffinch");
//		birdNames.add("Goldcrest");
//		birdNames.add("Mallard");
//		birdNames.add("Goldfinch");
//		
//		for (int i = 0; i < 10; i++) {
//			Collections.sightings.add(new BirdSightingImpl(UUID.randomUUID(), new Date(), birdNames.get(new Random().nextInt(9))));
//		}
//	}
}
