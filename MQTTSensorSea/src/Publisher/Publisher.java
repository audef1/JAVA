package Publisher;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Publisher {

	private String host;
	private String topic;
	private String username;
	private String password;
	private int port;
	private MqttClient mqttClient;
	
	public Publisher(){
		
	}
	
	public void run(){
	    
	}
	
	public void connect(String host, String topic, int port, String username, String password){
		try {
			mqttClient = new MqttClient("tcp://" + host + ":" + port + "", topic);
			mqttClient.connect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void connect(String host, String topic){
		try {
			mqttClient = new MqttClient("tcp://" + host + ":1883", topic);
			mqttClient.connect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void connect(String host, String topic, int port){
		try {
			mqttClient = new MqttClient("tcp://" + host + ":" + port + "", topic);
			mqttClient.connect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect(){
		try {
			mqttClient.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	private void publish(String message){
		
	}
	
}
