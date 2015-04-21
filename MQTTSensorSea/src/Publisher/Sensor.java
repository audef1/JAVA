package Publisher;

import java.io.Serializable;

public abstract class Sensor extends Thread implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long timestamp;
	private String sourceID;
	
	public Sensor(){
		
	}
	
	public abstract void getValues();
	public abstract void export();
	
	
	public void setTimestamp(long timestamp){
		this.timestamp = timestamp;
	}
	
	public void setSourceID(String id){
		this.sourceID = id;
	}
}
