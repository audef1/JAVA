package Bank;

public class Lohn extends Thread{

	private Account n;
	
	public Lohn(Account n){
		this.n = n;
	}
	
	public void run(){
			try {
				while(true){
					sleep(30000);
					n.deposit(5000);
					System.out.println("Lohn ist ausbezahlt worden. Neuer Kontostand: " + n.getBalance());
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}