package Philosophers;

public class Main {
	
	public static void main (String[] arg) {
		
		GUI g = new GUI();
		Forks forks = new Forks();
		
		new Philo(0, 1000, 2000, forks, g).start();
		new Philo(1, 2000, 1000, forks, g).start();
		new Philo(2, 3000, 1500, forks, g).start();
		new Philo(3, 4000, 2000, forks, g).start();
		new Philo(4, 5000, 1500, forks, g).start();
	}
}