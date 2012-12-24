package processing;
//import RegionSightingAlertProcessor;
//import SightingAlertProcessorImpl;
//import SightingsMonitorWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import domainobjects.*;

public class DataProcessProvider {

	//private SightingAlertProcessorImpl[] sightingsProcessors = new SightingAlertProcessorImpl[]{new RegionSightingAlertProcessor()};
	private List<Thread> runThreads = new ArrayList<Thread>();
	private List<LinkedBlockingQueue<IBirdSighting>> threadQueues = new ArrayList<LinkedBlockingQueue<IBirdSighting>>();
	static DataProcessProvider instance = new DataProcessProvider();
	private Boolean processSightings;
	
	private DataProcessProvider(){
		// For each SightingsProcessor instance create a List<BirdSighting> instance
		// keyed on the concrete class name
		//for (SightingAlertProcessorImpl processor : sightingsProcessors) {
		//	threadQueues.put(processor.getClass().getName(), new ArrayList<BirdSighting>());
		//	processor.assignList(threadQueues.get(processor.getClass().getName()));
		//}
	}
	
	// Create a singleton instance
	public static DataProcessProvider getInstance(){
		
		if(instance == null){
			instance = new DataProcessProvider();
		}
			
		return instance;
	}
	
	public void start(int threadCount){
		
		for (int i = 0; i < threadCount; i++) {
			LinkedBlockingQueue<IBirdSighting> queue = new LinkedBlockingQueue<IBirdSighting>();
			ISightingsProcessor processor = new SightingsProcessor();
			processor.assignQueue(queue);
			threadQueues.add(queue);
			runThreads.add(new Thread(processor));
			runThreads.get(runThreads.size() -1).start();
		}
		
	}
	
	public void startProcessing(){
		// Start each processor in its own thread
		//this.startProcessors();
		
		// Now wait on the LinkedBlockingQueue pulling one item off for each new Sighting
		try
		{
			while (processSightings)
			{
				addSighting((BirdSightingImpl) SightingsMonitorWrapper.sightings.take());
			}
		} catch (Exception e)
		{
			// TODO: handle exception
		}
	}
}