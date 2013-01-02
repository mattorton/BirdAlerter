package process;
import java.util.ArrayList;
import java.util.List;

import common.Collections;

public class DataProcessProvider {

	private List<Thread> runThreads = new ArrayList<Thread>();
	static DataProcessProvider instance = new DataProcessProvider();
	
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
	public void start(int threadCount){
		for (int i = 0; i < threadCount; i++) {
			// Will swap out with guice injection
			startProcessor(new SightingsProcessor());
		}
	}
	
	//@Inject
	private void startProcessor(ISightingsProcessor processor){
		processor.assignQueue(Collections.sightings);
		runThreads.add(new Thread(processor));
		runThreads.get(runThreads.size() -1).start();
	}
	
	/**
	 * Starts N* threads running the injected processor instances
	 * @param threadCount: The number of processing threads to run
	 */
	public void stop(){
		
		for (Thread thread : runThreads) {
			if(thread != null)
			{
				thread.interrupt();
			}
		}
	}
	
	public Integer runningThreads(){
		
		Integer runningThreadCount = 0;
		for (Thread thread : runThreads) {
			if(thread.isAlive()){
				runningThreadCount++;
			}
		}
		return runningThreadCount;
	}
	
}