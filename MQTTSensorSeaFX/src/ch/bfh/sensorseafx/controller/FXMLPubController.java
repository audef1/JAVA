package ch.bfh.sensorseafx.controller;

import java.util.Observable;
import java.util.Observer;

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
    		pub.getBroker().connect(inputHost.getText(), Integer.parseInt(inputPort.getText()), inputUser.getText(), inputPass.getText());
        	//linechartTemp.getXAxis().setTickLabelsVisible(false);
        	//linechartTemp.getXAxis().setTickMarkVisible(false);
    	}
    }
    
    @FXML
    void addTopic(ActionEvent event){
    	if (inputTopic.getText().equals("")){
    		System.out.println("no topic to add.");
    	}
    	else{
    		pub.setSysout(true);
        	pub.addTopic(inputTopic.getText());
    	}
    }
    
    @FXML
    void removeTopic(ActionEvent event){
    	pub.removeTopic(listTopic.getSelectionModel().getSelectedItem());
    }
    
	@Override
	public void update(Observable o, Object arg) {
		
		if (o.equals(pub)){
			listTopic.setItems(pub.getTopics());
		}
		linechartTemp.getData().clear();
		linechartTemp.getData().add(store.getTempSeries());
		
	}
}
