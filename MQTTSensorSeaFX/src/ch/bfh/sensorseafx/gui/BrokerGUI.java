package ch.bfh.sensorseafx.gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ch.bfh.sensorseafx.controller.Broker;
import ch.bfh.sensorseafx.controller.FXMLBrokerController;

public class BrokerGUI extends Stage {

	private FXMLLoader loader;
	private FXMLBrokerController fxmlController;
	
	public BrokerGUI(Broker broker) throws IOException{
		
		fxmlController = new FXMLBrokerController(broker);
		loader = new FXMLLoader(getClass().getResource("/ch/bfh/sensorseafx/application/Broker.fxml"));
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
