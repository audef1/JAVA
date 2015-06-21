package ch.bfh.sensorseafx.sensors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

import javax.xml.bind.annotation.XmlElement;

public abstract class Sensor extends ScheduledService<Void> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private long timestamp;
	private String sourceID;

	@XmlElement(name ="value")
	private ArrayList<Object> values = new ArrayList<Object>();
	
	public Sensor(){}
	
	public abstract void addValues();
	public abstract String toString();
		
	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				System.out.println("Sensor "+ sourceID + " running");
				setTimestamp();
				addValues();
				cleanup();
				return null;
			}
		};
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
	
	public ArrayList<Object> getValues(){
		return values;
	}

}
