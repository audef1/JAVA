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
	private boolean notify = false;
	private int arraySize = 1000;
	
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
					   	Sensor s = (Sensor) ser.deserialize(message.getPayload());
					   	if (notify){
					   		System.out.println("From " + string + ": " + s );
					   	}
					   	addSensor(s);
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
		if (sensors.size() < arraySize){
			sensors.add(s);
			if (notify){System.out.println("Sensor " + s.getSourceID() + " added to list.");}else{};
		}
		else
		{
			export();
			sensors.add(s);
			if (notify){System.out.println("Sensor " + s.getSourceID() + " added to list.");}else{};
		}
	}

	
	public void subscribe(String topic){
		if (topics.contains(topic)){
			if (notify){System.out.println("Already subscribed to the topic " + topic + ".");}else{};
		}
		else{
			topics.add(topic);
			if (notify){System.out.println("Subscribed to the topic " + topic + ".");}else{};
		}
	}
	
	public void unsubscribe(String topic){
		if (topics.contains(topic)){
			topics.remove(topics.indexOf(topic));
			if (notify){System.out.println("Unsubscribed from topic " + topic + ".");}else{};
		}
		else
			if (notify){System.out.println("No such topic to unsubscribe from.");}else{};
	}
	
	public void setArraySize(int arraySize){
		this.arraySize = arraySize;		
	}
	
	public void setNotify(boolean notify){
		this.notify = notify;
	}
	
	public void export(){
		//send ArrayList<Sensor> to JAXB and write XML-file
		if (notify){ System.out.println("Exporting list...."); } else { };
		sensors = new ArrayList<Sensor>();
	}
}
