package Sensors;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.Date;

import Publisher.Publisher;

public class TempSensor extends Sensor{

	private int temp = 0;
	
	public TempSensor(){}
	
	public TempSensor(String id, Publisher p){
		setTimestamp(new Date().getTime());
		setSourceID(id);
		setPublisher(p);
	}

	@Override
	public void getValue() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		temp = p.x;
		//testhalber mit Mausposition
		//connect over bluetooth to sensor with sensorID and get value 
	}
	
	@Override
	public void export() {
		getPublisher().publish(this);
	}

	@Override
	public String toString() {
		Date date = new Date(getTimestamp());
		return date + " - " + getSourceID() + ": " + temp + ".";
	}
}