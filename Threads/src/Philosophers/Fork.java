package Philosophers;

//Fork pool used by the philosophers
class Forks {
	boolean[] fork = new boolean[5]; // initially false, i.e. not used

	// Try to pick up the forks with the designated numbers
	synchronized void get(int left, int right) {
		try {
			while (fork[left] || fork[right]) wait();
		} catch (Exception e){
			return;
		}
		fork[left] = true;
		fork[right] = true;
	}

	// Lay down the forks with the designated numbers
	synchronized void put(int left, int right) {
		fork[left] = false;
		fork[right] = false;
		notifyAll();
	}
}