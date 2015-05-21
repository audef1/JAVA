package Controller;

import java.io.StreamCorruptedException;
import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import GUI.SubscriberGUI;
import Helpers.Serialiser;
import Model.Datastore;
import Sensors.Sensor;

public class Subscriber extends Thread {
	
	private Datastore datastore;
	private ArrayList<String> topics = new ArrayList<String>();
	private Broker broker = new Broker();
	private Serialiser ser = new Serialiser();
	private boolean on = false;
	private boolean sysout = false;
	
	public Subscriber(){
		this.setDaemon(false);
		this.start();
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
			}
		}
	}
	
	public void subscribe(String topic){
		if (broker.isConnected()){
			if (topics.contains(topic)){
				if (sysout){System.out.println("Already subscribed to the topic " + topic + ".");}else{};
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
			if (sysout){System.out.println("Unsubscribed from topic " + topic + ".");}else{};
		}
		else
			if (sysout){System.out.println("No such topic to unsubscribe from.");}else{};
	}
	
	public void setBroker(Broker broker){
		this.broker = broker;
	}
	
	public void setDatastore(Datastore datastore){
		this.datastore = datastore;
	}
	
	public void setSysout(boolean sysout){
		this.sysout = sysout;
	}
}