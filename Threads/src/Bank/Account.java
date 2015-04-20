package Bank;

public class Account {

	private int balance;

	public Account(int i) {
		this.balance = i;
	}

	synchronized void deposit(int amount) {
		this.balance = this.balance + amount;
		notify();
	}

	synchronized void withdraw(int amount) { // methoden synchronisieren ->
												// locks werden überflüssig.
		try {
			while (balance < amount)
				wait();
		} catch (InterruptedException e) {
			return;
		}
		this.balance = this.balance - amount;
	}

	public int getBalance() {
		return balance;
	}
}