package birdalerter.common;


import java.util.concurrent.LinkedBlockingQueue;

import birdalerter.domainobjects.*;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Collections {
	
	public static LinkedBlockingQueue<IBirdSighting> sightings = new LinkedBlockingQueue<IBirdSighting>();
	public static LinkedBlockingQueue<BirdAlertImpl> sightingAlerts = new LinkedBlockingQueue<BirdAlertImpl>();
	private static Injector injector = Guice.createInjector();
	
	public static synchronized Injector getInjector(){
		return injector;
	}
}
