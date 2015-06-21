package ch.bfh.sensorseafx.controller;

import java.io.IOException;
import java.util.Date;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import ch.bfh.sensorseafx.helpers.Serialiser;
import ch.bfh.sensorseafx.model.SensorList;
import ch.bfh.sensorseafx.model.SubscriberList;
import ch.bfh.sensorseafx.sensors.Sensor;

public class Publisher extends ScheduledService<Void>{
	
	private String id = Double.toString(new Date().getTime());
	private Broker broker = new Broker("pub" + id);
	private SubscriberList topics = new SubscriberList();
	private SensorList sensors = new SensorList();
	private Serialiser ser = new Serialiser();
	private boolean debug = false;
	private String status = "";
	
	public Publisher(){}
	
	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				System.out.println("publisherservice running...");
				if (!(sensors.getSensors().isEmpty())){
					if (!(topics.getTopics().isEmpty())){
						System.out.println("connected - publishing values...");
						for (Sensor sensor : sensors.getSensors()){
							if (sensor.isRunning()){
								publish(sensor);
							}
						}
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
		if (broker.getClient().isConnected()){
			if (topics.getTopics().contains(topic)){
				if (debug){System.out.println("Already subscribed to the topic " + topic + ".");}else{};
				status = "Already subscribed to the topic " + topic + ".";
			}
			else{
				topics.add(topic);
				
				try {
					broker.getClient().subscribe(topic);
				} catch (MqttException e) {
					e.printStackTrace();
				}
				
				if (debug){System.out.println("Subscribed to the topic " + topic + ".");}else{};
				status = "Subscribed to the topic " + topic + ".";
				
			}
	    	
		}
	}
	
	public void unsubscribe(String topic){
		if (topics.getTopics().contains(topic)){
			topics.remove(topic);
			
			try {
				broker.getClient().unsubscribe(topic);
			} catch (MqttException e) {
				e.printStackTrace();
			}
			
			if (debug){System.out.println("Unsubscribed from topic " + topic + ".");}else{};
			status = "Unsubscribed from topic " + topic + ".";
			
		}
		else{
			if (debug){System.out.println("No such topic to unsubscribe.");}else{};
			status = "No topic selected to unsubscribe.";
		}
    	
	}
	
	public void unsubscribeAll(){
		for (String topic : topics.getTopics()){
			unsubscribe(topic);
		}
		topics.removeAll();
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
	
	public SensorList getSensorList(){
		return sensors;
	}
	
	public Broker getBroker(){
		return broker;
	}

}
