package ch.bfh.sensorseafx.controller;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import ch.bfh.sensorseafx.model.Datastore;
import ch.bfh.sensorseafx.sensors.Sensor;
import ch.bfh.sensorseafx.sensors.TempSensor;

public class FXMLPubController implements Observer {

	private Publisher pub;
	private Datastore store;
	
	public FXMLPubController(Publisher pub, Datastore store){
		this.pub = pub;
		this.pub.setDebug(true);
		this.pub.getSubscriberList().addObserver(this);
		this.pub.getSensorList().addObserver(this);
		this.pub.getBroker().addObserver(this);
		
		this.store = store;
		this.store.addObserver(this);
		
		pub.setPeriod(Duration.seconds(20));
		pub.start();
	}

	@FXML
    private Button btnAddTopic;
    
	@FXML
    private Button btnRemoveTopic;
	
    @FXML
    private Button btnQuickConnect;
	
    @FXML
    private Button btnSearchDevices;
    
	@FXML
    private Button btnConnectDevice;
    
    @FXML
    private TextField inputHost;
   
    @FXML
    private TextField inputPort;
    
    @FXML
    private TextField inputUser;
    
    @FXML
    private TextField inputTopic;
    
    @FXML
    private PasswordField inputPass;
   
    @FXML
    private ListView<String> listTopic;

    @FXML
    private ListView<String> liSensor;
    
    @FXML
    private ListView<String> liConnected;
   
    @FXML
    void connect(ActionEvent event) throws InterruptedException{
    	if (pub.getBroker().isConnected()){
    		//disconnect
    		pub.unsubscribeAll();
        	pub.getBroker().disconnect();
    	}
    	else{
    		//connect
    		if (inputUser.getText().equals("") || inputPass.getText().equals("")){
    			if (inputPort.getText().equals("")){
    				pub.getBroker().connect(inputHost.getText());
    			}
    			else{
    				pub.getBroker().connect(inputHost.getText(), Integer.parseInt(inputPort.getText()));
    			}
    		}
    		else if (inputPort.getText().equals("")){
    			pub.getBroker().connect(inputHost.getText(), inputUser.getText(), inputPass.getText());
    		}
    		else{
    			pub.getBroker().connect(inputHost.getText(), Integer.parseInt(inputPort.getText()), inputUser.getText(), inputPass.getText());
    		}
    	}	
    }
    
    @FXML
    void addTopic(ActionEvent event){
    	if (inputTopic.getText().equals("")){
    		System.out.println("no topic to add.");
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Topic");
    		alert.setHeaderText(null);
    		alert.setContentText("Please specify a topic to add.");
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    		    inputTopic.requestFocus();
    		}
    	}
    	else{
        	pub.subscribe(inputTopic.getText());
        	inputTopic.setText("");
    	}
    }
    
    @FXML
    void removeTopic(ActionEvent event){
    	pub.unsubscribe(listTopic.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    void searchDevices(ActionEvent event){
    	//testing
    	System.out.println("searching for devices...");
		pub.getSensorList().addDevice("ab:cd:ef:12:34");
		pub.getSensorList().addDevice("99:99:99:99:99");
		pub.getSensorList().addDevice("12:34:56:78:90");
    }
    
    @FXML
    void connectDevice(ActionEvent event){
    	//
    	if (liSensor.getSelectionModel().getSelectedItem() != null){
    		Sensor s = new TempSensor(liSensor.getSelectionModel().getSelectedItem());
    		s.start();
    		pub.getSensorList().add(s);
    	}
    	
    
    }
    
	@Override
	public synchronized void update(Observable o, Object arg) {
		Platform.runLater(() -> {
			if (pub.getBroker().isConnected()){
				btnQuickConnect.setText("Disconnect Broker");
				btnQuickConnect.setStyle("-fx-text-fill: red;");
				inputHost.setDisable(true);
				inputPort.setDisable(true);
				inputUser.setDisable(true);
				inputPass.setDisable(true);
				
				btnSearchDevices.setDisable(false);
				
				if (!(pub.getSensorList().getDevices().isEmpty())){
					btnConnectDevice.setDisable(false);
				}
				
				inputTopic.setDisable(false);
				inputTopic.setEditable(true);
				btnAddTopic.setDisable(false);
				btnRemoveTopic.setDisable(false);
			}
			else{
				btnQuickConnect.setText("Connect Broker");
				btnQuickConnect.setStyle("-fx-text-fill: black;");
				inputHost.setDisable(false);
				inputPort.setDisable(false);
				inputUser.setDisable(false);
				inputPass.setDisable(false);
				
				btnSearchDevices.setDisable(true);
				btnConnectDevice.setDisable(true);
				
				inputTopic.setDisable(true);
				inputTopic.setEditable(false);
				btnAddTopic.setDisable(true);
				btnRemoveTopic.setDisable(true);
			}
		
			if (o.equals(pub.getSubscriberList())){
				listTopic.setItems(pub.getSubscriberList().getTopics());
			}
			
			if (o.equals(pub.getSensorList())){
				// replace with sensorlist from bluetoothdongle
				liSensor.setItems(pub.getSensorList().getDevices());
				liConnected.setItems(pub.getSensorList().getConnectedSensors());
			}
		});
	}
}
