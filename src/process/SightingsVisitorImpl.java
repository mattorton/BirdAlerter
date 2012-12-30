package process;

import domainobjects.BirdSightingImpl;

public class SightingsVisitorImpl implements ISightingVisitor{

	public SightingsVisitorImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Visit(BirdSightingImpl sighting) {
		System.out.println(String.format("Visited sighting for %s. Thread: ", sighting.getName(), Thread.currentThread().getName()));
	}
}
