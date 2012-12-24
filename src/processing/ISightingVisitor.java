package processing;

import domainobjects.*;

public interface ISightingVisitor {
	public void Visit(BirdSightingImpl sighting);
}
