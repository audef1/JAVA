package Sensors;

import java.awt.MouseInfo;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import Controller.Publisher;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tempsensor")
public class TempSensor extends Sensor{
	
	private static final long serialVersionUID = 1L;

	public TempSensor(){}
	
	public TempSensor(String id, Publisher p){
		this.setSourceID(id);
		this.setPublisher(p);
	}

	@Override
	public void addValues() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		this.getValues().add(p.x);
		this.getValues().add(p.y);
		//testhalber mit Mausposition
		//connect over bluetooth to sensor and get value 
	}
	
	public int getX(){
		return (int) this.getValues().get(0);
	}
	
	public int getY(){
		return (int) this.getValues().get(1);
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String date = df.format(new Date(getTimestamp()));
		return date + " - " + getSourceID() + ": " + this.getValues().get(0) + " | " + this.getValues().get(1) +" .";
	}
}