package Streams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

public class FileDemo {

	public static void main(String[] args) throws IOException {
		
		File path = new File("hello.txt");
		OutputStream fos = new FileOutputStream(path);

		String s = "Hallo dies ist ein Test!";
		fos.write(s.getBytes());
		fos.close();
		
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		
		String input = reader.readLine();
		reader.close();
		
		System.out.println("Input: " + input);
		
		URL url = new URL("http://www.gutenberg.org/cache/epub/76/pg76.txt");
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(url.openStream()));
	
		String line = reader2.readLine();
		
		while (line != null){
			System.out.println(reader2.readLine());
			line = reader2.readLine();
		}
		
		reader2.close();
			
	}

}
