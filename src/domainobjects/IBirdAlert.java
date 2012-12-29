package domainobjects;

import java.util.Date;

public interface IBirdAlert{	
	IBirdSighting getSighting();
	void setSighting(IBirdSighting sighting);
	
	Date getDate();
	void setDate(Date date);
	
	String getName();
	void setName(String name);
}