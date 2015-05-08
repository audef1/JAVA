package application;
	
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Stopwatch s = new Stopwatch();
						
			primaryStage.setTitle("StopwatchFX");
			s.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(s.getScene());
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
