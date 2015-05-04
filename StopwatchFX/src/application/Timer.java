package application;

public class Timer implements Runnable{

	private Stopwatch stopwatch;
	private double time;
	private boolean isRunning;
	
	public Timer(){

	}

	public void attach(Stopwatch s){
		this.stopwatch = s;
	}
	
	public double getTime(){
		return time;
	}
	
	public String getTimeString(){
		return Double.toString(time);
	}
	
	public boolean isRunning(){
		return isRunning;
	}
	
	@Override
	public void run() {
		while (isRunning){
			stopwatch.update();
		}
	}
	
	public void start(){
		isRunning = true;
		
	}
	
	public void stop(){
		isRunning = false;
	}
	
	public void reset(){
		
	}
	
}
