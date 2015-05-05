package application;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer implements Runnable{

	private Stopwatch stopwatch;
	private double time;
	private boolean isRunning;
	
	private long starttime;
	private SimpleDateFormat df = new SimpleDateFormat("mm:ss.SS");
	
	public Timer(){

	}

	public void attach(Stopwatch s){
		this.stopwatch = s;
	}
	
	public double getTime(){
		return Double.parseDouble(df.format(new Date((long) time)));
	}
	
	public String getTimeString(){
		return df.format(new Date((long) time));
	}
	
	public boolean isRunning(){
		return isRunning;
	}
	
	@Override
	public void run() {
		while (isRunning){
			try {
				time = System.currentTimeMillis() - starttime;
				stopwatch.update();
				wait(10);
			} catch (InterruptedException e) {
				isRunning = false;
				e.printStackTrace();
			}
		}
	}
	
	public void stop(){
		isRunning = false;
	}
	
	public void reset(){
		time = 0;
		starttime = new Date().getTime();
	}
	
	public void start(){
		isRunning = true;
		starttime = new Date().getTime();
		run();
	}

}
