package ch.bfh.sensorseafx.gui;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import ch.bfh.sensorseafx.controller.FXMLPubController;
import ch.bfh.sensorseafx.controller.FXMLSubController;
import ch.bfh.sensorseafx.controller.Publisher;
import ch.bfh.sensorseafx.controller.Subscriber;
import ch.bfh.sensorseafx.model.Datastore;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PublisherGUI extends Stage {

	private FXMLLoader loader;
	private FXMLPubController fxmlController;
	
	public PublisherGUI(Publisher pub, Datastore store) throws IOException{
		
		fxmlController = new FXMLPubController(pub, store);
		loader = new FXMLLoader(getClass().getResource("/ch/bfh/sensorseafx/application/Publisher.fxml"));
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
