package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Platform;

public class Timer implements Runnable{

	private Stopwatch stopwatch;
	private double time;
	private boolean isRunning;
	
	private long starttime;
	private SimpleDateFormat df = new SimpleDateFormat("mm:ss:S");
	
	public Timer(Stopwatch s){
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
		isRunning = true;
		starttime = new Date().getTime();
		while (isRunning){
			time = System.currentTimeMillis() - starttime;
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Platform.runLater(() -> {
				stopwatch.update();
				//System.out.println(getTimeString());
			});

		}
	};
	
	public void stop(){
		isRunning = false;
	}
	
	public void reset(){
		time = 0;
		starttime = new Date().getTime();
	}

}
