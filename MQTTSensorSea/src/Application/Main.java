package Application;

import Publisher.Publisher;
import Publisher.Sensor;
import Publisher.TempSensor;

public class Main {

	public static void main(String[] args) {
		Publisher p = new Publisher("Temperatur");
		p.connect("remote.floeggu.ch", "test", 1883, "florian", "12345678");
		
		Sensor s = new TempSensor("Wohnzimmer",p);
		s.setInterval(2000);
		
		Sensor t = new TempSensor("Schlafzimmer",p);
		t.setInterval(5000);
		
		s.start();
		t.start();
	}
}