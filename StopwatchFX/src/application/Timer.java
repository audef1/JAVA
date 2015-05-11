package application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;

import javafx.application.Platform;

public class Timer extends Observable implements Runnable{

	private StopwatchGUI gui;
	private boolean isRunning;
	private boolean isReset;
	
	private double time;
	private long inittime;
	private long elapsed;
	private SimpleDateFormat df = new SimpleDateFormat("mm:ss:SS");
	
	public Timer(){
		
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
	
	public boolean isReset(){
		return isReset;
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
				isRunning = false;
				e.printStackTrace();
			}	
			
			//update the gui via notifications to the observers
			this.setChanged();
			this.notifyObservers();
		}
	};
	
	public void start(){
		Thread t = new Thread(this);
		t.setDaemon(false);
		t.start();
		isReset = false;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void stop(){
		isRunning = false;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void reset(){
		isReset = true;
		time = 0;
		elapsed = 0;
		inittime = new Date().getTime();
		this.setChanged();
		this.notifyObservers();
	}

}
