package ch.bfh.fbi.audef1.Messenger.io;

import java.io.StreamCorruptedException;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class nothredreciever {

	private Serialiser ser = new Serialiser();
	private MqttClient mqttClient;

	public nothredreciever() throws MqttSecurityException, MqttException {
		mqttClient = new MqttClient("tcp://m20.cloudmqtt.com:13575", "test");

		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(false);
		options.setUserName("kcrcqfpu");
		options.setPassword("oRfK8H93cXj9".toCharArray());
		mqttClient.connect(options);
		// mqttClient.subscribe("ch/bfh/fbi/talk/+");
		mqttClient.subscribe("test");
	}

	public void connectionLost(Throwable throwable) {

	}

	public void messageArrived(String string, MqttMessage mm) throws Exception,
			StreamCorruptedException {
		System.out.printf("From (%s):", string);
		Object o = ser.deserialize(mm.getPayload());
		System.out.println(o);
	}

	public void deliveryComplete(IMqttDeliveryToken imdt) {

	}

}
