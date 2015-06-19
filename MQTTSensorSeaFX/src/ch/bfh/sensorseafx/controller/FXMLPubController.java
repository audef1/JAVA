package ch.bfh.sensorseafx.controller;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ch.bfh.sensorseafx.model.Datastore;

public class FXMLPubController implements Observer {

	private Publisher pub;
	private Datastore store;
	
	public FXMLPubController(Publisher pub, Datastore store){
		this.pub = pub;
		this.store = store;
		this.pub.addObserver(this);
		this.store.addObserver(this);
		this.pub.getBroker().addObserver(this);
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
   private TextArea outputConsole;
   
   @FXML
   private LineChart<Number,Number> linechartTemp;   
   
    @FXML
    void connect(ActionEvent event){
    	if (pub.getBroker().isConnected()){
    		pub.getBroker().disconnect();
    	}
    	else{
    		//checks if not empty, connect without port, etc.
    		pub.getBroker().connect(inputHost.getText(), Integer.parseInt(inputPort.getText()), inputUser.getText(), inputPass.getText());
    	}
    }
    
    @FXML
    void addTopic(ActionEvent event){
    	if (inputTopic.getText().equals("")){
    		System.out.println("no topic to add.");
    	}
    	else{
    		pub.setDebug(true);
        	pub.addTopic(inputTopic.getText());
    	}
    }
    
    @FXML
    void removeTopic(ActionEvent event){
    	pub.removeTopic(listTopic.getSelectionModel().getSelectedItem());
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
		
			if (o.equals(pub)){
				listTopic.setItems(pub.getTopics());
			}
		});
	}
}
