package Dice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import javax.xml.validation.Schema;

import Marshaller.XMLHelper;

public class Main {
	public static void main(String[] args) {
		
		try {
			Dice d = new Dice(0,6);
			
			for (int i = 0; i < 100; i++){
				d.play();
				//System.out.println(d);
			}
			
			byte[] array;
			ByteArrayOutputStream os = new ByteArrayOutputStream();	
			try {
				
				XMLHelper.saveInstance(os, d);
				array = os.toByteArray();
				Scanner s = new Scanner(new ByteArrayInputStream(array));
				while(s.hasNextLine())
					System.out.println(s.nextLine());
				s.close();
				
				//Write Dice to File
				Writer out = new FileWriter("Dice");
				XMLHelper.saveInstance(out, d);
				out.close();
				
				//load Dice from file
				Reader in = new FileReader("Dice");
				Object o = XMLHelper.loadInstance(in, Dice.class);
				Dice loadedDice = (Dice) o;
				
				System.out.println(Arrays.toString(loadedDice.getStatistics()));
				
				//xml schema erzeugen von der Klasse Dice
				XMLHelper.saveSchema(new File("."), Dice.class);

				//schema erneut zu einer klasse umwandeln mit xjc
				// bsp: xjc -p meinwuerfel ../schema1.xsd
				// erstellt ein neues package meinwuerfel mit der klasse Dice
				
				//FileOutputStream fout = new FileOutputStream("test.xml"); //auch möglich
				FileWriter fout = new FileWriter("test.xml");
				XMLHelper.saveInstance(fout, new URL("http://ch.fbi.xml.beispielEins"), "schema1.xsd", d);
				fout.close();
				
				FileReader fin = new FileReader("test.xml");
				String schemaName = "schema1.xsd";
				Schema schema = XMLHelper.loadSchema((new File(schemaName).toURI().toURL()));
				Dice diiiice = (Dice) XMLHelper.loadInstance(fin, schema, Dice.class);
				
				diiiice.play();
				System.out.println(Arrays.toString(diiiice.getStatistics()));
				
			} catch (Exception e) {
				e.printStackTrace();
			}			
		} catch (DiceException e) {
			e.printStackTrace();
		}
	}
}
