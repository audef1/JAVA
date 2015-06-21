package ch.bfh.sensorseafx.sensors;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ch.bfh.sensorseafx.controller.Publisher;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tempsensor")
public class TempSensor extends Sensor {
	
	private static final long serialVersionUID = 1L;
	
	public TempSensor(){}
	
	public TempSensor(String id, Publisher p){
		this.setSourceID(id);
		this.setPublisher(p);
	}

	@Override
	public void addValues() {
		//werte eines temperatursensors
		//System.out.println("es wurden: " + getSming().readTempAttribute() + " gemessen.");
		getValues().add(1 + (int)(Math.random()*37));
		
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String date = df.format(new Date(getTimestamp()));
		return date + " - " + getSourceID() + ": " + getValues().get(0) +" .";
	}

}