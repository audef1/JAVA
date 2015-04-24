package Application;

import Broker.Broker;
import Publisher.Publisher;
import Sensors.Sensor;
import Sensors.TempSensor;
import Subscriber.Subscriber;

public class Main {

	public static void main(String[] args) {
		
		Broker b = new Broker("Temperatur-Out");
		b.connect("remote.floeggu.ch", 1883, "florian", "12345678");
		Publisher p = new Publisher(b);
		p.addTopic("test");
		
		Sensor s = new TempSensor("Wohnzimmer",p);
		s.setInterval(2000);
		
		Sensor t = new TempSensor("Schlafzimmer",p);
		t.setInterval(5000);
		
		Subscriber sub = new Subscriber(b);
		sub.subscribe("test");
		sub.start();
		
		s.start();
		t.start();
		

	}
}