package ch.bfh.sensorseafx.sensors;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.util.Duration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tempsensor")
public class TempSensor extends Sensor {
	
	private static final long serialVersionUID = 2L;
	
	public TempSensor(){}
	
	public TempSensor(String id){
		this.setSourceID(id);
		this.setPeriod(Duration.minutes(1));
		this.start();
		System.out.println("adding new sensor");
		System.out.println("connecting to " + id);
	}

	@Override
	public void addValues() {
		getValues().add(1 + (int)(Math.random()*35));
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String date = df.format(new Date(getTimestamp()));
		return date + " - " + getSourceID() + ": " + getValues().get(0) +" .";
	}

}