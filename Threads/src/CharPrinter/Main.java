package CharPrinter;

public class Main {

	public static void main(String[] args) {
		CharPrinter thread1 = new CharPrinter('+', 5000);
		CharPrinter thread2 = new CharPrinter('-', 3000);
		
		thread1.setName("One");
		thread2.setName("Two");
		
		thread1.start();
		thread2.start();
		
		try{
			thread1.join();
			thread2.join();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Fertig.");
	}
}