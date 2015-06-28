package ch.bfh.sensorseafx.sensors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

import javax.xml.bind.annotation.XmlElement;

public abstract class Sensor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long timestamp;
	private String sourceID;
	private boolean running = false;

	@XmlElement(name ="value")
	private ArrayList<Object> values = new ArrayList<Object>();
	
	public Sensor(){}
	
	public abstract void addValues();
	public abstract String toString();
	
	public void update(){
		setTimestamp();
		addValues();
	}
	
	public void cleanup(){
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

	public void setRunning(boolean running){
		if (running){
			this.running = running;
			System.out.println("Sensor "+ sourceID + " running.");
		}
		else{
			this.running = running;
			System.out.println("Sensor "+ sourceID + " stopped.");
		}
	}

	public boolean isRunning() {
		return running;
	}
}
