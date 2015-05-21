package GUI;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import Controller.Broker;
import Controller.Subscriber;
import Model.Datastore;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SubscriberGUI extends Stage implements Observer{

	private Parent root;
	private Subscriber subscriber;
	private Datastore datastore;
	
	public SubscriberGUI(Subscriber sub, Datastore store){
		this.subscriber = sub;
		this.datastore = store;
		
		try {
			root = FXMLLoader.load(getClass().getResource("/application/Subscriber.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		Scene scene = new Scene(root);
		
		this.setScene(scene);
		this.show();
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}
}