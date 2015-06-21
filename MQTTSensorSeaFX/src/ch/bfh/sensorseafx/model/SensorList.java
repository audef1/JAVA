package ch.bfh.sensorseafx.model;

import java.util.Observable;

import ch.bfh.sensorseafx.sensors.Sensor;
import ch.bfh.sensorseafx.sensors.TempSensor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SensorList extends Observable{

	private ObservableList<Sensor> sensors = FXCollections.observableArrayList();
	private ObservableList<String> connectedSensors = FXCollections.observableArrayList();
	private ObservableList<String> testSensors = FXCollections.observableArrayList();
	
	public SensorList(){

	}
	
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
	
	public ObservableList<String> getConnectedSensors(){
		for (Sensor sensor : sensors){
			if (sensor.isRunning()){
				connectedSensors.add(sensor.getSourceID());
			}
		}
		return connectedSensors;
	}
	
	//for testing
	
	public void addTestSensor(String s){
		sensors.add(new TempSensor(s));
		this.setChanged();
    	this.notifyObservers();
	}
	
	
	public ObservableList<String> getTestSensors(){
		return testSensors;
	}
}
