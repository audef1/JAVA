package ch.bfh.sensorseafx.application;
	
import java.io.IOException;

import ch.bfh.sensorseafx.controller.Publisher;
import ch.bfh.sensorseafx.controller.Subscriber;
import ch.bfh.sensorseafx.gui.PublisherGUI;
import ch.bfh.sensorseafx.gui.SubscriberGUI;
import ch.bfh.sensorseafx.model.Datastore;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Main extends Application {
	
	private Datastore store = new Datastore();
	private Subscriber sub = new Subscriber();
	private Publisher pub = new Publisher();
	
	@Override
	public void start(Stage Stage) throws IOException {
		
			//create the actual view and link it to the model and controller
			SubscriberGUI sgui = new SubscriberGUI(sub, store);
			PublisherGUI pgui = new PublisherGUI(pub, store);
			
			//add close function
			Stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override public void handle(WindowEvent t) {
			        System.out.println("CLOSING");
			        System.exit(0);
			    }
			});
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}