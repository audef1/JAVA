package Sensors;

import java.awt.MouseInfo;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.Date;

import Publisher.Publisher;

@SuppressWarnings("serial")
public class TempSensor extends Sensor{
	
	public TempSensor(){}
	
	public TempSensor(String id, Publisher p){
		sourceID = id;
		publisher = p;
	}

	@Override
	public void getValue() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		values.add(p.x);
		//testhalber mit Mausposition
		//connect over bluetooth to sensor with sensorID and get value 
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String date = df.format(new Date(getTimestamp()));
		return date + " - " + getSourceID() + ": " + values.get(0) + ".";
	}
}