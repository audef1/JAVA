package Datastore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Sensors.Sensor;

public class Datastore {
	
	private ArrayList<Sensor> datastore = new ArrayList<Sensor>();
	
	public Datastore(){
		
	}
	
	public void add(Sensor s){
		String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		if (time.equals("00:00:00"))
			export();
		datastore.add(s);
	}
	
	public ArrayList<Sensor> getDatastore(){
		return datastore;
	}
	
	public void export(){
		System.out.println("Exporting list....");
		
		//send ArrayList<Sensor> to JAXB and write XML-file
		
		datastore = new ArrayList<Sensor>();
	}
}
