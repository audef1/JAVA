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
		pub.addTopic("test");
		
		Subscriber sub = new Subscriber(bro);
		sub.subscribe("test");
		sub.subscribe("test");
		sub.start();
		
		Sensor s = new TempSensor("Wohnzimmer",pub);
		s.setInterval(1000);
		s.start();
		
		//Sensor t = new TempSensor("Schlafzimmer",p);
		//t.setInterval(5000);
		//t.start();
	}
}