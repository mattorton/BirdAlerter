package process;

import domainobjects.*;

public interface ISightingVisitor {
	public void Visit(BirdSightingImpl sighting);
}
