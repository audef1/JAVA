package application;
	
import Broker.Broker;
import Publisher.Publisher;
import Sensors.Sensor;
import Sensors.TempSensor;
import Subscriber.Subscriber;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {			
			BorderPane root = new BorderPane();
			root.setAlignment(root, Pos.CENTER);
			
			Button b = new Button("GO!");
			root.setCenter(b);
			b.setOnAction(this::buttonAction);
			
			
			
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void buttonAction(ActionEvent event){
		Broker bro = new Broker("TempSensors");
		bro.connect("remote.floeggu.ch", 1883, "florian", "12345678");
		
		Publisher pub = new Publisher(bro);
		pub.addTopic("test");
		pub.addTopic("blaa");
		pub.setNotify(true);

		Subscriber sub = new Subscriber(bro);
		sub.subscribe("test");
		sub.setArraySize(10);
		sub.setNotify(true);
		sub.start();
		
		Sensor sen = new TempSensor("Wohnzimmer",pub);
		sen.setInterval(500);
		sen.start();
	}
	
}
