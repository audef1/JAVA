package Publisher;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import ch.Message;

public class Serialiser{

	public byte[] serialize(Message m) throws IOException, ClassNotFoundException{
		//System.out.println(">" + m.toString());
		
		// beim serialisieren immer oos nehmen anstelle von PrintWriter!
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream aos = new ObjectOutputStream(baos);  
		aos.writeObject(m);
		aos.close();
		
		byte[] bytes = baos.toByteArray();
		
		return bytes;
	}
	
	public Object deserialize(byte[] bytes){
		Object o=null;
		//Das serialisierte ByteArray wieder einlesen
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
