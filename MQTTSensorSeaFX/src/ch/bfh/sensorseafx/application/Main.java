package ch.bfh.sensorseafx.application;
	
import java.io.IOException;

import ch.bfh.sensorseafx.controller.Subscriber;
import ch.bfh.sensorseafx.gui.SubscriberGUI;
import ch.bfh.sensorseafx.model.Datastore;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage Stage) {
		Datastore store = new Datastore();
		Subscriber sub = new Subscriber();
		try {
			SubscriberGUI gui = new SubscriberGUI(sub, store);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}