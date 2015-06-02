package Application;

import Broker.Broker;
import Datastore.Datastore;
import Publisher.Publisher;
import Sensors.Sensor;
import Sensors.TempSensor;
import Subscriber.Subscriber;

public class Main {

	public static void main(String[] args) {
		
		Datastore store = new Datastore();
		
		Broker bro = new Broker("Sensors");
		//bro.connect("remote.floeggu.ch", 1883, "florian", "12345678");
		bro.connect("m20.cloudmqtt.com", 13575, "kcrcqfpu", "oRfK8H93cXj9");
		
		Publisher pub = new Publisher(bro);
		pub.addTopic("test");
		pub.setSysout(true);

		Sensor sen1 = new TempSensor("Wohnzimmer",pub);
		sen1.setInterval(3000);
		sen1.setDaemon(false);
		sen1.start();
		
		Subscriber sub = new Subscriber(bro, store);
		sub.subscribe("test");
		
		sub.setDaemon(false);
		sub.setSysout(true);
		sub.start();
		
		
//		Sensor sen2 = new LightSensor("Schlafzimmer",pub);
//		sen2.setInterval(500);
//		sen2.setDaemon(false);
//		sen2.start();
	}
}