package Application;

import Broker.Broker;
import Publisher.Publisher;
import Sensors.Sensor;
import Sensors.TempSensor;
import Subscriber.Subscriber;

public class Main {

	public static void main(String[] args) {
		
		Broker bro = new Broker("TempSensors");
		bro.connect("remote.floeggu.ch", 1883, "florian", "12345678");
		
		Publisher pub = new Publisher(bro);
		pub.addTopic("test");
		pub.addTopic("blaa");
		pub.setNotify(false);

		Subscriber sub = new Subscriber(bro);
		sub.subscribe("test");
		sub.setArraySize(10);
		sub.setNotify(true);
		sub.start();
		
		Sensor sen = new TempSensor("Wohnzimmer",pub);
		sen.setInterval(500);
		sen.start();
		
		//Sensor t = new TempSensor("Schlafzimmer",pub);
		//t.setInterval(5000);
		//t.start();
	}
}