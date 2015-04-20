package ch.bfh.fbi.audef1.Messenger.io;

import java.io.IOException;
import java.io.StreamCorruptedException;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttChatReciever extends Thread {

	private Serialiser ser = new Serialiser();
	
	public MqttChatReciever() throws MqttException, IOException, InterruptedException, StreamCorruptedException{
		
	}
	
	public void run(){
		
		MqttClient mqttClient;
		
		try {
			mqttClient = new MqttClient("tcp://remote.floeggu.ch:1883", "test");
			//mqttClient = new MqttClient("tcp://iot.eclipse.org:1883", "ch.bfh.fbi.audef1.listener");
		    mqttClient.setCallback(new MqttCallback() {
	
	    	@Override
	    	public void connectionLost(Throwable throwable) {
	    		
	    	}
	
	    	@Override
	    	public void messageArrived(String string, MqttMessage mm) throws Exception, StreamCorruptedException{
			   	System.out.printf("From (%s):", string);
			   	Object o = ser.deserialize(mm.getPayload());
			   	System.out.println(o);
		    }
	
	    	@Override
	    	public void deliveryComplete(IMqttDeliveryToken imdt) {
	    		
	    	}
	    });
	       
	    MqttConnectOptions options = new MqttConnectOptions();
	    options.setCleanSession(false);
	    mqttClient.connect(options);
	    //mqttClient.subscribe("ch/bfh/fbi/talk/+");
	    mqttClient.subscribe("test");
	    
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
