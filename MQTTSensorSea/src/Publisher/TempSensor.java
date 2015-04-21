package Publisher;

import java.util.Date;

public class TempSensor extends Sensor{

	private int temp;
	
	public TempSensor(String id){
		super.setTimestamp(new Date().getTime());
		super.setSourceID(id);
	}

	@Override
	public void getValues() {
	
		//connect to device with sensorID and get value 
		temp = 10;
		
	}
	
	@Override
	public void export() {
		//serialize object and call publisher.publish();
		
	}	
}