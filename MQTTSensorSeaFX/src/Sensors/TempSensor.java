package Sensors;

import java.awt.MouseInfo;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	public void addValues() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		values.add(p.x);
		values.add(p.y);
		//testhalber mit Mausposition
		//connect over bluetooth to sensor with sensorID and get value 
	}
	
	public int getX(){
		return (int) values.get(0);
	}
	
	public int getY(){
		return (int) values.get(1);
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String date = df.format(new Date(getTimestamp()));
		return date + " - " + getSourceID() + ": " + values.get(0) + ".";
	}

	@Override
	public ArrayList<Object> getValues() {
		return values;		
	}
}