package Datastore;

import java.util.ArrayList;

import Sensors.Sensor;

public class Datastore {

	private ArrayList<Sensor> datastore = new ArrayList<Sensor>();
	
	public Datastore(){
		
	}
	
	public void add(Sensor s){
		datastore.add(s);
	}
	
	public void export(){
		System.out.println("Exporting list....");
		
		//send ArrayList<Sensor> to JAXB and write XML-file
		
		datastore = new ArrayList<Sensor>();
	}
}
