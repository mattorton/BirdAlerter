import input.ImportProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

import domainobjects.BirdSightingImpl;

public class SightingsMonitorWrapper {
	
	public static LinkedBlockingQueue<BirdSighting> sightings;
	public static LinkedBlockingQueue<BirdSightingAlert> sightingAlerts;
	private static SightingAlertProcessorImpl[] sightingAlertProcessors;

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {	
		sightings = new LinkedBlockingQueue<BirdSighting>();
		sightingAlerts = new LinkedBlockingQueue<BirdSightingAlert>();
		sightingAlertProcessors = SightingAlertProcessorFactory.getInstance().getAlertProcessors();
		
		// First start the data importers
		ImportProvider.getInstance().startImporters();
		
		// Load the shared queue with some sample sightings
		loadSightings();
		
		// Start each processor in its own thread (SightingAlertProcessorImpl extends Thread)
		for (SightingAlertProcessorImpl processor : sightingAlertProcessors) {
			processor.start();
		}
		
		// Load the shared queue with some sample sightings
		loadSightings();
		
		
		//Thread.sleep(500);
		System.out.println("slept");
		sightings.add(new BirdSightingImpl(UUID.randomUUID(), new Date(), "Vulture"));

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
			sightings.add(new BirdSightingImpl(UUID.randomUUID(), new Date(), birdNames.get(new Random().nextInt(9))));
		}
	}
}
