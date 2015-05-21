package Helpers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Sensors.Sensor;

public class Serialiser{

	public byte[] serialize(Sensor s) throws IOException, ClassNotFoundException{
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream aos = new ObjectOutputStream(baos);  
		aos.writeObject(s);
		aos.close();
		
		byte[] bytes = baos.toByteArray();
		
		return bytes;
	}
	
	public Object deserialize(byte[] bytes){
		Object o = null;

		InputStream is = new ByteArrayInputStream(bytes);
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(is);
			o = ois.readObject();
			ois.close();

		} catch (IOException e) {
			o=new String(bytes);
		} catch (ClassNotFoundException e) {
			o=new String(bytes);
		}
		
		return o;
	}
}
