package Bank;

import java.util.Random;

public class Customer extends Thread{

	private Account n;
	
	public Customer(Account n){
		this.n = n;
	}
	
	public void run(){
			try {
				while(true){
					sleep(300);
					int i = (int)((Math.random()) * 100 + 1);
					n.withdraw(i);
					System.out.println("Hebe " + i + "CHF ab. Neuer Kontostand: " + n.getBalance() + "CHF.");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}