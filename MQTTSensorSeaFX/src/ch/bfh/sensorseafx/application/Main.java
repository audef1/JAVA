package ch.bfh.sensorseafx.application;
	
import java.io.IOException;

import ch.bfh.sensorseafx.controller.Publisher;
import ch.bfh.sensorseafx.controller.Subscriber;
import ch.bfh.sensorseafx.gui.SubscriberGUI;
import ch.bfh.sensorseafx.model.Datastore;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	
	@Override
	public void start(Stage Stage) throws IOException {
		Datastore store = new Datastore();
		Subscriber sub = new Subscriber();
		Publisher pub = new Publisher();
		
			//create the actual view and link it to the model and controller
			
			SubscriberGUI sgui = new SubscriberGUI(sub, store);
			//PublisherGUI pgui = new PublisherGUI(pub, store);
			
			//add close function
			Stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override public void handle(WindowEvent t) {
			        System.out.println("CLOSING");
			    }
			});
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}