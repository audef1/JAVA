package Subscriber;

import java.io.StreamCorruptedException;
import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import Application.Serialiser;
import Broker.Broker;
import Datastore.Datastore;
import Sensors.Sensor;

public class Subscriber extends Thread {
	
	private Datastore datastore;
	private ArrayList<String> topics = new ArrayList<String>();
	private Broker broker;
	private Serialiser ser = new Serialiser();
	private boolean on = true;
	private boolean sysout = false;
	
	public Subscriber(Broker broker, Datastore datastore){
		this.broker = broker;
		this.datastore = datastore;
	}
	
	public synchronized void run(){
		try {
			for (String topic:topics){
				broker.getClient().subscribe(topic);
			}
			
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
			}
		} catch (MqttException e) {
			on = false;
			broker.disconnect();
			e.printStackTrace();
		}
	}
	
	public void subscribe(String topic){
		if (topics.contains(topic)){
			if (sysout){System.out.println("Already subscribed to the topic " + topic + ".");}else{};
		}
		else{
			topics.add(topic);
			if (sysout){System.out.println("Subscribed to the topic " + topic + ".");}else{};
		}
	}
	
	public void unsubscribe(String topic){
		if (topics.contains(topic)){
			topics.remove(topics.indexOf(topic));
			if (sysout){System.out.println("Unsubscribed from topic " + topic + ".");}else{};
		}
		else
			if (sysout){System.out.println("No such topic to unsubscribe from.");}else{};
	}
	
	public void setSysout(boolean sysout){
		this.sysout = sysout;
	}

}
