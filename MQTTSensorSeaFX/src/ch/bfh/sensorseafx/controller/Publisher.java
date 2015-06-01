package ch.bfh.sensorseafx.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import ch.bfh.sensorseafx.helpers.Serialiser;
import ch.bfh.sensorseafx.sensors.Sensor;

public class Publisher {

	private Broker broker;
	private ArrayList<String> topics = new ArrayList<String>();
	private Serialiser ser = new Serialiser();
	private boolean sysout = false;
	
	public Publisher(Broker broker){
		this.broker = broker;
	}
	
	public Publisher(){
	}
	
	public void publish(Sensor s){
		try {
			byte[] bytes = ser.serialize(s);
			MqttMessage message = new MqttMessage(bytes);
			try {
				for (String topic:topics){
					broker.getClient().publish(topic, message);
					if (sysout){System.out.println("Sensorvalue published to " + topic + "!");}else{};
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
	
	public void setSysout(boolean sysout){
		this.sysout = sysout;
	}
	
	public void addTopic(String topic){
			if (topics.contains(topic)){
				if (sysout){System.out.println("Topic is already in the distributionlist.");}else{};
			}
			else
			{
				topics.add(topic);
			}	
	}
	
	public void removeTopic(String topic){
		if (topics.contains(topic))
				topics.remove(topics.indexOf(topic));
		else
			if (sysout){System.out.println("No such topic to remove.");}else{};
	}
}
