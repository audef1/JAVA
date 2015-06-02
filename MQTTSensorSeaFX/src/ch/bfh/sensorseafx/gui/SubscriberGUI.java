package ch.bfh.sensorseafx.gui;

import java.io.IOException;

import ch.bfh.sensorseafx.controller.Broker;
import ch.bfh.sensorseafx.controller.FXMLSubController;
import ch.bfh.sensorseafx.controller.Publisher;
import ch.bfh.sensorseafx.controller.Subscriber;
import ch.bfh.sensorseafx.model.Datastore;
import ch.bfh.sensorseafx.sensors.Sensor;
import ch.bfh.sensorseafx.sensors.TempSensor;
import ch.bfh.sming.service.Txw51Service;
import ch.bfh.sming.service.Txw51ServiceImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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