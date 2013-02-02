package birdalerter.process;

import birdalerter.domainobjects.*;

public interface ISightingVisitor {
	public void Visit(BirdSightingImpl sighting);
}
