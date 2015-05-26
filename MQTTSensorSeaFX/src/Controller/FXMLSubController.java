package Controller;

import java.util.Observable;
import java.util.Observer;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLSubController implements Observer{
    
	private Subscriber sub;
	
	public FXMLSubController(Subscriber sub){
		this.sub = sub;
		this.sub.addObserver(this);
	}
	
	@FXML
    private Button btnAddTopic;
    
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
    void connect(ActionEvent event){
    	sub.getBroker().connect(inputHost.getText(), Integer.parseInt(inputPort.getText()), inputUser.getText(), inputPass.getText());
    }
    
    @FXML
    void addTopic(ActionEvent event){
    	sub.setSysout(true);
    	sub.subscribe(inputTopic.getText());
    	
    	listTopic.setItems(sub.getTopics());
    }
    
	@Override
	public void update(Observable o, Object arg) {

	}
}
