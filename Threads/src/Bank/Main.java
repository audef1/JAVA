package Bank;

public class Main {

	public static void main(String[] args) {
		
		Account n = new Account(5000);
		
		Customer c1 = new Customer(n);
		Lohn l1 = new Lohn(n);
		c1.start();
		l1.start();
	}
}