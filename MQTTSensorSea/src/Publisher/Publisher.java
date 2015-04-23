package Publisher;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import Application.Serialiser;

public class Publisher {

	private String clientID = "publisher";
	private String host ;
	private String topic;
	private String username;
	private String password;
	private int port;
	private MqttClient mqttClient;
	private MqttConnectOptions connOpt = new MqttConnectOptions();
	
	private Serialiser ser = new Serialiser();
	
	public Publisher(){
		
	}
	
	public Publisher(String clientID){
		this.clientID = clientID;
	}
		
	public void connect(String host, String topic, int port, String username, String password){
		try {
			this.topic = topic;
			
			if (mqttClient == null){
				mqttClient = new MqttClient("tcp://" + host + ":" + port + "", clientID);
				connOpt.setCleanSession(true);
				connOpt.setUserName(username);
				connOpt.setPassword(password.toCharArray());
	            	System.out.println("Connecting to broker: "+ host);
				mqttClient.connect(connOpt);
					System.out.println("Connected!");
			}
			else
			{
				System.out.println("Connection to " + mqttClient.getServerURI() + " already established.");
			}
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void connect(String host, String topic, String username, String password){
		try {
			if (mqttClient == null){
				this.topic = topic;
				mqttClient = new MqttClient("tcp://" + host + ":1883", clientID);
				connOpt.setCleanSession(true);
				connOpt.setUserName(username);
				connOpt.setPassword(password.toCharArray());
	            	System.out.println("Connecting to broker: "+ host);
				mqttClient.connect(connOpt);
					System.out.println("Connected!");
			}
			else
			{
				System.out.println("Connection to " + mqttClient.getServerURI() + " already established.");
			}
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void connect(String host, String topic){
		try {
			if (mqttClient == null){
				this.topic = topic;
				mqttClient = new MqttClient("tcp://" + host + ":1883", clientID);
					System.out.println("Connecting to broker: "+ host);
				mqttClient.connect();
					System.out.println("Connected!");
			}
			else
			{
				System.out.println("Connection to " + mqttClient.getServerURI() + " already established.");
			}
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void connect(String host, String topic, int port){
		try {
			mqttClient = new MqttClient("tcp://" + host + ":" + port + "", clientID);
			mqttClient.connect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect(){
		try {
			mqttClient.disconnect();
			System.out.println("Disconnected!");
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void publish(Sensor s){
		try {
			byte[] bytes = ser.serialize(s);
			MqttMessage message = new MqttMessage(bytes);
			try {
				mqttClient.publish(topic, message);
				System.out.println("Sensorvalue published!");
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
}
