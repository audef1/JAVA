package application;
	
import Controller.TimeController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage Stage) {
		try {

			Timer t = new Timer();
			TimeController tc = new TimeController(t);
			StopwatchGUI s = new StopwatchGUI(tc, t);
			StopwatchGUI sw = new StopwatchGUI(tc, t);
			StopwatchGUI s2 = new StopwatchGUI(tc, t);
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
