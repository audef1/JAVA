package GUI;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import Controller.Broker;
import Controller.FXMLSubController;
import Controller.Subscriber;
import Model.Datastore;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SubscriberGUI extends Stage implements Observer{

	private FXMLLoader loader;
	private FXMLSubController fxmlController;
	public SubscriberGUI(Subscriber sub, Datastore store) throws IOException{
		sub.addObserver(this);
		store.addObserver(this);
		
		fxmlController = new FXMLSubController(sub);
		loader = new FXMLLoader(getClass().getResource("/application/Subscriber.fxml"));
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
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("i got notified.");
		
	}
}