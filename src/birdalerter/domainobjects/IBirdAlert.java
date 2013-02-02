package birdalerter.domainobjects;

import java.util.Date;

public interface IBirdAlert{
	
	String getSightingsProcessor();
	
	IBirdSighting getSighting();
	
	Date getDate();
	void setDate(Date date);
}