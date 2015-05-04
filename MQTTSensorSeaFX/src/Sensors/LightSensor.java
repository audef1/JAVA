package Sensors;

import java.text.SimpleDateFormat;
import java.util.Date;

import Publisher.Publisher;

public class LightSensor extends Sensor{

	private static final long serialVersionUID = 1L;

	public LightSensor(){}
	
	public LightSensor(String id, Publisher p){
		sourceID = id;
		publisher = p;
	}
	
	@Override
	public void addValues() {
		//werte eines lichtsensors
		values.add(20);
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String date = df.format(new Date(getTimestamp()));
		return date + " - " + getSourceID() + ": " + values.get(0) + "°C.";
	}

}
