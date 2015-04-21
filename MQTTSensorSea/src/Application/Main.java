package Application;

import Publisher.Publisher;
import Publisher.TempSensor;

public class Main {

	public static void main(String[] args) {
		
		Publisher p = new Publisher("sensor1");
		p.connect("remote.floeggu.ch", "test", "florian", "12345678");
		//hier ein odere mehrere threads welche die sensoren abfragen und diese danach exportieren / an den publisher senden.
		p.publish(new TempSensor("Wohnzimmer"));
		p.disconnect();
	}

}
