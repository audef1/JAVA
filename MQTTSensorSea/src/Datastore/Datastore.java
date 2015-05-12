package Datastore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import Application.XMLHelper;
import Sensors.Sensor;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace="http://ch.audef1.mqttsensorsea",name="datastore")
public class Datastore {
	
	@XmlElement(name ="timestamp")
	private long lastupdate = new Date().getTime();
	
	@XmlElement(name = "sensors")
	private ArrayList<Sensor> datastore = new ArrayList<Sensor>();
	
	public Datastore(){
		
	}
	
	public void add(Sensor s){
		
		//int interval = (int) (new Date().getTime() - lastupdate) / (1000*60*60*24); //days
		//int interval = (int) (((new Date().getTime() - lastupdate) / (1000*60*60)) % 24); //hours
		int interval = (int) (((new Date().getTime() - lastupdate) / (1000*60)) % 60); //minutes
	
		if (interval == 1){
			try {
				export();
			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			lastupdate = new Date().getTime();
		}
			
		datastore.add(s);
	}
	
	public ArrayList<Sensor> getDatastore(){
		return datastore;
	}
	
	public void export() throws JAXBException, IOException{
		System.out.println("Exporting list....");
		
		SimpleDateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH:mm:ss");
		String date = df.format(lastupdate);
		
		OutputStream os = new FileOutputStream("export_" + date + ".xml");
		XMLHelper.saveInstance(os, this);
		os.close();
		
		datastore = new ArrayList<Sensor>();
	}
}
