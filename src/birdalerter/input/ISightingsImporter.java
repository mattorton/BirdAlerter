package birdalerter.input;
import java.util.List;

import birdalerter.domainobjects.IBirdSighting;



public interface ISightingsImporter extends Runnable {

	void assignList(List<IBirdSighting> birdSightings);
}
