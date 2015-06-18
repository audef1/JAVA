package ch.bfh.sensorseafx.controller;

import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import ch.bfh.sensorseafx.gui.SubscriberGUI;
import ch.bfh.sensorseafx.helpers.Serialiser;
import ch.bfh.sensorseafx.model.Datastore;
import ch.bfh.sensorseafx.sensors.Sensor;

public class Subscriber extends Observable implements Runnable {
	
	private Datastore datastore;
	private ObservableList<String> topics = FXCollections.observableArrayList();
	private Broker broker = new Broker("subscriber");
	private Serialiser ser = new Serialiser();
	private boolean on = true;
	private boolean sysout = false;
	private String status = "";

	public Subscriber(){
		new Thread(this).run();
	}

	public synchronized void run(){
	
		if (broker.isConnected()){
			while(on){
				broker.getClient().setCallback(new MqttCallback() {	
			    	@Override
			    	public void connectionLost(Throwable throwable) {
			    		
			    	}
			
			    	@Override
			    	public void messageArrived(String string, MqttMessage message) throws Exception, StreamCorruptedException{
					   	Sensor s = (Sensor) ser.deserialize(message.getPayload());
					   	if (sysout){
					   		System.out.println("From " + string + ": " + s );
					   	}
					   	datastore.add(s);
				    }
			    	
			    	@Override
			    	public void deliveryComplete(IMqttDeliveryToken imdt) {
			    		
			    	}
			    	
			    });
				
		    	this.setChanged();
		    	this.notifyObservers();
			}
		}
	}
	
	public void subscribe(String topic){
		if (broker.isConnected()){
			if (topics.contains(topic)){
				if (sysout){System.out.println("Already subscribed to the topic " + topic + ".");}else{};
				status = "Already subscribed to the topic " + topic + ".";
				on = true;
			}
			else{
				topics.add(topic);
				try {
					broker.getClient().subscribe(topic);
				} catch (MqttException e) {
					e.printStackTrace();
				}
				if (sysout){System.out.println("Subscribed to the topic " + topic + ".");}else{};
				status = "Subscribed to the topic " + topic + ".";
			}
	    	this.setChanged();
	    	this.notifyObservers();
		}
	}
	
	public void unsubscribe(String topic){
		if (topics.contains(topic)){
			topics.remove(topics.indexOf(topic));
			try {
				broker.getClient().unsubscribe(topic);
			} catch (MqttException e) {
				e.printStackTrace();
			}
			if (sysout){System.out.println("Unsubscribed from topic " + topic + ".");}else{};
			status = "Unsubscribed from topic " + topic + ".";
		}
		else{
			if (sysout){System.out.println("No such topic to unsubscribe.");}else{};
			status = "No topic selected to unsubscribe.";
		}
    	this.setChanged();
    	this.notifyObservers();
	}
	
	public void setBroker(Broker broker){
		this.broker = broker;
	}
	
	public Broker getBroker() {
		return broker;
	}
	
	public ObservableList<String> getTopics(){
		return topics;
	}
	
	public void setDatastore(Datastore datastore){
		this.datastore = datastore;
	}
	
	public void setSysout(boolean sysout){
		this.sysout = sysout;
	}
	
	public String getStatus(){
		return status;
	}
	
}