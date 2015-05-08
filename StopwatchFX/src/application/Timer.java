package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Platform;

public class Timer implements Runnable{

	private Stopwatch stopwatch;
	private boolean isRunning;
	
	private double time;
	private long inittime;
	private long elapsed;
	private SimpleDateFormat df = new SimpleDateFormat("mm:ss:S");
	
	public Timer(Stopwatch s){
		this.stopwatch = s;
	}

	public double getTime(){
		return Double.parseDouble(df.format(new Date((long)time + elapsed)));
	}
	
	public String getTimeString(){
		return df.format(new Date((long)time + elapsed));
	}
	
	public boolean isRunning(){
		return isRunning;
	}
	
	@Override
	public void run() {
		isRunning = true;
		inittime = new Date().getTime() - elapsed;
		while (isRunning){
			elapsed = System.currentTimeMillis() - inittime;
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
		elapsed = 0;
		inittime = new Date().getTime();
	}

}
