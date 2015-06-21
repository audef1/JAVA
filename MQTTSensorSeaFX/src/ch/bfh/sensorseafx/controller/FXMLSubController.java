package ch.bfh.sensorseafx.controller;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import ch.bfh.sensorseafx.model.Datastore;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class FXMLSubController implements Observer{
    
	private Subscriber sub;
	private Datastore store;
	
	public FXMLSubController(Subscriber sub, Datastore store){
		this.sub = sub;
		this.sub.getSubscriberList().addObserver(this);
		this.sub.getBroker().addObserver(this);
		
		this.sub.setDatastore(store);
		this.store = store;
		this.store.addObserver(this);

		sub.setPeriod(Duration.seconds(20));
		sub.start();
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
   
   private CategoryAxis xAxis = new CategoryAxis();
   private NumberAxis yAxis = new NumberAxis();
   
   @FXML
   private LineChart<String,Number> linechartTemp = new LineChart<String, Number>(xAxis, yAxis);
   
    @FXML
    void connect(ActionEvent event) throws InterruptedException{
    	if (sub.getBroker().isConnected()){
    		//disconnect
    		sub.unsubscribeAll();
        	sub.getBroker().disconnect();
    	}
    	else{
    		//connect
    		if (inputUser.getText().equals("") || inputPass.getText().equals("")){
    			if (inputPort.getText().equals("")){
    				sub.getBroker().connect(inputHost.getText());
    			}
    			else{
    				sub.getBroker().connect(inputHost.getText(), Integer.parseInt(inputPort.getText()));
    			}
    		}
    		else if (inputPort.getText().equals("")){
    			sub.getBroker().connect(inputHost.getText(), inputUser.getText(), inputPass.getText());
    		}
    		else{
    			sub.getBroker().connect(inputHost.getText(), Integer.parseInt(inputPort.getText()), inputUser.getText(), inputPass.getText());
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
    		sub.setDebug(true);
        	sub.subscribe(inputTopic.getText());
        	inputTopic.setText("");
    	}
    	
    }
    
    @FXML
    void removeTopic(ActionEvent event){
    	sub.unsubscribe(listTopic.getSelectionModel().getSelectedItem());
    }
    
	@Override
	public synchronized void update(Observable o, Object arg) {
		Platform.runLater(() -> {
			if (sub.getBroker().isConnected()){
				btnQuickConnect.setText("Disconnect");
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
				btnQuickConnect.setText("Connect");
				btnQuickConnect.setStyle("-fx-text-fill: black;");
				inputHost.setDisable(false);
				inputPort.setDisable(false);
				inputUser.setDisable(false);
				inputPass.setDisable(false);
				
				inputTopic.setDisable(true);
				btnAddTopic.setDisable(true);
				btnRemoveTopic.setDisable(true);
			}
				
			
			if (o.equals(sub.getSubscriberList())){
				listTopic.setItems(sub.getSubscriberList().getTopics());
			}
			
			linechartTemp.setData(store.getDatastore());
		});
	}
}
