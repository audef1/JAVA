package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLSubController{
    
	private Subscriber sub;
	
	public FXMLSubController(Subscriber sub){
		this.sub = sub;
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
    private  PasswordField inputPass;
   
    @FXML
    private  ListView listTopic;
    
    @FXML
    void connect(ActionEvent event){
    	
    	sub.getBroker().connect(inputHost.getText(), Integer.parseInt(inputPort.getText()), inputUser.getText(), inputPass.getText());
    }
    
    @FXML
    void addTopic(ActionEvent event){
    	sub.setSysout(true);
    	sub.subscribe(inputTopic.getText());
    }
}
