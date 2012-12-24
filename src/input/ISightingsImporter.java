package input;
import java.util.List;

import domainobjects.IBirdSighting;


public interface ISightingsImporter extends Runnable {

	void assignList(List<IBirdSighting> birdSightings);
}
