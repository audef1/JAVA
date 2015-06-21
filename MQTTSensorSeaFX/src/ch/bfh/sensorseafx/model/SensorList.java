package ch.bfh.sensorseafx.model;

import java.util.Observable;

import ch.bfh.sensorseafx.sensors.Sensor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SensorList extends Observable{

	private ObservableList<Sensor> sensors = FXCollections.observableArrayList();	

	public void add(Sensor sensor){
		sensors.add(sensor);
		this.setChanged();
    	this.notifyObservers();
	}
	
	public void remove(Sensor sensor){
		sensors.remove(sensors.indexOf(sensor));
		this.setChanged();
    	this.notifyObservers();
	}
	
	public void removeAll(){
		sensors = FXCollections.observableArrayList();
		this.setChanged();
		this.notifyObservers();
	}
	
	public ObservableList<Sensor> getSensors(){
		return sensors;
	}
}
