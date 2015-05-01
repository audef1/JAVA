package Sensors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Publisher.Publisher;

public abstract class Sensor extends Thread implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected long timestamp;
	protected String sourceID;
	protected transient Publisher publisher;
	protected transient boolean on = true;
	protected transient int interval = 1000;
	
	protected ArrayList<Object> values = new ArrayList<Object>();
	
	public Sensor(){
		
	}
	
	public abstract void addValues();
	public abstract ArrayList<Object> getValues();
	public abstract String toString();
	
	public synchronized void run(){
		while (on){
			setTimestamp();
			addValues();
			sendValue();
			cleanup();
			try {
				wait(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
				on = false;
			}
		}
	}
	
	public void sendValue() {
		publisher.publish(this);
	}
	
	private void cleanup(){
		values = new ArrayList<Object>();
	}
	
	public void setTimestamp(){
		this.timestamp = new Date().getTime();
	}
	
	public long getTimestamp(){
		return timestamp;
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
