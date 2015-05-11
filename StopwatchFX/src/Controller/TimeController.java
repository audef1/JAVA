package Controller;

import application.Timer;

public class TimeController {

	private Timer timer;
	
	public TimeController(Timer timer){
		this.timer = timer;
	}
	
	public void start(){
		this.timer.start();
	}
	
	public void stop(){
		this.timer.stop();
	}
	
	public void reset(){
		this.timer.reset();
	}
	
	public Timer getTimer(){
		return timer;
	}
	
}
