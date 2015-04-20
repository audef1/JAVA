package Scanner;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class MiS {

	public static void main(String[] args) {
		try {
		URL webPage;
		Scanner s;
			webPage = new URL("http://www.heise.de");
			s = new Scanner(new BufferedInputStream(webPage.openStream()));
			
			while ((s.hasNextLine())) {
				System.out.println(s.nextLine());
			}
			s.close();
			byte[] bs = new String("Hello").getBytes();
			Scanner f = new Scanner(new ByteArrayInputStream(bs));
			String x = f.next();
			System.out.println(x);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

