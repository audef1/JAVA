package Sensors;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import Controller.Publisher;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ligthsensor")
public class LightSensor extends Sensor{

	private static final long serialVersionUID = 1L;

	public LightSensor(){}
	
	public LightSensor(String id, Publisher p){
		this.setSourceID(id);
		this.setPublisher(p);
	}
	
	@Override
	public void addValues() {
		//werte eines lichtsensors
		this.getValues().add(10 + (int)(Math.random()*100));
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String date = df.format(new Date(getTimestamp()));
		return date + " - " + getSourceID() + ": " + this.getValues().get(0) + " Lumen.";
	}

}
