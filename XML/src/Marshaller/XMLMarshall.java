package Marshaller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.Scanner;

public class XMLMarshall {

	public static void main(String[] args) {
		
		byte[] array;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		try {
			Writer w = new FileWriter("test");
			TimeUnit unit1 = new TimeUnit("blaa");
			//XMLHelper.saveInstance(os, unit1);
			XMLHelper.saveInstance(w, unit1);
			w.close();
			//os.close();
			//array = os.toByteArray();
			//InputStream in = new ByteArrayInputStream(array);
			Reader in = new FileReader("test");
			Scanner s = new Scanner(in);
			while (s.hasNextLine())
				System.out.println(s.nextLine());
			s.close();	 //wichtig! scanner immer schliessen
			
			in = new FileReader("test");
			Object o = XMLHelper.loadInstance(in, TimeUnit.class);
			TimeUnit t2 = (TimeUnit) o;
			System.out.println(t2);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
