package process;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import common.Collections;

import domainobjects.*;

public class DataProcessProvider {

	//private SightingAlertProcessorImpl[] sightingsProcessors = new SightingAlertProcessorImpl[]{new RegionSightingAlertProcessor()};
	private List<Thread> runThreads = new ArrayList<Thread>();
	private List<LinkedBlockingQueue<IBirdSighting>> threadQueues = new ArrayList<LinkedBlockingQueue<IBirdSighting>>();
	static DataProcessProvider instance = new DataProcessProvider();
	private Boolean processSightings;
	
	private DataProcessProvider(){
	}
	
	// Create a singleton instance
	public static DataProcessProvider getInstance(){
		
		if(instance == null){
			instance = new DataProcessProvider();
		}
			
		return instance;
	}
	
	/**
	 * Starts N* threads running the injected processor instances
	 * @param threadCount: The number of processing threads to run
	 */
	public void Start(int threadCount){
		
		for (int i = 0; i < threadCount; i++) {
			LinkedBlockingQueue<IBirdSighting> queue = new LinkedBlockingQueue<IBirdSighting>();
			// This will be injected via guice shortly
			ISightingsProcessor processor = new SightingsProcessor();
			processor.assignQueue(queue);
			threadQueues.add(queue);
			runThreads.add(new Thread(processor));
			runThreads.get(runThreads.size() -1).start();
		}
		
		// Start the internal processing of the Collections.sightings LinkedBlockingQueue
		this.startProcessing();
	}
	
	/**
	 * Retrieves sightings from the Collections.sightings queue
	 * whilst in a processing state
	 */
	private void startProcessing(){
		// Now wait on the LinkedBlockingQueue retrieving one item for each new Sighting
		try
		{
			while (processSightings)
			{
				addSighting((BirdSightingImpl) Collections.sightings.take());
			}
		} catch (Exception e)
		{
			// TODO: handle exception
		}
	}
	
	/**
	 * Adds the retrieved sighting to each processor threads queue collection
	 * @param sighting: The IBirdSighting concrete class to add to each thread queue collection
	 */
	private void addSighting(IBirdSighting sighting){
		for (LinkedBlockingQueue<IBirdSighting> queue : threadQueues) {
			queue.add(sighting);
		}
	}		
}