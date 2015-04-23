package Publisher;

import java.io.Serializable;

public abstract class Sensor extends Thread implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long timestamp;
	private String sourceID;
	private transient Publisher publisher;
	private boolean on = true;
	private int interval = 1000;
	
	public Sensor(){
		
	}
	
	public abstract void getValue();
	public abstract void export();
	
	public synchronized void run(){
		while (on){
			getValue();
			export();
			try {
				wait(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
				on = false;
			}
		}
	}
	
	public void setTimestamp(long timestamp){
		this.timestamp = timestamp;
	}
	
	public void setSourceID(String id){
		this.sourceID = id;
	}
	
	public String getSourceID(){
		return sourceID;
	}
	
	public void setPublisher(Publisher publisher){
		this.publisher = publisher;
	}
	
	public Publisher getPublisher(){
		return publisher;
	}
	
	public void setInterval(int interval){
		this.interval = interval;
	}
	
}
