package Publisher;

public abstract class Sensor {

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
