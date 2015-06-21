package ch.bfh.sensorseafx.controller;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Observable;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import ch.bfh.sensorseafx.helpers.Serialiser;
import ch.bfh.sensorseafx.model.SensorList;
import ch.bfh.sensorseafx.model.SubscriberList;
import ch.bfh.sensorseafx.sensors.Sensor;

public class Publisher extends ScheduledService<Void>{

	private Broker broker = new Broker("publisher");
	private SubscriberList topics = new SubscriberList();
	private SensorList sensors = new SensorList();
	private Serialiser ser = new Serialiser();
	private boolean debug = false;
	
	public Publisher(Broker broker){
		this.broker = broker;
	}
	
	public Publisher(){
		
	}
	
	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				System.out.println("Service running");
				if (!(sensors.getSensors().isEmpty())){
					
					System.out.println("Sending Sensorvalues every 5 Seconds");
					
					for (Sensor sensor : sensors.getSensors()){
						publish(sensor);
					}
					
				}
				return null;
			}
		};
	}
	
	public void publish(Sensor s){
		try {
			byte[] bytes = ser.serialize(s);
			MqttMessage message = new MqttMessage(bytes);
			try {
				for (String topic:topics.getTopics()){
					broker.getClient().publish(topic, message);
					if (debug){System.out.println("Sensorvalue published to " + topic + "!");}else{};
				}
			} catch (MqttPersistenceException e) {
				e.printStackTrace();
			} catch (MqttException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDebug(boolean debug){
		this.debug = debug;
	}
	
	public void subscribe(String topic){
			if (topics.getTopics().contains(topic)){
				if (debug){System.out.println("Topic is already in the distributionlist.");}else{};
			}
			else
			{
				topics.add(topic);
				if (debug){System.out.println("Subscribed to Topic " + topic + ".");}else{};
			}	
	}
	
	public void unsubscribe(String topic){
		if (topics.getTopics().contains(topic)){
			topics.remove(topic);
		}	
		else{
			if (debug){System.out.println("No such topic to remove.");}else{};
		}	
	}
	
	public void unsubscribeAll(){
		for (String topic : topics.getTopics()){
			topics.remove(topic);
		}
	}
	
	public void addSensor(Sensor sensor){
		if (sensors.getSensors().contains(sensor)){
			if (debug){System.out.println("Sensor was already added.");}else{};
		}
		else
		{
			sensors.add(sensor);
			if (debug){System.out.println("Sensor has been added to the list.");}else{};
		}	
	}
	
	public void removeSensor(Sensor sensor){
		if (sensors.getSensors().contains(sensor)){
			sensors.remove(sensor);
		}	
		else{
			if (debug){System.out.println("No such Sensor to remove.");}else{};
		}	
	}
	
	public SubscriberList getSubscriberList(){
		return topics;
	}
	
	public Broker getBroker(){
		return broker;
	}

}
