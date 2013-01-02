import input.ImportProvider;

import java.util.concurrent.LinkedBlockingQueue;
import common.Collections;
import process.DataProcessProvider;
import domainobjects.BirdAlertImpl;
import domainobjects.IBirdSighting;

public class SightingsMonitor {

	/**
	 * The wrapper class from which the BirdAlerter runs
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		Collections.sightings = new LinkedBlockingQueue<IBirdSighting>();
		Collections.sightingAlerts = new LinkedBlockingQueue<BirdAlertImpl>();

		// Start the data import threads via the ImportProvider
		ImportProvider.getInstance().start();
		
		// Start the data process threads via the DataProcessProvider
		DataProcessProvider.getInstance().start(4);
	}
}
