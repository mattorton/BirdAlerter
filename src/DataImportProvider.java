import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataImportProvider {

	private SightingsImporter[] sightingsImporters = new SightingsImporter[]{new TcpSightingsImporterImpl(4444, 20)};
	private List<Thread> runThreads = new ArrayList<Thread>();
	private Map<String, List<BirdSighting>> threadQueues = new HashMap<String, List<BirdSighting>>();
	static DataImportProvider instance = new DataImportProvider();
	
	private DataImportProvider(){
		// For each SightingsImporter instance create a List<BirdSighting> instance
		// keyed on the concrete class name
		for (SightingsImporter importer : sightingsImporters) {
			threadQueues.put(importer.getClass().getName(), new ArrayList<BirdSighting>());
			importer.assignList(threadQueues.get(importer.getClass().getName()));
		}
	}
	
	// Create a singleton instance
	public static DataImportProvider getInstance(){
		
		if(instance == null){
			instance = new DataImportProvider();
		}
			
		return instance;
	}
	
	// Start each importer running in a new thread
	public void startImporters(){
		for (SightingsImporter importer : sightingsImporters) {
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