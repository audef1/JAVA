package application;
	
import Broker.Broker;
import Datastore.Datastore;
import Publisher.Publisher;
import Sensors.Sensor;
import Sensors.TempSensor;
import Subscriber.Subscriber;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	
	private XYChart.Series sensor1 = new XYChart.Series();
	private LineChart<Number,Number> lineChart;
	
	@Override
	public void start(Stage primaryStage) {
		
		try {			
			BorderPane root = new BorderPane();
			root.setAlignment(root, Pos.CENTER);
			
			Button b = new Button("Connect");
			root.setCenter(b);
			b.setOnAction(this::connect);
			
			Scene scene = new Scene(root,400,400);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void connect(ActionEvent event){
		
		Datastore store = new Datastore();
		
		Broker bro = new Broker("TempSensors");
		bro.connect("remote.floeggu.ch", 1883, "florian", "12345678");
		
		Publisher pub = new Publisher(bro);
		pub.addTopic("test");
		pub.addTopic("blaa");
		pub.setNotify(false);

		Subscriber sub = new Subscriber(bro, store);
		sub.subscribe("test");
		sub.setNotify(true);
		sub.start();
		
		Sensor sen = new TempSensor("Wohnzimmer",pub);
		sen.setInterval(500);
		sen.start();
		
		ObservableList<Sensor> sensordata = FXCollections.observableArrayList(store.getDatastore());
		
		sensordata.addListener(new ListChangeListener<Sensor>(){
		    public void onChanged( javafx.collections.ListChangeListener.Change<? extends Sensor> s){
		      while (s.next()) {
		    	int x = (int) s.getAddedSubList().get(0).getValues().get(0);
		    	int y = (int) s.getAddedSubList().get(0).getValues().get(1);
		        sensor1.getData().add(new XYChart.Data(x,x));
		        lineChart.getData().add(sensor1);
		      }
		    }
		  }
		);
	}
}
