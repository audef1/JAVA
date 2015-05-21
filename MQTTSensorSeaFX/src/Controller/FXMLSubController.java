package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLSubController {
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
    void connect(ActionEvent event){
    	inputUser.setText("Connect");
    }
    
    @FXML
    void addTopic(ActionEvent event){
    	
    }
    
    
}
