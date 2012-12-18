import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DataProcessProvider {

	private SightingAlertProcessorImpl[] sightingsProcessors = new SightingAlertProcessorImpl[]{new RegionSightingAlertProcessor()};
	private List<Thread> runThreads = new ArrayList<Thread>();
	private Map<String, List<BirdSighting>> threadQueues = new HashMap<String, List<BirdSighting>>();
	static DataProcessProvider instance = new DataProcessProvider();
	private Boolean processSightings;
	
	private DataProcessProvider(){
		// For each SightingsProcessor instance create a List<BirdSighting> instance
		// keyed on the concrete class name
		for (SightingAlertProcessorImpl processor : sightingsProcessors) {
			threadQueues.put(processor.getClass().getName(), new ArrayList<BirdSighting>());
			processor.assignList(threadQueues.get(processor.getClass().getName()));
		}
	}
	
	// Create a singleton instance
	public static DataProcessProvider getInstance(){
		
		if(instance == null){
			instance = new DataProcessProvider();
		}
			
		return instance;
	}
	
	public void startProcessing(){
		// Start each processor in its own thread
		this.startProcessors();
		
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
	
	private void addSighting(BirdSightingImpl sighting)
	{	
		// Iterate the thread queue HashMap
		Iterator iterator = threadQueues.entrySet().iterator();
		while(iterator.hasNext())
		{
			Map.Entry threadQueue = (Map.Entry)iterator.next();
			((List<BirdSighting>)threadQueue.getValue()).add(sighting);
		}
	}
	
	// Start each processor running in a new thread
	private void startProcessors(){
		processSightings = true;
		for (SightingAlertProcessorImpl processor : sightingsProcessors) {
			Thread runThread = new Thread(processor);
			runThreads.add(runThread);
			// Start the thread
			runThreads.get(runThreads.size()-1).start();
		}
	}
	
	// Stop each of the spawned threads
	public void stopProcessors(){
		processSightings = false;
		for (Thread thread: runThreads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}