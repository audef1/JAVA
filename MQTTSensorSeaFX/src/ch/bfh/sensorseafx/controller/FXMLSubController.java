package ch.bfh.sensorseafx.controller;

import java.util.Observable;
import java.util.Observer;

import ch.bfh.sensorseafx.model.Datastore;
import ch.bfh.sensorseafx.sensors.Sensor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
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
    	sub.getBroker().connect(inputHost.getText(), Integer.parseInt(inputPort.getText()), inputUser.getText(), inputPass.getText());
    	//linechartTemp.getXAxis().setTickLabelsVisible(false);
    	//linechartTemp.getXAxis().setTickMarkVisible(false);
    }
    
    @FXML
    void addTopic(ActionEvent event){
    	sub.setSysout(true);
    	sub.subscribe(inputTopic.getText());
    }
    
    @FXML
    void removeTopic(ActionEvent event){
    	sub.unsubscribe(listTopic.getSelectionModel().getSelectedItem());
    }
    
	@Override
	public void update(Observable o, Object arg) {
		
		if (o.equals(sub)){
			listTopic.setItems(sub.getTopics());
		}
		linechartTemp.getData().clear();
		linechartTemp.getData().add(store.getTempSeries());
		
	}
}
