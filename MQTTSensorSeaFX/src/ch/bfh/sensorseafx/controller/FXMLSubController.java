package ch.bfh.sensorseafx.controller;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import ch.bfh.sensorseafx.model.Datastore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLSubController implements Observer{
    
	private Subscriber sub;
	private Datastore store;
	
	public FXMLSubController(Subscriber sub, Datastore store){
		this.sub = sub;
		this.store = store;
		this.sub.addObserver(this);
		this.store.addObserver(this);
		this.sub.getBroker().addObserver(this);
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
    	if (sub.getBroker().isConnected()){
    		sub.getBroker().disconnect();
    	}
    	else{
    		sub.getBroker().connect(inputHost.getText(), Integer.parseInt(inputPort.getText()), inputUser.getText(), inputPass.getText());
        	//linechartTemp.getXAxis().setTickLabelsVisible(false);
        	//linechartTemp.getXAxis().setTickMarkVisible(false);
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
    		alert.showAndWait();
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    		    inputTopic.setFocusTraversable(true);
    		}
    	}
    	else{
    		sub.setSysout(true);
        	sub.subscribe(inputTopic.getText());
    	}
    	
    }
    
    @FXML
    void removeTopic(ActionEvent event){
    	sub.unsubscribe(listTopic.getSelectionModel().getSelectedItem());
    }
    
	@Override
	public void update(Observable o, Object arg) {
		
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
			
		
		if (o.equals(sub)){
			listTopic.setItems(sub.getTopics());
		}
		linechartTemp.getData().clear();
		linechartTemp.getData().add(store.getTempSeries());
		
	}
}
