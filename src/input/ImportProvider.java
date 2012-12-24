package input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domainobjects.IBirdSighting;

public class ImportProvider {

	private ISightingsImporter[] sightingsImporters = new ISightingsImporter[]{new TcpSightingsImporterImpl(4444, 20)};
	private List<Thread> runThreads = new ArrayList<Thread>();
	private Map<String, List<IBirdSighting>> threadQueues = new HashMap<String, List<IBirdSighting>>();
	static ImportProvider instance = new ImportProvider();
	
	private ImportProvider(){
		// For each SightingsImporter instance create a List<BirdSighting> instance
		// keyed on the concrete class name
		for (ISightingsImporter importer : sightingsImporters) {
			threadQueues.put(importer.getClass().getName(), new ArrayList<IBirdSighting>());
			importer.assignList(threadQueues.get(importer.getClass().getName()));
		}
	}
	
	// Create a singleton instance
	public static ImportProvider getInstance(){
		
		if(instance == null){
			instance = new ImportProvider();
		}
			
		return instance;
	}
	
	// Start each importer running in a new thread
	public void startImporters(){
		for (ISightingsImporter importer : sightingsImporters) {
			Thread runThread = new Thread(importer);
			runThreads.add(runThread);
			// Start the thread
			runThreads.get(runThreads.size()-1).start();
		}
	}
	
	// Stop each of the spawned threads
	public void stopImporters(){
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