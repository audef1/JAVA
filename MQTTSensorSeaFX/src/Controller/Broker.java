package Controller;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Broker {

	private String clientID = "undefined";
	
	private MqttClient client;
	private MqttConnectOptions connOpt = new MqttConnectOptions();
	
	public Broker(){
		
	}
	
	public Broker(String clientID){
		this.clientID = clientID;
	}
	
	public void connect(String host, int port, String username, String password){
		try {
			if (client == null){
				client = new MqttClient("tcp://" + host + ":" + port + "", clientID);
				connOpt.setCleanSession(false);
				connOpt.setUserName(username);
				connOpt.setPassword(password.toCharArray());
	            	System.out.println("Connecting to broker: "+ host);
				client.connect(connOpt);
					System.out.println("Connected!");
			}
			else
			{
				System.out.println("Connection to " + client.getServerURI() + " already established.");
			}
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void connect(String host, String username, String password){
		try {
			if (client == null){
				client = new MqttClient("tcp://" + host + ":1883", clientID);
				connOpt.setCleanSession(false);
				connOpt.setUserName(username);
				connOpt.setPassword(password.toCharArray());
	            System.out.println("Connecting to broker: "+ host);
				client.connect(connOpt);
				System.out.println("Connected!");
			}
			else
			{
				System.out.println("Connection to " + client.getServerURI() + " already established.");
			}
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void connect(String host){
		try {
			if (client == null){
				client = new MqttClient("tcp://" + host + ":1883", clientID);
				System.out.println("Connecting to broker: "+ host);
				connOpt.setCleanSession(false);
				client.connect(connOpt);
				System.out.println("Connected!");
			}
			else
			{
				System.out.println("Connection to " + client.getServerURI() + " already established.");
			}
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void connect(String host, int port){
		try {
			client = new MqttClient("tcp://" + host + ":" + port + "", clientID);
			System.out.println("Connecting to broker: "+ host);
			client.connect(connOpt);
			System.out.println("Connected!");
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect(){
		try {
			client.disconnect();
			System.out.println("Disconnected!");
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public MqttClient getClient(){
		if (client != null)
			return client;
		return client;
	}
	
	public boolean isConnected(){
		if (client == null)
			return false;
		else
			return client.isConnected();	
	}
	
}
