package ch.bfh.sensorseafx.sensors;

import java.awt.MouseInfo;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ch.bfh.sensorseafx.controller.Publisher;
import ch.bfh.sming.service.Txw51Service;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tempsensor")
public class TempSensor extends Sensor{
	
	private static final long serialVersionUID = 1L;

	public TempSensor(){}
	
	public TempSensor(String id, Publisher p){
		this.setSourceID(id);
		this.setPublisher(p);
		this.initialize("/dev/tty.usbmodem1"); //connect to sming
		this.listServices();
	}

	@Override
	public void addValues() {
		//werte eines temperatursensors
		this.getValues().add(1 + (int)(Math.random()*37)); 
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String date = df.format(new Date(getTimestamp()));
		return date + " - " + getSourceID() + ": " + this.getValues().get(0) +" .";
	}

	@Override
	public void initialize(String serialPort) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listServices() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readAttribute(int handle) {
		// TODO Auto-generated method stub
		
	}

}