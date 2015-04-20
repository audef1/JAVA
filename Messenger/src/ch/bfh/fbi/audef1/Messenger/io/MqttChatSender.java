package ch.bfh.fbi.audef1.Messenger.io;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import ch.Message;

public class MqttChatSender extends Thread{
	
	private Serialiser ser = new Serialiser();
	
	public MqttChatSender() throws MqttException, InterruptedException {
		
	}
	
	public void run(){
		MqttClient mqttClient;
		try {
			mqttClient = new MqttClient("tcp://remote.floeggu.ch:1883", "test");
		mqttClient.connect();
	    while (true) {
	    	String payloadString = JOptionPane.showInputDialog("Talk");
	    	if (payloadString == null) {
	    		break;
	    	}

	    	byte[] bytes = ser.serialize(new Message(payloadString, "florian"));
	    	
	    	MqttMessage message = new MqttMessage(bytes);
	    	mqttClient.publish("test", message);
	    }
	    mqttClient.disconnect();
		} catch (MqttException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}