package Publisher;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.Date;

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
		System.out.println("Getting Value from " + getSourceID() + ": " + temp );
		//testhalber mit Mausposition
		//connect over bluetooth to sensor with sensorID and get value 
	}
	
	@Override
	public void export() {
		getPublisher().publish(this);
	}	
}