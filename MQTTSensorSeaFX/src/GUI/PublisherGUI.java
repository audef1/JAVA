package GUI;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PublisherGUI extends Stage implements Observer{

	private BorderPane bp = new BorderPane();
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
