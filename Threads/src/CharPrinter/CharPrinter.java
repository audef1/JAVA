package CharPrinter;

import java.util.Random;

public class CharPrinter extends Thread{

	
	private static Random randomGen;
	
	static {
		randomGen = new Random();
	}
	
	// dynamic Object declarations
	private char showChar;
	private int delayRange;
	
	public CharPrinter(char inChar, int inDelayRange){
		this.showChar = inChar;
		this.delayRange = inDelayRange;
	}
	
	public void run(){
		int delay;
		for (int i = 0; i < 5; i++){
			System.out.println(showChar);
			delay = randomGen.nextInt(this.delayRange);
			try{
				sleep(delay);
			} catch (Exception e){
				System.out.println(Thread.currentThread().getName());
				return;
			}
		}
	}
}
