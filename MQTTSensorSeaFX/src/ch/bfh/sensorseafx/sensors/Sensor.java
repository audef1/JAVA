package ch.bfh.sensorseafx.sensors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import ch.bfh.sensorseafx.controller.Publisher;

public abstract class Sensor extends Thread implements Serializable {

	private static final long serialVersionUID = 1L;

	private long timestamp;
	private String sourceID;
	private transient Publisher publisher;
	private transient boolean on = true;
	private transient int interval = 1000;
	
	@XmlElement(name ="value")
	private ArrayList<Object> values = new ArrayList<Object>();
	
	public Sensor(){}
	
	public abstract void addValues();
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
				on = false;
				System.out.println("Sensor " + sourceID + " has been shutdown due to an error.");
				e.printStackTrace();
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
	
	public ArrayList<Object> getValues(){
		return values;
	}
	
}
