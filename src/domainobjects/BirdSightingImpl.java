package domainobjects;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import process.ISightingVisitor;

/*
 * 
 */
public class BirdSightingImpl implements IBirdSighting, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6005447360168489620L;
	private UUID uuid;
	private Date date;
	private String name;
	
	@Override
	public UUID getUUID() {
		return uuid;
	}

	@Override
	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public Date getDate() {
		if(date == null){
			 date = new Date();
		}
		return date;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}
	
	public BirdSightingImpl(){
	}
	
	public BirdSightingImpl(UUID uuid, Date date, String name){
		this.uuid = uuid;
		this.date = date;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void accept(ISightingVisitor visitor) {
		visitor.Visit(this);
	}
}
