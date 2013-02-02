package birdalerter.process;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import birdalerter.common.Collections;

public class DataProcessProvider {

	private ExecutorService pool;
	static DataProcessProvider instance = new DataProcessProvider();
	public Boolean running;

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
		pool = Executors.newFixedThreadPool(threadCount);
		for (int i = 0; i < threadCount; i++) {
			
			// Configured to use Spring DI
			// New factory created for each to create new object
			// for each reference
			ISightingsProcessor processor = new ProcessorFactory().getSightingsProcessor();
			processor.assignQueue(Collections.sightings);
			pool.execute(processor);
		}
		running = true;
	}
	
	/**
	 * The following stop() method shuts down an ExecutorService in two phases,
	 * first by calling shutdown to reject incoming tasks, and then calling
	 * shutdownNow, if necessary, to cancel any lingering tasks
	 */
	public void stop(){
		// Disable new tasks from being submitted
		pool.shutdown();
		try
		{
			// Wait for existing tasks to terminate
			if(!pool.awaitTermination(60, TimeUnit.SECONDS))
			{
				// Threads are taking too long to join - cancel currently executing tasks
				pool.shutdownNow();
		        // Wait a while for tasks to respond to being cancelled
		        if (!pool.awaitTermination(60, TimeUnit.SECONDS))
		        {
		        	// TODO: Swap out for log4j logging
		           System.err.println("Pool did not terminate");		        	
		        }
			}
		}
		catch (InterruptedException ie)
		{
		     // (Re-)Cancel if current thread also interrupted
		     pool.shutdownNow();
		     // Preserve interrupt status
		     Thread.currentThread().interrupt();
		}
	}
	
	public Integer runningThreads(){
		// Explicitly cast to ThreadPoolExecutor to access
		// getActiveCount method
		return ((ThreadPoolExecutor)pool).getActiveCount();
	}
	
}