package birdalerter.domainobjects;

import java.util.Date;

public class BirdAlertImpl implements IBirdAlert{

	private Date date;
	private IBirdSighting sighting;
	
	public BirdAlertImpl(IBirdSighting sighting) {
		this.sighting = sighting;
	}

	@Override
	public String getSightingsProcessor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBirdSighting getSighting() {
		return sighting;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}
}
