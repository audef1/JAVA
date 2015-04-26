package Subscriber;

import java.io.StreamCorruptedException;
import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import Application.Serialiser;
import Broker.Broker;
import Sensors.Sensor;


public class Subscriber extends Thread {

	private ArrayList<Sensor> sensors = new ArrayList<Sensor>();
	private ArrayList<String> topics = new ArrayList<String>();
	private Broker broker;
	private Serialiser ser = new Serialiser();
	private boolean on = true;
	
	public Subscriber(Broker broker){
		this.broker = broker;
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
					   	System.out.printf("From (%s):", string);
					   	Sensor s = (Sensor) ser.deserialize(message.getPayload());
					   	addSensor(s);
					   	System.out.println(s);
				    }
			
			    	@Override
			    	public void deliveryComplete(IMqttDeliveryToken imdt) {
			    		
			    	}
			    });
			}
		} catch (MqttException e) {
			on = false;
			e.printStackTrace();
		}
	}
	
	public void addSensor(Sensor s){
		if (sensors.size() < 10){
			sensors.add(s);
			System.out.println("Sensor added to list.");
		}
		else
		{
			export();
			sensors.add(s);
			System.out.println("Sensor added to list.");
		}
	}

	
	public void subscribe(String topic){
		if (topics.contains(topic)){
			System.out.println("Already subscribed to the topic " + topic + ".");
		}
		else{
			topics.add(topic);
			System.out.println("Subscribed to the topic " + topic + ".");
		}
	}
	
	public void unsubscribe(String topic){
		if (topics.contains(topic)){
			topics.remove(topics.indexOf(topic));
			System.out.println("Unsubscribed from topic " + topic + ".");
		}
		else
			System.out.println("No such topic to unsubscribe from.");
	}
	
	public void export(){
		//send ArrayList<Sensor> to JAXB and write XML-file
		System.out.println("Exporting list...");
		sensors = new ArrayList<Sensor>();
	}
}
