package ch.bfh.fbi.audef1.Messenger;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttException;

import ch.bfh.fbi.audef1.Messenger.io.MqttChatReciever;
import ch.bfh.fbi.audef1.Messenger.io.MqttChatSender;

public class Main {
	public static void main(String[] args) throws MqttException, IOException, InterruptedException {
		MqttChatSender s = new MqttChatSender();
		MqttChatReciever r = new MqttChatReciever();
		
		s.setDaemon(true);
		r.setDaemon(true);
		
		r.start();
		s.start();
		
		System.in.read();
	}
}
