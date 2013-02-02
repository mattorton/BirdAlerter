import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import birdalerter.common.AppConfig;
import birdalerter.common.Collections;
import birdalerter.domainobjects.BirdAlertImpl;
import birdalerter.domainobjects.IBirdSighting;
import birdalerter.input.ImportProvider;
import birdalerter.process.DataProcessProvider;

public class SightingsMonitor {

	/**
	 * The wrapper class from which the BirdAlerter runs
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext ctx = 
				   new AnnotationConfigApplicationContext();
		
		ctx.register(AppConfig.class);
		ctx.refresh();
		
		Collections.sightings = new LinkedBlockingQueue<IBirdSighting>();
		Collections.sightingAlerts = new LinkedBlockingQueue<BirdAlertImpl>();

		// Start the data import threads via the ImportProvider
		ImportProvider.getInstance().start();
		
		// Start the data process threads via the DataProcessProvider
		DataProcessProvider.getInstance().start(4);
	}
}
