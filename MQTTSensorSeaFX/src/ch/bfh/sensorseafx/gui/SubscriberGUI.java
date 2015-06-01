package ch.bfh.sensorseafx.gui;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import ch.bfh.sensorseafx.controller.FXMLSubController;
import ch.bfh.sensorseafx.controller.Subscriber;
import ch.bfh.sensorseafx.model.Datastore;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SubscriberGUI extends Stage{

	private FXMLLoader loader;
	private FXMLSubController fxmlController;
	public SubscriberGUI(Subscriber sub, Datastore store) throws IOException{
		
		fxmlController = new FXMLSubController(sub, store);
		loader = new FXMLLoader(getClass().getResource("/ch/bfh/sensorseafx/application/Subscriber.fxml"));
		loader.setController(fxmlController);	
		
		Scene scene;
		
		try {
			scene = new Scene(loader.load());
			this.setScene(scene);
			this.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}