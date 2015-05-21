package application;
	
import Controller.Subscriber;
import GUI.SubscriberGUI;
import Model.Datastore;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage Stage) {
		Datastore store = new Datastore();
		Subscriber sub = new Subscriber();
		SubscriberGUI gui = new SubscriberGUI(sub, store);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}