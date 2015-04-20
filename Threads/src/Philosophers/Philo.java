package Philosophers;

//A thread representing a dining philosopher
class Philo extends Thread {
	int n;
	int sleepDelay, eatDelay;
	int left, right;
	Forks forks;
	GUI g;

	Philo (int n, int sleepDelay, int eatDelay, Forks forks, GUI g) {
		this.n = n;
		this.sleepDelay = sleepDelay; this.eatDelay = eatDelay;
		this.forks = forks;
		this.g = g;
		left = n;
		right = (n+1) % 5;
	}

	public void run() {
		while(true){
			try {
				sleep(sleepDelay);
				forks.get(left, right);
				System.out.println("Philo " + n + " is eating...");
				 // set color green
					g.setGreen(n);
				sleep(eatDelay);
  				forks.put(left, right);
				 // set color red
					g.setRed(n);
			} catch (Exception e) { return; }
		}
	}

}