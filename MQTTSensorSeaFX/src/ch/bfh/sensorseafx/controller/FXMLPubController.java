package ch.bfh.sensorseafx.controller;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import ch.bfh.sensorseafx.model.Datastore;

public class FXMLPubController implements Observer {

	private Publisher pub;
	private Datastore store;
	
	public FXMLPubController(Publisher pub, Datastore store){
		this.pub = pub;
		this.pub.getSubscriberList().addObserver(this);
		this.pub.getBroker().addObserver(this);
		
		this.store = store;
		this.store.addObserver(this);
		
		this.pub.setPeriod(Duration.seconds(5));
	}

	@FXML
    private Button btnAddTopic;
    
	@FXML
    private Button btnRemoveTopic;
	
    @FXML
    private Button btnQuickConnect;
    
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
    private ListView<String> listSensors;
   
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
    				pub.start();
    			}
    			else{
    				pub.getBroker().connect(inputHost.getText(), Integer.parseInt(inputPort.getText()));
    				pub.start();
    			}
    		}
    		else if (inputPort.getText().equals("")){
    			pub.getBroker().connect(inputHost.getText(), inputUser.getText(), inputPass.getText());
    			pub.start();
    		}
    		else{
    			pub.getBroker().connect(inputHost.getText(), Integer.parseInt(inputPort.getText()), inputUser.getText(), inputPass.getText());
    			pub.start();
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
    		pub.setDebug(true);
        	pub.subscribe(inputTopic.getText());
        	inputTopic.setText("");
    	}
    }
    
    @FXML
    void removeTopic(ActionEvent event){
    	pub.unsubscribe(listTopic.getSelectionModel().getSelectedItem());
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
				
				inputTopic.setDisable(false);
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
				
				inputTopic.setDisable(true);
				btnAddTopic.setDisable(true);
				btnRemoveTopic.setDisable(true);
			}
		
			if (o.equals(pub.getSubscriberList())){
				listTopic.setItems(pub.getSubscriberList().getTopics());
			}
			
			if (o.equals(pub.getSensorList())){
				list.setItems(pub.getSubscriberList().getTopics());
			}
		});
	}
}
