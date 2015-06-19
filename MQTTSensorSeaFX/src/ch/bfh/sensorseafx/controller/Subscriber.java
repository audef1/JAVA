package ch.bfh.sensorseafx.controller;

import java.io.StreamCorruptedException;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import ch.bfh.sensorseafx.helpers.Serialiser;
import ch.bfh.sensorseafx.model.Datastore;
import ch.bfh.sensorseafx.sensors.Sensor;

public class Subscriber extends Observable implements Runnable{
	
	private Datastore datastore;
	private ObservableList<String> topics = FXCollections.observableArrayList();
	private Broker broker = new Broker("subscriber");
	private Serialiser ser = new Serialiser();
	private boolean on = true;
	private boolean debug = false;
	private String status = "";

	public Subscriber(){
		
	}
	
	public Task checkMessages() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				
				while (broker.isConnected()) {
					System.out.println("subscriber running");
					broker.getClient().setCallback(new MqttCallback() {
						@Override
						public void connectionLost(Throwable throwable) {

						}

						@Override
						public void messageArrived(String string, MqttMessage message)
								throws Exception, StreamCorruptedException {
							if (isSensor(ser.deserialize(message.getPayload()))) {
								Sensor s = (Sensor) ser.deserialize(message
										.getPayload());
								if (debug) {
									System.out.println("From " + string + ": " + s);
								}
								datastore.add(s);
							} else {
								if (debug) {
									System.out.println("Message: "
											+ message.getPayload());
								}
							}
						}

						@Override
						public void deliveryComplete(IMqttDeliveryToken imdt) {

						}

					});
					
					Thread.sleep(1000);

				}
				
				return true;
			}
		};
	}
	
	public synchronized void run() {
		while (broker.isConnected()) {
			System.out.println("subscriber running");
			broker.getClient().setCallback(new MqttCallback() {
				@Override
				public void connectionLost(Throwable throwable) {

				}

				@Override
				public void messageArrived(String string, MqttMessage message)
						throws Exception, StreamCorruptedException {
					if (isSensor(ser.deserialize(message.getPayload()))) {
						Sensor s = (Sensor) ser.deserialize(message
								.getPayload());
						if (debug) {
							System.out.println("From " + string + ": " + s);
						}
						datastore.add(s);
					} else {
						if (debug) {
							System.out.println("Message: "
									+ message.getPayload());
						}
					}
				}

				@Override
				public void deliveryComplete(IMqttDeliveryToken imdt) {

				}

			});

			this.setChanged();
			this.notifyObservers();
		}
	}

	
	public void subscribe(String topic){
		if (broker.isConnected()){
			if (topics.contains(topic)){
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
				
				this.setChanged();
		    	this.notifyObservers();
			}
	    	
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
			
			if (debug){System.out.println("Unsubscribed from topic " + topic + ".");}else{};
			status = "Unsubscribed from topic " + topic + ".";
			
			this.setChanged();
	    	this.notifyObservers();
		}
		else{
			if (debug){System.out.println("No such topic to unsubscribe.");}else{};
			status = "No topic selected to unsubscribe.";
		}
    	
	}
	
	public void unsubscribeAll(){
		for (String topic : topics){
			unsubscribe(topic);
		}
		topics = FXCollections.observableArrayList();	
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
	
	public void setDebug(boolean debug){
		this.debug = debug;
	}
	
	public String getStatus(){
		return status;
	}
	
	private boolean isSensor(Object o) {
		if (o == null)
			  return false;
		if (o instanceof Sensor)
			  return true;
		else
			return false;
	}
	
}