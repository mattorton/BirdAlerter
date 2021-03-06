package birdalerter.process;

import java.util.Date;

import birdalerter.common.Collections;
import birdalerter.domainobjects.BirdAlertImpl;
import birdalerter.domainobjects.BirdSightingImpl;

public class SightingsVisitorImpl implements ISightingVisitor{

	public SightingsVisitorImpl() {
	}

	@Override
	public void Visit(BirdSightingImpl sighting) {
		
		BirdAlertImpl alert = new BirdAlertImpl(sighting);
		alert.setDate(new Date());
		
		System.out.println(String.format("Visited sighting for %s. Thread: ", sighting.getName(), Thread.currentThread().getName()));
		Collections.sightingAlerts.add(alert); 
	}
}
