package Publisher;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import Sensors.Sensor;
import Application.Serialiser;
import Broker.Broker;

public class Publisher {

	private Broker broker;
	private ArrayList<String> topics = new ArrayList<String>();
	private Serialiser ser = new Serialiser();
	
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
					System.out.println("Sensorvalue published to " + topic + "!");
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
	
	public void addTopic(String topic){
		
		for (String t:topics){
			if (t == topic)
				System.out.println("Topic is already in the distributionlist.");
			else
				topics.add(topic);
		}
	}
	
	public void removeTopic(String topic){
		for (String t:topics){
			if (t == topic)
				topics.remove(topics.indexOf(t));
			else
				System.out.println("No such topic to remove.");
		}
	}
}
