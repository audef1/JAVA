package ch.bfh.sensorseafx.model;

import java.util.Observable;

import ch.bfh.sensorseafx.sensors.Sensor;
import ch.bfh.sensorseafx.sensors.TempSensor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SensorList extends Observable{

	//the actual sensors
	private ObservableList<Sensor> sensors = FXCollections.observableArrayList();
	
	//list of devices found
	private ObservableList<String> devices = FXCollections.observableArrayList();
	
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
	
	public void remove(String sensorname){
		Sensor sensor = null;
		for (Sensor s : sensors){
			if (s.getSourceID().equals(sensorname)){
				sensor = s;
			}
		}
		
		sensors.remove(sensors.indexOf(sensor));
		this.setChanged();
    	this.notifyObservers();
	}
	
	public void removeAll(){
		for (Sensor sensor : sensors){
			remove(sensor);
		}
	}
	
	public ObservableList<Sensor> getSensors(){
		return sensors;
	}
	
	public ObservableList<String> getConnectedSensors(){
		ObservableList<String> connectedSensors = FXCollections.observableArrayList();
		for (Sensor sensor : sensors){
			if (sensor.isRunning()){
				connectedSensors.add(sensor.getSourceID());
			}
		}
		this.setChanged();
		this.notifyObservers();
		return connectedSensors;
	}
	
	public void addDevice(String s){
		devices.add(s);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ObservableList<String> getDevices(){
		return devices;
	}
}
